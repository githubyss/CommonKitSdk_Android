package com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseFragment
import com.githubyss.mobile.common.kit.util.logE
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * RootReflectBindingFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 11:27:32
 */
abstract class RootReflectBindingFragment<B : ViewDataBinding> : BaseFragment(0) {

    /** ****************************** Properties ****************************** */

    private lateinit var _binding: B
    val binding get() = _binding


    /** ****************************** Override ****************************** */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = (type.actualTypeArguments[0]) as Class<B>
                val methodInflate = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                _binding = (methodInflate.invoke(null, inflater, container, false)) as B

                // 这个写法有问题，会崩溃
                // _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
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

        return binding.root
    }

    override fun onDestroyView() {
        _binding.unbind()
        // _binding = null
        super.onDestroyView()
    }
}
