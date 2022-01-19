package com.githubyss.mobile.common.kit.base.view_binding.recycler_view.inline

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


/**
 * BaseInlineBindingViewHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/08 09:59:43
 */
class BaseInlineBindingViewHolder<B : ViewBinding> : RecyclerView.ViewHolder {

    /** ****************************** Properties ****************************** */

    private var _binding: B? = null
    val binding: B get() = _binding!!


    /** ****************************** Constructors ****************************** */

    constructor(binding: B) : super(binding.root) {
        _binding = binding
    }
}
