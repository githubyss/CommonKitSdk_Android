package com.githubyss.mobile.common.kit.manager.font

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.githubyss.mobile.common.kit.util.logE
import java.lang.ref.SoftReference


/**
 * FontManager
 * 字体管理器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:38:22
 */
object FontManager {

    /** ****************************** Properties ****************************** */

    private val TAG: String = FontManager::class.java.simpleName

    /** Use cache to hold all loaded typeface. by Ace Yan */
    private var typefaceCacheMap = HashMap<String, SoftReference<Typeface>>()


    /** ****************************** Functions ****************************** */

    fun replaceFontFromAsset(rootView: View?, fontPath: String, fontStyle: Int = Typeface.NORMAL) {
        replaceFont(rootView, createTypefaceFromAsset(rootView?.context, fontPath), fontStyle)
    }

    fun replaceFontFromFile(rootView: View?, fontPath: String, fontStyle: Int = Typeface.NORMAL) {
        replaceFont(rootView, createTypefaceFromFile(fontPath), fontStyle)
    }

    /**
     * FontManager.replaceSystemDefaultFontFromAsset([context, fontPath])
     * Replace system default font.
     * <Details>
     *     You should also add code below to your app theme in styles.xml:
     *     {@code <item name="android:typeface">monospace</item>}
     *     The best place to call this method is {@link Application#onCreate()}, it will affect the whole app font.
     *     If you call this method after view is visible, you need to invalid the view to make it effective.
     *
     * @attention Does not compatible to android.support.v7.app.AppCompatActivity
     *
     * @params [context, fontPath]
     * @param context
     * @param fontPath Font file path relative to 'assets' directory.
     * @return
     */
    fun replaceSystemDefaultFontFromAsset(context: Context?, fontPath: String) {
        replaceSystemDefaultFont(createTypefaceFromAsset(context, fontPath))
    }

    fun replaceSystemDefaultFontFromFile(fontPath: String) {
        replaceSystemDefaultFont(createTypefaceFromFile(fontPath))
    }


    /**
     * FontManager.replaceFont([rootView, typeface, fontStyle])
     * Replace the font of specified view and it's children with specified typeface and font style.
     * <Details>
     *
     * @params [rootView, typeface, fontStyle]
     * @param rootView The root view.
     * @param typeface Font file path relative to 'assets' directory.
     * @param fontStyle One of {@link Typeface#NORMAL}, {@link Typeface#BOLD}, {@link Typeface#ITALIC}, {@link Typeface#BOLD_ITALIC}.
     * @return
     */
    private fun replaceFont(rootView: View?, typeface: Typeface?, fontStyle: Int = Typeface.NORMAL) {
        var fontStyleCopy = fontStyle
        if (fontStyleCopy < 0 || fontStyleCopy > 3) {
            fontStyleCopy = Typeface.NORMAL
        }

        if (rootView is TextView) {
            rootView.setTypeface(typeface, fontStyleCopy)
        }
        else if (rootView is ViewGroup) {
            for (idx in 0 until rootView.childCount) {
                replaceFont(rootView.getChildAt(idx), typeface, fontStyleCopy)
            }
        }
    }

    /**
     * FontManager.createTypefaceFromAsset([context, fontPath])
     * Create a Typeface instance with specified font file.
     * <Details>
     *
     * @params [context, fontPath]
     * @param context
     * @param fontPath Font file path relative to 'assets' directory.
     * @return
     */
    private fun createTypefaceFromAsset(context: Context?, fontPath: String): Typeface? {
        var typefaceRef = typefaceCacheMap[fontPath]
        var typeface = typefaceRef?.get()
        if (typefaceRef == null || typefaceRef.get() == null) {
            typeface = Typeface.createFromAsset(context?.assets, fontPath)
            typefaceRef = SoftReference(typeface)
            typefaceCacheMap.put(fontPath, typefaceRef)
        }

        return typeface
    }

    private fun createTypefaceFromFile(fontPath: String): Typeface? {
        var typefaceRef = typefaceCacheMap[fontPath]
        var typeface = typefaceRef?.get()
        if (typefaceRef == null || typefaceRef.get() == null) {
            typeface = Typeface.createFromFile(fontPath)
            typefaceRef = SoftReference(typeface)
            typefaceCacheMap.put(fontPath, typefaceRef)
        }

        return typeface
    }

    private fun replaceSystemDefaultFont(typeface: Typeface?) {
        // modifyObjectField(null, "SANS_SERIF", typeface)
        // modifyObjectField(null, "SERIF", typeface)
        modifyObjectField(null, "MONOSPACE", typeface)
    }

    private fun modifyObjectField(any: Any?, fieldName: String, typeface: Typeface?) {
        try {
            val defaultField = Typeface::class.java.getDeclaredField(fieldName)
            defaultField.isAccessible = true
            defaultField.set(any, typeface)
        }
        catch (exception: NoSuchFieldException) {
            logE(TAG, t = exception)
        }
        catch (exception: IllegalAccessException) {
            logE(TAG, t = exception)
        }
    }
}
