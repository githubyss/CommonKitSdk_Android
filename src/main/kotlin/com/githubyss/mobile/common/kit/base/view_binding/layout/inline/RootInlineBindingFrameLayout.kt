package com.githubyss.mobile.common.kit.base.view_binding.layout.inline

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.base.view_binding.function.inflateBindingByViewGroup


inline fun <reified B : ViewBinding> FrameLayout.inflate(parent: ViewGroup): Lazy<B> {
    return lazy { inflateBindingByViewGroup(parent) }
}