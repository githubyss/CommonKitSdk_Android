package com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.util.logE
import java.lang.reflect.InvocationTargetException
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

    private lateinit var _binding: B
    val binding get() = _binding


    /** ****************************** Override ****************************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = (type.actualTypeArguments[0] ?: return) as Class<B>
                val inflateMethod = clazz.getMethod("inflate", LayoutInflater::class.java) ?: return
                val inflater = layoutInflater
                _binding = (inflateMethod.invoke(null, inflater) ?: return) as B
                setContentView(binding.root)
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
