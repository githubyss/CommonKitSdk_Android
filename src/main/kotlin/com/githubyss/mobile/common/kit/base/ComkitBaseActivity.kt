package com.githubyss.mobile.common.kit.base

import android.app.Fragment
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
    /** Bind Presenter. by Ace Yan */
    open fun bindPresenter() {}

    /** Init Views, Listeners, Adapters, and so on. by Ace Yan */
    open fun initView() {}

    /** Init default value of Top-level variables, and so on. by Ace Yan */
    open fun initData() {}

    /** Refresh Views. by Ace Yan */
    open fun refreshView() {}

    /** Setup Toolbar title by ResId. by Ace Yan */
    open fun setToolbarTitle(titleResId: Int) {
//        toolbarBase.title = ComkitResUtils.getString(this@ComkitBaseActivity, titleResId)
        toolbarBase.setTitle(titleResId)
    }

    /** Setup Toolbar title by String. by Ace Yan */
    open fun setToolbarTitle(titleString: String) {
        toolbarBase.title = titleString
    }

    /** Setup Toolbar navigation icon by ResId. by Ace Yan */
    open fun setToolbarNavigationIcon(iconResId: Int) {
//        toolbarBase.navigationIcon = ComkitResUtils.getDrawable(this@ComkitBaseActivity, iconResId)
        toolbarBase.setNavigationIcon(iconResId)
    }

    /** Setup Toolbar navigation icon by Drawable. by Ace Yan */
    open fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        toolbarBase.navigationIcon = iconDrawable
    }

    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        fragment.arguments = intent.extras
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragmentContainer, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.comkit_activity_base)
        setSupportActionBar(toolbarBase)
    }
}
