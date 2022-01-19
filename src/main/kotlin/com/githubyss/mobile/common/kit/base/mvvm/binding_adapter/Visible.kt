package com.githubyss.mobile.common.kit.base.mvvm.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter


// @BindingAdapter("visibleOrGone")
// fun View.isVisibleOrGone(visible: Boolean) {
//     visibility = if (visible) View.VISIBLE else View.GONE
// }
//
// @BindingAdapter("visible")
// fun View.isVisible(visible: Boolean) {
//     visibility = if (visible) View.VISIBLE else View.INVISIBLE
// }

@set: BindingAdapter("bind:isVisibleOrGone")
var View.isVisibleOrGone
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

@set: BindingAdapter("bind:isVisibleOrInvisible")
var View.isVisibleOrInvisible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

@set:BindingAdapter("bind:isInvisible")
var View.isInvisible
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

@set:BindingAdapter("bind:isGone")
var View.isGone
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }
