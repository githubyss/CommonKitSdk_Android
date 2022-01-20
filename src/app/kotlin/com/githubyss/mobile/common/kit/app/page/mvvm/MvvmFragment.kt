package com.githubyss.mobile.common.kit.app.page.mvvm

import android.view.View
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm.enumeration.DisplayType
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentMvvmBinding


/**
 * MvvmFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:18:21
 */
class MvvmFragment : BaseInlineBindingToolbarFragment(R.layout.comkit_fragment_mvvm) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = MvvmFragment::class.java.simpleName
    }

    private val binding by bindView<ComkitFragmentMvvmBinding>()
    private val mvvmVmObservableField: MvvmVmObservableField by lazy { ViewModelProvider(requireActivity()).get(MvvmVmObservableField::class.java) }
    private val mvvmVmLiveData: MvvmVmLiveData by lazy { ViewModelProvider(requireActivity()).get(MvvmVmLiveData::class.java) }


    /** ****************************** Override ****************************** */

    override fun init() {
        initView()
        initData()
        // initObservableField()
        initLiveData()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_mvvm_title)
    }


    /** ****************************** Functions ****************************** */

    private fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        // binding.tcvCountdown.remainingMillisecond = 7200000
    }

    private fun initData() {
        binding.mvvmVm = mvvmVmLiveData
    }

    private fun initObservableField() {
        mvvmVmObservableField.isTextShow?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (mvvmVmObservableField.isTextShow?.get()) {
                    true -> binding.textDisplay.visibility = View.VISIBLE
                    false -> binding.textDisplay.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun initLiveData() {
        mvvmVmLiveData.displayType?.observe(viewLifecycleOwner, Observer { s ->
            when (s) {
                DisplayType.TEXT -> {
                    binding.flexboxText.visibility = View.VISIBLE
                    binding.flexboxImage.visibility = View.GONE
                }
                DisplayType.IMAGE -> {
                    binding.flexboxText.visibility = View.GONE
                    binding.flexboxImage.visibility = View.VISIBLE
                }
            }
        })
    }
}
