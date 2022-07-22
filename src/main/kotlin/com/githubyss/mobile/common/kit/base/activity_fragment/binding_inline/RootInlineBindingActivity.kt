package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.binding.binding_inflate.inflateBindingByLayoutInflater


inline fun <reified B : ViewDataBinding> Activity.inflate(): Lazy<B> {
    return lazy { inflateBindingByLayoutInflater<B>(layoutInflater).apply { setContentView(root) } }
}
