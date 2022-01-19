package com.githubyss.mobile.common.kit.base.view_binding.page.inline

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.githubyss.mobile.common.kit.base.view_binding.page.base.BaseActivity
import com.githubyss.mobile.common.kit.databinding.ComuiActivityBaseToolbarBinding


/**
 * BaseInlineBindingToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 16:45:23
 */
abstract class BaseInlineBindingToolbarActivity : BaseActivity(0) {

    /** ****************************** Properties ****************************** */

    val binding by inflate<ComuiActivityBaseToolbarBinding>()


    /** ****************************** Override ****************************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Make sure that you can use Toolbar as simple as ActionBar. */
        if (binding is ComuiActivityBaseToolbarBinding) setSupportActionBar((binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }


    /** ****************************** Abstract ****************************** */

    abstract fun setToolbarTitle()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        // toolbarBase.text = ResourceUtils.getString(this@BaseInlineBindingToolbarActivity, titleResId)
        if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
    }

    /** Setup Toolbar navigation icon by ResId. */
    protected fun setToolbarNavigationIcon(iconResId: Int) {
        // toolbarBase.navigationIcon = ResourceUtils.getDrawable(this@BaseInlineBindingToolbarActivity, iconResId)
        if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setNavigationIcon(iconResId)
    }

    /** Setup Toolbar navigation icon by Drawable. */
    protected fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.navigationIcon = iconDrawable
    }

    /** Setup Toolbar navigation click listener. */
    protected fun setToolbarNavigationOnClickListener(onBaseToolbarNavigationClickListener: OnBaseToolbarNavigationClickListener) {
        if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setNavigationOnClickListener { v ->
            onBaseToolbarNavigationClickListener.onClick(v)
        }
    }

    /** Setup Toolbar menu item click listener. */
    protected fun setToolbarMenuItemOnClickListener(onBaseToolbarMenuItemClickListener: OnBaseToolbarMenuItemClickListener) {
        if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setOnMenuItemClickListener { item ->
            onBaseToolbarMenuItemClickListener.onClick(item)
        }
    }

    /** Get the menu in Toolbar. */
    protected fun getMenu(): Menu? {
        return if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.menu else null
    }

    protected fun setToolbarOnLongClickListener(onBaseToolbarLongClickListener: OnBaseToolbarLongClickListener) {
        if (binding is ComuiActivityBaseToolbarBinding) (binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setOnLongClickListener { v ->
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
