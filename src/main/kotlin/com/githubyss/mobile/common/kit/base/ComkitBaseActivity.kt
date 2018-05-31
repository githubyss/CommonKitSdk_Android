package com.githubyss.mobile.common.kit.base

import android.app.Fragment
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.githubyss.mobile.common.kit.R
import kotlinx.android.synthetic.main.comkit_toolbar_base.*

/**
 * ComkitBaseActivity.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
abstract class ComkitBaseActivity : AppCompatActivity() {
    /** Toolbar navigation click listener in ComkitBaseActivity. by Ace Yan */
    interface OnComkitBaseToolbarNavigationClickListener {
        fun onClick(v: View)
    }

    /** Toolbar menu item click listener in ComkitBaseActivity. by Ace Yan */
    interface OnComkitBaseToolbarMenuItemClickListener {
        fun onClick(item: MenuItem): Boolean
    }

    interface OnComkitBaseToolbarLongClickListener {
        fun onLongClick(v: View): Boolean
    }


    /** Bind Presenter. by Ace Yan */
    open fun bindPresenter() {}

    /** Init Views, Listeners, Adapters, and so on. by Ace Yan */
    open fun initView() {}

    /** Init default value of Top-level variables, and so on. by Ace Yan */
    open fun initData() {}

    /** Refresh Views. by Ace Yan */
    open fun refreshView() {}


    /** Setup Toolbar title by ResId. by Ace Yan */
    protected fun setToolbarTitle(titleResId: Int) {
//        toolbarBase.title = ComkitResUtils.getString(this@ComkitBaseActivity, titleResId)
        toolbarBase.setTitle(titleResId)
    }

    /** Setup Toolbar title by String. by Ace Yan */
    protected fun setToolbarTitle(titleString: String) {
        toolbarBase.title = titleString
    }

    /** Setup Toolbar navigation icon by ResId. by Ace Yan */
    protected fun setToolbarNavigationIcon(iconResId: Int) {
//        toolbarBase.navigationIcon = ComkitResUtils.getDrawable(this@ComkitBaseActivity, iconResId)
        toolbarBase.setNavigationIcon(iconResId)
    }

    /** Setup Toolbar navigation icon by Drawable. by Ace Yan */
    protected fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        toolbarBase.navigationIcon = iconDrawable
    }

    /** Setup Toolbar navigation click listener. by Ace Yan */
    protected fun setToolbarNavigationOnClickListener(onComkitBaseToolbarNavigationClickListener: OnComkitBaseToolbarNavigationClickListener) {
        toolbarBase.setNavigationOnClickListener { v ->
            onComkitBaseToolbarNavigationClickListener.onClick(v)
        }
    }

    /** Setup Toolbar menu item click listener. by Ace Yan */
    protected fun setToolbarMenuItemOnClickListener(onComkitBaseToolbarMenuItemClickListener: OnComkitBaseToolbarMenuItemClickListener) {
        toolbarBase.setOnMenuItemClickListener { item ->
            onComkitBaseToolbarMenuItemClickListener.onClick(item)
        }
    }

    /** Get the menu in Toolbar. by Ace Yan */
    protected fun getMenu(): Menu {
        return toolbarBase.menu
    }

    protected fun setToolbarOnLongClickListener(onComkitBaseToolbarLongClickListener: OnComkitBaseToolbarLongClickListener) {
        toolbarBase.setOnLongClickListener { v ->
            onComkitBaseToolbarLongClickListener.onLongClick(v)
        }
    }

    /** Add fragment to activity. by Ace Yan */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        if (fragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        fragment.arguments = intent.extras
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragmentContainer, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        if (fragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        fragment.arguments = intent.extras
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragmentContainer, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.comkit_activity_base)

        /** Make sure that you can use Toolbar as simple as ActionBar. by Ace Yan */
        setSupportActionBar(toolbarBase)
    }
}
