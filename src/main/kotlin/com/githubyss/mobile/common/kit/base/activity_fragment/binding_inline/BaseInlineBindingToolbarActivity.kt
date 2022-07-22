package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.RootInlineBindingActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseInlineBindingToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 16:45:23
 */
abstract class BaseInlineBindingToolbarActivity<B : ViewDataBinding>(@LayoutRes layoutId: Int) : RootInlineBindingActivity<B>(layoutId) {

    /** ****************************** Override ****************************** */

    /***/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Make sure that you can use Toolbar as simple as ActionBar. */
        if (binding is ComkitActivityBaseToolbarBinding) setSupportActionBar((binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }


    /** ****************************** Abstract ****************************** */

    /***/
    abstract fun setToolbarTitle()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        // toolbarBase.text = getStringFromRes(this@BaseInlineBindingToolbarActivity, titleResId)
        if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
    }

    /** Setup Toolbar navigation icon by ResId. */
    protected fun setToolbarNavigationIcon(iconResId: Int) {
        // toolbarBase.navigationIcon = getDrawableFromRes(this@BaseInlineBindingToolbarActivity, iconResId)
        if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setNavigationIcon(iconResId)
    }

    /** Setup Toolbar navigation icon by Drawable. */
    protected fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.navigationIcon = iconDrawable
    }

    /** Setup Toolbar navigation click listener. */
    protected fun setToolbarNavigationOnClickListener(onBaseToolbarNavigationClickListener: OnBaseToolbarNavigationClickListener) {
        if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setNavigationOnClickListener { v ->
            onBaseToolbarNavigationClickListener.onClick(v)
        }
    }

    /** Setup Toolbar menu item click listener. */
    protected fun setToolbarMenuItemOnClickListener(onBaseToolbarMenuItemClickListener: OnBaseToolbarMenuItemClickListener) {
        if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setOnMenuItemClickListener { item ->
            onBaseToolbarMenuItemClickListener.onClick(item)
        }
    }

    /** Get the menu in Toolbar. */
    protected fun getMenu(): Menu? {
        return if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.menu else null
    }

    protected fun setToolbarOnLongClickListener(onBaseToolbarLongClickListener: OnBaseToolbarLongClickListener) {
        if (binding is ComkitActivityBaseToolbarBinding) (binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setOnLongClickListener { v ->
            onBaseToolbarLongClickListener.onLongClick(v)
        }
    }


    /** ****************************** Interface ****************************** */

    /** Toolbar navigation click listener in BaseInlineBindingToolbarActivity. */
    interface OnBaseToolbarNavigationClickListener {
        fun onClick(v: View)
    }

    /** Toolbar menu item click listener in BaseInlineBindingToolbarActivity. */
    interface OnBaseToolbarMenuItemClickListener {
        fun onClick(item: MenuItem): Boolean
    }

    interface OnBaseToolbarLongClickListener {
        fun onLongClick(v: View): Boolean
    }
}
