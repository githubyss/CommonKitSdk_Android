package com.githubyss.mobile.common.kit.view_binding.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.githubyss.mobile.common.kit.glide.GlideUtils


@BindingAdapter(value = ["path", "placeholder", "error"], requireAll = false)
fun ImageView.loadImage(path: Any?, placeholder: Any?, error: Any?) {
    GlideUtils.loadImage(this, this, path, placeholder, error)
}
