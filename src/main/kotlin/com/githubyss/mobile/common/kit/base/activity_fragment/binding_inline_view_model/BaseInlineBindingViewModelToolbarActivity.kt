package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_view_model

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.inflate
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseInlineBindingViewModelToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/22 13:12:26
 */
abstract class BaseInlineBindingViewModelToolbarActivity : BaseActivity(0) {

    /** ****************************** Properties ****************************** */

    /***/
    val binding by inflate<ComkitActivityBaseToolbarBinding>()


    /** ****************************** Override ****************************** */

    /***/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindLifecycleOwner()
        bindViewModelXml()
        observeViewModelData()

        /** Make sure that you can use Toolbar as simple as ActionBar. */
        if (binding is ComkitActivityBaseToolbarBinding) setSupportActionBar((binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }

    override fun onDestroy() {
        removeViewModelObserver()
        super.onDestroy()
    }


    /** ****************************** Abstract ****************************** */

    /***/
    abstract fun setToolbarTitle()

    /** 绑定 Activity LifecycleOwner 到 ViewDataBinding */
    abstract fun bindLifecycleOwner()

    /** 绑定 ViewModel 到 ViewDataBinding */
    abstract fun bindViewModelXml()

    /** 观察 ViewModel 的数据变化 */
    abstract fun observeViewModelData()

    /** 移除 ViewModel 的数据观察 */
    abstract fun removeViewModelObserver()


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