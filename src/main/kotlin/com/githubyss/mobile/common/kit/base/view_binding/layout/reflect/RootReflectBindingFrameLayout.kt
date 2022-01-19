package com.githubyss.mobile.common.kit.base.view_binding.layout.reflect

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.base.view_binding.page.base.BaseFragment
import com.githubyss.mobile.common.kit.util.LogUtils
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType


/**
 * RootReflectBindingFrameLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 11:39:37
 */
abstract class RootReflectBindingFrameLayout<B : ViewBinding> : FrameLayout {

    /** ****************************** Properties ****************************** */

    private var _binding: B? = null
    val binding: B get() = _binding!!


    /** ****************************** Constructors ****************************** */

    @Suppress("LeakingThis")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<B>?
                val inflateMethod: Method? = clazz?.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                val inflater = LayoutInflater.from(context)
                val container = this
                _binding = inflateMethod?.invoke(null, inflater, container, true) as B
            }
            catch (e: NoSuchMethodException) {
                LogUtils.e(BaseFragment.TAG, t = e)
            }
            catch (e: IllegalAccessException) {
                LogUtils.e(BaseFragment.TAG, t = e)
            }
            catch (e: InvocationTargetException) {
                LogUtils.e(BaseFragment.TAG, t = e)
            }
        }
    }
}
