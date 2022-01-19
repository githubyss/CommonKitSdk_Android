package com.githubyss.mobile.common.kit.base.view_binding.function

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


/**
 * 通过 LayoutInflater 获取 ViewBinding
 *
 * @param layoutInflater LayoutInflater
 * @return ViewBinding 实例
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified B : ViewBinding> inflateBindingByLayoutInflater(layoutInflater: LayoutInflater): B {
    return B::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as B
}

/**
 * 通过 ViewGroup 获取 ViewBinding
 *
 * @param parent ViewGroup
 * @return ViewBinding 实例
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified B : ViewBinding> inflateBindingByViewGroup(parent: ViewGroup): B {
    return B::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        .invoke(null, LayoutInflater.from(parent.context), parent, false) as B
}
