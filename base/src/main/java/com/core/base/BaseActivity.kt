package com.core.base

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ninenox.kotlinlocalemanager.AppCompatActivityBase

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivityBase() {

    private var mProgressDialog: CustomProgressDialog = CustomProgressDialog()
    lateinit var viewDataBinding: T
    lateinit var mViewModel: V

    lateinit var navigation: NavController

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun bindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun layoutId(): Int

    @IdRes
    abstract fun controllerId(): Int

    abstract fun getViewModel(): V

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        performDataBinding()
        initNavigationController()
        isLoadingObserver()
        messageObserver()
        exceptionMessageObserver()
    }

    private fun exceptionMessageObserver() {
        mViewModel.dataRecources.exceptionMessage.observe(this, Observer {
            if (it is Int) {
                showMessage(it)
            } else {
                showMessage(it as String)
            }
            mViewModel.setIsLoading(false)
        })
    }

    private fun messageObserver() {
        mViewModel.message.observe(this, Observer {
            if (it is Int) {
                showMessage(it)
            } else {
                showMessage(it as String)
            }
        })
    }

    private fun isLoadingObserver() {
        mViewModel.isLoading.observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideLoading() {
        mProgressDialog.dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    fun showMessage(message: String) {
        toast(message)
    }

    fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    fun showLog(message: String) {
        Log.e("TAG", message)
    }

    fun showLog(@StringRes resId: Int) {
        showLog(getString(resId))
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun showLoading() {
//        hideLoading()
//        mProgressDialog =
//            CommonUtils.showCustomLoadingDialog(this)
        mProgressDialog?.show(this)
    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()

    private fun initViewModel() {
        mViewModel = getViewModel()
        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(mViewModel::class.java)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId())
        viewDataBinding.setVariable(bindingVariable(), mViewModel)
        viewDataBinding.lifecycleOwner = this
    }

    private fun initNavigationController() {
        if (controllerId() != 0)
            navigation = Navigation.findNavController(this, controllerId())
    }

    fun navigateToUriWithClearStack(@StringRes resId: Int, bundle: Bundle = Bundle()) {
        val intent = Intent()
        intent.putExtras(bundle)
        intent.data = Uri.parse(getString(resId))
        navigation.handleDeepLink(intent)
    }
}
