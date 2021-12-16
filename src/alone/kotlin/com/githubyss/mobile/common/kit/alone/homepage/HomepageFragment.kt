package com.githubyss.mobile.common.kit.alone.homepage

import android.view.View
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentHomepageBinding
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarFragment
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.bindView


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:42
 */
class HomepageFragment : BaseToolbarFragment(R.layout.comkit_fragment_homepage) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = HomepageFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComkitFragmentHomepageBinding>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        initView()
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        binding.btnLog.setOnClickListener(onClickListener)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_log -> {
            }
        }
    }
}
