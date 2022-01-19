package com.githubyss.mobile.common.kit.base.view_binding.recycler_view.inline

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.base.view_binding.function.inflateBindingByViewGroup


/**
 * 通过 ViewGroup 获取 ViewHolder
 *
 * @param parent ViewGroup
 * @return ViewHolder 实例
 */
inline fun <reified B : ViewBinding> inflate(parent: ViewGroup): BaseInlineBindingViewHolder<B> {
    return BaseInlineBindingViewHolder(inflateBindingByViewGroup(parent))
}
