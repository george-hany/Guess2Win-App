package com.core.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.core.utils.AppConstant

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {
    var baseActivity: BaseActivity<*, *>? = null
    private var mRootView: View? = null
    lateinit var viewDataBinding: T
    lateinit var mViewModel: V

    lateinit var navigation: NavController

    var isSubFragment = false

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

    abstract fun getViewModel(): V

    abstract fun handleError()
    /**
     * Override for set view model
     *
     * @return view model instance
     */

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    private fun messageObserver() {
        mViewModel.message.observe(viewLifecycleOwner, Observer {
            if (it is Int) {
                showMessage(it)
            } else {
                showMessage(it as String)
            }
        })
    }

    private fun isLoadingObserver() {
        mViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mRootView = viewDataBinding.root
        return mRootView
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onAttach(context: Context) {
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context
        }
        super.onAttach(context)
    }

    private fun initViewModel() {
        mViewModel = getViewModel()
        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(mViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        isLoadingObserver()
        messageObserver()
        performDataBinding()
        initNavigationController(view)
        exceptionMessageObserver()
    }

    private fun exceptionMessageObserver() {
        mViewModel.dataRecources.exceptionMessage.observe(viewLifecycleOwner, Observer {
            if (it is Int) {
                showMessage(it)
            } else {
                showMessage(it as String)
            }
            handleError()
            mViewModel.setIsLoading(false)
        })
    }

    private fun initNavigationController(view: View) {
        if (!isSubFragment)
            navigation = Navigation.findNavController(view)
    }

    private fun performDataBinding() {
        viewDataBinding.setVariable(bindingVariable(), mViewModel)
        viewDataBinding.lifecycleOwner = this
    }

    fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    fun showMessage(resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(resId)
        }
    }

    fun showMessage(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(message)
        }
    }

    fun showLoading() {
        if (baseActivity != null) {
            baseActivity!!.showLoading()
        }
    }

    fun hideLoading() {
        if (baseActivity != null) {
            baseActivity!!.hideLoading()
        }
    }

    fun showLog(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showLog(message)
        }
    }

    fun showLog(resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showLog(resId)
        }
    }

    /**
     * Navigate to a destination via the given deep link {@sample resId}.
     * @param resId deepLink to the destination reachable from the current NavGraph with its resId
     * @param bundle special options for this navigation operation
     */
    fun navigateToUri(@StringRes resId: Int, bundle: Bundle = Bundle()) {
        val build = NavArgument.Builder().setDefaultValue(bundle).build()
        navigation.graph.addArgument("bundle", build)
        navigation.navigate(Uri.parse(getString(resId)))
    }

    /**
     * fun to get graph arguments
     *
     * @return Bundle graph bundle instance
     */
    fun getGraphArguments(): Bundle? {
        val bundle = navigation.graph.arguments["bundle"]?.defaultValue as Bundle
        navigation.graph.removeArgument("bundle")
        return bundle
    }

    /**
     * fun to return arguments from deeplink intent
     *
     * @return Bundle deeplink bundle instance
     */
    fun getDeepLinkArguments(): Bundle? {
        return requireArguments().getParcelable<Intent>(AppConstant.deepLinkArgumentsKey)?.extras
    }

    /**
     * Navigate to a destination via the given deep link {@sample resId} and removing all back stack.
     * @param resId deepLink to the destination reachable from the current NavGraph with its resId
     * @param bundle special options for this navigation operation
     */
    fun navigateToUriWithClearStack(@StringRes resId: Int, bundle: Bundle = Bundle()) {
        val intent = Intent()
        intent.putExtras(bundle)
        intent.data = Uri.parse(getString(resId))
        navigation.handleDeepLink(intent)
    }
}
