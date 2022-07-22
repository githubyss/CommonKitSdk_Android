package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root

import android.app.Activity
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.githubyss.mobile.common.kit.binding.binding_inflate.inflateBindingByLayoutInflater
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


inline fun <reified B : ViewDataBinding> Activity.inflate(): Lazy<B> {
    return lazy { inflateBindingByLayoutInflater<B>(layoutInflater).apply { setContentView(root) } }
}


inline fun <reified B : ViewDataBinding> Fragment.bindView() = FragmentBindingDelegate(B::class.java)

class FragmentBindingDelegate<B : ViewDataBinding>(private val clazz: Class<B>) : ReadOnlyProperty<Fragment, B> {

    private lateinit var _binding: B
    private val binding get() = _binding

    private var isInitialized = false

    override fun getValue(thisRef: Fragment, property: KProperty<*>): B {
        if (!isInitialized) {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroyView() {
                    _binding.unbind()
                    // _binding = null
                }
            })
            val methodBind = clazz.getMethod("bind", View::class.java)
            _binding = methodBind.invoke(null, thisRef.requireView()) as B

            isInitialized = true
        }
        return binding
    }
}
