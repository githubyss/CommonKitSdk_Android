package com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_root.RootReflectBindingActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseReflectBindingToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:21
 */
abstract class BaseReflectBindingToolbarActivity<B : ViewDataBinding> : RootReflectBindingActivity<B>() {

    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Make sure that you can use Toolbar as simple as ActionBar. */
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> setSupportActionBar((binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase)
        }
    }

    /**  */
    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }


    /** ****************************** Abstract ****************************** */

    /**  */
    abstract fun setToolbarTitle()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        // toolbarBase.text = getStringFromRes(this@BaseReflectBindingToolbarActivity, titleResId)
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.setTitle(titleResId)
        }
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.title = titleString
        }
    }

    /** Setup Toolbar navigation icon by ResId. */
    protected fun setToolbarNavigationIcon(iconResId: Int) {
        // toolbarBase.navigationIcon = getDrawableFromRes(this@BaseReflectBindingToolbarActivity, iconResId)
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.setNavigationIcon(iconResId)
        }
    }

    /** Setup Toolbar navigation icon by Drawable. */
    protected fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.navigationIcon = iconDrawable
        }
    }

    /** Setup Toolbar navigation click listener. */
    protected fun setToolbarNavigationOnClickListener(onBaseToolbarNavigationClickListener: OnBaseToolbarNavigationClickListener) {
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.setNavigationOnClickListener { v ->
                onBaseToolbarNavigationClickListener.onClick(v)
            }
        }
    }

    /** Setup Toolbar menu item click listener. */
    protected fun setToolbarMenuItemOnClickListener(onBaseToolbarMenuItemClickListener: OnBaseToolbarMenuItemClickListener) {
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.setOnMenuItemClickListener { item ->
                onBaseToolbarMenuItemClickListener.onClick(item)
            }
        }
    }

    /** Get the menu in Toolbar. */
    protected fun getMenu(): Menu? {
        return when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.menu
            else -> null
        }
    }

    /**  */
    protected fun setToolbarOnLongClickListener(onBaseToolbarLongClickListener: OnBaseToolbarLongClickListener) {
        when (binding) {
            is ComkitActivityBaseToolbarBinding -> (binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.setOnLongClickListener { v ->
                onBaseToolbarLongClickListener.onLongClick(v)
            }
        }
    }


    /** ****************************** Interface ****************************** */

    /** Toolbar navigation click listener in BaseReflectBindingToolbarActivity. */
    interface OnBaseToolbarNavigationClickListener {
        fun onClick(v: View)
    }

    /** Toolbar menu item click listener in BaseReflectBindingToolbarActivity. */
    interface OnBaseToolbarMenuItemClickListener {
        fun onClick(item: MenuItem): Boolean
    }

    /**  */
    interface OnBaseToolbarLongClickListener {
        fun onLongClick(v: View): Boolean
    }
}
