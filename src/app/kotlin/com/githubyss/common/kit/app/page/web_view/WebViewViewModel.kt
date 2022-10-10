package com.githubyss.common.kit.app.page.web_view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * WebViewViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/18 15:38:53
 */
class WebViewViewModel : ViewModel() {

    /**  */
    val url by lazy { MutableLiveData("https://www.woshipm.com/") }

    val highlightKeyword by lazy { MutableLiveData("") }


    /** ****************************** Functions ****************************** */

    /**  */
}
