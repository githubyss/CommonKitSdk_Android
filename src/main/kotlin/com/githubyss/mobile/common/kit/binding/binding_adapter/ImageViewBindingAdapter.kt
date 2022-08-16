package com.githubyss.mobile.common.kit.binding.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.githubyss.mobile.common.kit.glide.loadImage


@BindingAdapter(value = ["path", "placeholder", "error"], requireAll = false)
fun ImageView.image(path: Any?, placeholder: Any?, error: Any?) {
    loadImage(this, this, path, placeholder, error)
}
