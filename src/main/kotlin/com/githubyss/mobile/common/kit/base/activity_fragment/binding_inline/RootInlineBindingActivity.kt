package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.view_binding.binding_inflate.inflateBindingByLayoutInflater


inline fun <reified B : ViewBinding> Activity.inflate(): Lazy<B?> {
    return lazy { inflateBindingByLayoutInflater<B>(layoutInflater).apply { setContentView(root) } }
}
