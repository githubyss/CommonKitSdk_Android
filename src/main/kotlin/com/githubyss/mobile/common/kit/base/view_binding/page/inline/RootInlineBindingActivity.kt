package com.githubyss.mobile.common.kit.base.view_binding.page.inline

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.base.view_binding.function.inflateBindingByLayoutInflater


inline fun <reified B : ViewBinding> Activity.inflate(): Lazy<B> {
    return lazy { inflateBindingByLayoutInflater<B>(layoutInflater).apply { setContentView(root) } }
}
