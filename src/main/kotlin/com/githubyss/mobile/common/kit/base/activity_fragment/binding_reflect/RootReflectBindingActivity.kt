package com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.util.logE
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType


/**
 * RootReflectBindingActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 10:48:25
 */
abstract class RootReflectBindingActivity<B : ViewBinding> : BaseActivity(0) {

    /** ****************************** Properties ****************************** */

    private var _binding: B? = null
    val binding: B? get() = _binding


    /** ****************************** Override ****************************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<B>?
                val inflateMethod: Method? = clazz?.getMethod("inflate", LayoutInflater::class.java)
                val inflater = layoutInflater
                _binding = inflateMethod?.invoke(null, inflater) as B
                setContentView(binding?.root ?: return)
            }
            catch (e: NoSuchMethodException) {
                logE(TAG, t = e)
            }
            catch (e: IllegalAccessException) {
                logE(TAG, t = e)
            }
            catch (e: InvocationTargetException) {
                logE(TAG, t = e)
            }
        }

        super.onCreate(savedInstanceState)
    }
}
