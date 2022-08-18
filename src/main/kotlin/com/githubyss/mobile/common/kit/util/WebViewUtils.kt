package com.githubyss.mobile.common.kit.util

import android.graphics.Bitmap
import android.view.View
import android.webkit.*
import android.widget.ProgressBar


/**
 * WebViewUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/18 15:19:48
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG = "EncodeUtils"

/** 点击不会跳转到 WebView 外的 WebViewClient */
val webViewClientLoadInside = object : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}


/** ****************************** Functions ****************************** */

/**
 * 设置 WebView 默认配置
 * @param webView
 */
@JvmName("defaultWebSetting_")
fun defaultWebSetting(webView: WebView?) = webView.defaultWebSetting()
fun WebView?.defaultWebSetting() {
    this ?: return

    val webSettings = this.settings

    /**
     * 是否允许执行 JS，默认为 false。
     * 设置 true 时，会提醒可能造成 XSS 漏洞。
     */
    webSettings.javaScriptEnabled = true

    /** 设置 JS 可以直接打开窗口，如 window.open()，默认为 false。 */
    webSettings.javaScriptCanOpenWindowsAutomatically = true

    /** 设置可以访问文件。 */
    webSettings.allowFileAccess = true

    /** 是否使用缓存。 */
    webSettings.setAppCacheEnabled(true)

    /**
     * 设置如何缓存。
     * - LOAD_DEFAULT：默认使用缓存，当缓存没有，或者缓存过期，才使用网络。
     * - LOAD_CACHE_ELSE_NETWORK：设置默认使用缓存，即使是缓存过期，也使用缓存，只有缓存消失，才使用网络。
     */
    webSettings.cacheMode = WebSettings.LOAD_DEFAULT

    /** DOM Storage。 */
    webSettings.domStorageEnabled = true

    /** 是否可以缩放，默认true。 */
    webSettings.setSupportZoom(true)

    /** 是否显示缩放按钮，默认false。 */
    webSettings.builtInZoomControls = true

    /** 设置加载进来的页面自适应手机屏幕（可缩放），可任意比例缩放。大视图模式。 */
    webSettings.useWideViewPort = true

    /** 和 useWideViewPort = true 一起解决网页自适应问题。 */
    webSettings.loadWithOverviewMode = true

    /** 设置用户代理，一般不用 */
    // webSettings.setUserAgentString("User-Agent:Android")
}

/**
 * 设置 WebView 初始值信息并且绑定 url 等操作
 *
 * @param url
 * @param progressBar
 * @param openInside true: 在 WebView 打开，false: 在手机默认浏览器打开
 */
fun initWebView(url: String?, progressBar: ProgressBar, openInside: Boolean = true, webView: WebView?) = webView.initWebView(url, progressBar, openInside)
fun WebView?.initWebView(url: String?, progressBar: ProgressBar, openInside: Boolean = true) {
    this ?: return

    /** 获取焦点 */
    this.requestFocus()

    /**  */
    this.isHorizontalScrollBarEnabled = false
    this.isVerticalScrollBarEnabled = false

    /**  */
    this.setVerticalScrollbarOverlay(true)

    this.defaultWebSetting()

    /** 进度条显示隐藏 */
    this.webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            when {
                newProgress >= 100 -> progressBar.visibility = View.GONE
                else -> {
                    when (progressBar.visibility) {
                        View.GONE, View.INVISIBLE -> progressBar.visibility = View.VISIBLE
                    }
                    progressBar.progress = newProgress
                }
            }
            super.onProgressChanged(view, newProgress)
        }
    }

    /** 加载 url */
    this.post(Runnable { this.loadUrl(url ?: "") })

    this.webViewClient = object : WebViewClient() {
        // 点击不会跳转到 WebView 外
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            // 返回值是 true 的时候控制去 WebView 打开，为 false 调用系统浏览器或第三方浏览器。
            return openInside //super.shouldOverrideUrlLoading(view, url);
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            progressBar.visibility = View.GONE
        }
    }
}

/**
 * 得到 HTML 并显示到 WebView 中
 *
 * @param url 要打开 HTML 的路径
 * @param webView WebView 控件
 */
fun getHtml(url: String?, webView: WebView?) = webView.getHtml(url)
fun WebView?.getHtml(url: String?) {
    this ?: return

    this.defaultWebSetting()
    this.loadUrl(url ?: "")
    this.webViewClient = webViewClientLoadInside
}

/**
 * 返回 Html 的上一个页面
 * @param webView
 */
@JvmName("backHtml_")
fun backHtml(webView: WebView?) = webView.backHtml()
fun WebView?.backHtml(): Boolean {
    this ?: return false

    /** 返回前一个页面 */
    return when {
        this.canGoBack() -> {
            this.goBack()
            true
        }
        else -> false
    }
}

fun WebView?.resume() {
    this ?: return

    this.visibility = View.VISIBLE
    this.settings.javaScriptEnabled = true
    this.onResume()
}

fun WebView?.pause() {
    this ?: return
    this.settings.javaScriptEnabled = false
    this.onPause()
}

fun WebView?.destroy() {
    this ?: return

    this.visibility = View.GONE
    this.destroy()
}
