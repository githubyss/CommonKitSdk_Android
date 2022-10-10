package com.githubyss.common.kit.util

import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method


/**
 * WebViewUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/18 15:19:48
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG = "WebViewUtils"

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

/**
 * 当我们申请我们的 app 为系统应用，也就是当我们在 AndroidManifest 文件中添加 android:sharedUserId="android.uid.system" 这一行的时候，如果我们使用 WebView 就会报错：For security reasons, WebView is not allowed in privileged processes。
 * 这个是 8.0 代码中的一种安全机制。
 *
 * 另一种说法是：由于 WebView 存在安全漏洞，谷歌从 5.1 开始全面禁止系统应用使用 WebView，使用会导致应用崩溃错误提示：Caused by: java.lang.UnsupportedOperationException: For security reasons, WebView is not allowed in privileged processes
 *
 * 异常信息可以看出，崩溃是在 WebViewFactory.java 的 getProvider 方法抛出的。源码路径为 frameworks/base/core/java/android/webkit/WebViewFactory.java
 *
 * 解决方案都是通过 Hook 技术根本思想是在异常发生前就给 sProviderInstance 赋值，因为在 getProvider 方法中当判断这个值不为空就直接返回了就不会抛异常了。
 * 需要在 Activity 的 onCreate() 方法中调用，并且一定要在 setContentView() 之前调用，或者在 WebView 创建之前调用。
 *
 * @param
 * @return
 */
fun hookWebview() {
    val sdkInt = Build.VERSION.SDK_INT
    try {
        val factoryClass = Class.forName("android.webkit.WebViewFactory")
        val field = factoryClass.getDeclaredField("sProviderInstance")
        field.isAccessible = true
        var sProviderInstance = field.get(null)
        if (sProviderInstance != null) {
            logI(TAG, "sProviderInstance isn't null")
            return
        }
        val getProviderClassMethod = if (sdkInt > 22) {
            factoryClass.getDeclaredMethod("getProviderClass")
        }
        else if (sdkInt == 22) {
            factoryClass.getDeclaredMethod("getFactoryClass")
        }
        else {
            logI(TAG, "Don't need to Hook Webview")
            return
        }
        getProviderClassMethod.isAccessible = true
        val factoryProviderClass = getProviderClassMethod.invoke(factoryClass) as Class<*>
        val delegateClass = Class.forName("android.webkit.WebViewDelegate")
        val delegateConstructor: Constructor<*> = delegateClass.getDeclaredConstructor()
        delegateConstructor.isAccessible = true
        if (sdkInt < 26) { // 低于 Android O 版本
            val providerConstructor: Constructor<*>? = factoryProviderClass.getConstructor(delegateClass)
            if (providerConstructor != null) {
                providerConstructor.isAccessible = true
                sProviderInstance = providerConstructor.newInstance(delegateConstructor.newInstance())
            }
        }
        else {
            val chromiumMethodName: Field = factoryClass.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD")
            chromiumMethodName.isAccessible = true
            var chromiumMethodNameStr = chromiumMethodName.get(null) as String?
            if (chromiumMethodNameStr == null) {
                chromiumMethodNameStr = "create"
            }
            val staticFactory: Method? = factoryProviderClass.getMethod(chromiumMethodNameStr, delegateClass)
            if (staticFactory != null) {
                sProviderInstance = staticFactory.invoke(null, delegateConstructor.newInstance())
            }
        }
        if (sProviderInstance != null) {
            field.set("sProviderInstance", sProviderInstance)
            logI(TAG, "Hook success!")
        }
        else {
            logI(TAG, "Hook failed!")
        }
    }
    catch (e: Throwable) {
        logE(TAG, t = e)
    }
}
