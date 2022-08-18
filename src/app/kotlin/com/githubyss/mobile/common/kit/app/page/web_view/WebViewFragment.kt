package com.githubyss.mobile.common.kit.app.page.web_view

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentWebViewBinding
import com.githubyss.mobile.common.kit.util.backHtml
import com.githubyss.mobile.common.kit.util.initWebView


/**
 * WebViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/18 15:58:57
 */
class WebViewFragment : BaseReflectBindingViewModelToolbarFragment<ComkitFragmentWebViewBinding>() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = WebViewFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val webViewVm by viewModels<WebViewViewModel>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!binding.webViewContent.backHtml()) {
                    activity?.finish()
                }
            }
        })
        binding.webViewContent.initWebView(webViewVm.url.value, binding.webViewProgressBar)
    }


    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_web_view_title)
    }

    /**  */
    override fun bindXmlData() {
        binding.webViewVm = webViewVm
        binding.webViewPage = this
    }


    /** ****************************** Functions ****************************** */

    fun onHighlightTextClick() {
        binding.webViewContent.findAllAsync(webViewVm.highlightKeyword.value ?: return)
    }
}
