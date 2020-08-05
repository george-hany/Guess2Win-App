package com.feature.profile.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.feature.profile.BR
import com.feature.profile.R
import com.feature.profile.databinding.EditDialogBinding
import com.feature.profile.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    val profileViewModel: ProfileViewModel by viewModel()
    private lateinit var dialogInfo: Dialog
    private lateinit var dialogBinding: EditDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogInfo = Dialog(requireContext())
        dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE)
        editButtonListener()
        changePasswordSuccessObserver()
    }

    private fun changePasswordSuccessObserver() {
        profileViewModel.changePasswordSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                activity?.toast("Phone Changed Successfully")
                dialogInfo.dismiss()
            }
        })
    }

    private fun phoneNumberErrorObserver() {
        profileViewModel.phoneNumberError.observe(viewLifecycleOwner, Observer {
            dialogBinding.phoneNumber.error = getString(it)
        })
    }

    private fun editButtonListener() {
        viewDataBinding.editPhone.setOnClickListener {
            openConfirmationDialog()
        }
    }

    private fun openConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        dialogInfo.setCancelable(false)
        dialogBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.edit_dialog,
                null,
                false
            )
        dialogBinding.viewModel = profileViewModel
        setUpCCP()
        phoneNumberErrorObserver()
        builder.setView(dialogBinding.root)
        dialogInfo = builder.create()
        dialogBinding.cancel.setOnClickListener {
            dialogInfo.dismiss()
        }
        dialogInfo.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogInfo.show()
    }

    fun setUpCCP() {
        dialogBinding.ccpGetFullNumber.detectNetworkCountry(true)
        dialogBinding.ccpGetFullNumber.detectSIMCountry(true)
        dialogBinding.ccpGetFullNumber.showNameCode(false)
        dialogBinding.ccpGetFullNumber.detectLocaleCountry(true)
        dialogBinding.ccpGetFullNumber.registerCarrierNumberEditText(dialogBinding.phoneNumber)
        dialogBinding.ccpGetFullNumber.setPhoneNumberValidityChangeListener { isValidNumber ->
            profileViewModel.isValidNumber = isValidNumber
            if (isValidNumber)
                profileViewModel.changePhoneRequest.phoneNumber =
                    dialogBinding.ccpGetFullNumber.fullNumberWithPlus
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_profile

    override fun getViewModel(): ProfileViewModel = profileViewModel

    override fun handleError() {}
}
