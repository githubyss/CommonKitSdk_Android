package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


inline fun <reified B : ViewBinding> Fragment.bindView(): FragmentBindingDelegate<B> {
    return FragmentBindingDelegate(B::class.java)
}

class FragmentBindingDelegate<B : ViewBinding>(private val clazz: Class<B>) : ReadOnlyProperty<Fragment, B?> {

    /** ****************************** Properties ****************************** */

    private var isInitialized = false
    private var _binding: B? = null
    private val binding: B? get() = _binding


    /** ****************************** Override ****************************** */

    override fun getValue(thisRef: Fragment, property: KProperty<*>): B? {
        if (!isInitialized || _binding == null) {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroyView() {
                    _binding = null
                }
            })
            _binding = clazz.getMethod("bind", View::class.java)
                .invoke(null, thisRef.requireView()) as B
            isInitialized = true
        }
        return binding
    }
}
