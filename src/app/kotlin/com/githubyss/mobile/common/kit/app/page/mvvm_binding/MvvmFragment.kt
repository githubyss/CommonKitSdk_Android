package com.githubyss.mobile.common.kit.app.page.mvvm_binding

import android.view.View
import androidx.databinding.Observable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.enumeration.DisplayType
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.live_data.MvvmEdittextVmByLiveData
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.live_data.MvvmImageVmByLiveData
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.live_data.MvvmTextVmByLiveData
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.live_data.MvvmViewModelByLiveData
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.observable_field.MvvmViewModelObservableField
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentMvvmBinding
import com.githubyss.mobile.common.kit.util.logD


/**
 * MvvmFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:18:21
 */
class MvvmFragment : BaseReflectBindingToolbarFragment<ComkitFragmentMvvmBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = MvvmFragment::class.java.simpleName
    }

    // private val mvvmVmLiveData: MvvmViewModelByLiveData by lazy { ViewModelProvider(requireActivity()).get(MvvmViewModelByLiveData::class.java) }
    private val mvvmVmLiveData: MvvmViewModelByLiveData by viewModels()
    private val mvvmTextVmLiveData: MvvmTextVmByLiveData by viewModels()
    private val mvvmImageVmLiveData: MvvmImageVmByLiveData by viewModels()
    private val mvvmEdittextVmLiveData: MvvmEdittextVmByLiveData by viewModels()

    // private val mvvmVmObservableField: MvvmViewModelObservableField by lazy { ViewModelProvider(requireActivity()).get(MvvmViewModelObservableField::class.java) }
    private val mvvmVmObservableField: MvvmViewModelObservableField by viewModels()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun setupData() {
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_mvvm_title)
    }

    override fun bindViewModelXml() {
        binding.mvvmVm = this.mvvmVmLiveData
        binding.layoutText.mvvmVm = this.mvvmTextVmLiveData
        binding.layoutImage.mvvmVm = this.mvvmImageVmLiveData
        binding.layoutEdittext.mvvmVm = this.mvvmEdittextVmLiveData

        // binding.mvvmVm = this.mvvmVmObservableField
    }

    override fun observeViewModelData() {
        observeVmByLiveData()
        // observeVmByObservableField()
    }

    override fun removeViewModelObserver() {
        removeVmObserverByLiveData()
        // removeVmObserverByObservableField()
    }


    /** ****************************** Functions ****************************** */

    private fun observeVmByLiveData() {
        this.mvvmVmLiveData.displayType.observe(this, changeObserverByLiveData)
        this.mvvmEdittextVmLiveData.edittext.observe(this) {
            logD(msg = "edittext: $it")
        }
    }

    private fun observeVmByObservableField() {
        this.mvvmVmObservableField.displayType.addOnPropertyChangedCallback(changeObserverByObservableField)
        this.mvvmVmObservableField.displayType.notifyChange()
    }

    private fun removeVmObserverByLiveData() {
        this.mvvmVmLiveData.displayType.removeObservers(this)
        this.mvvmEdittextVmLiveData.edittext.removeObservers(this)
    }

    private fun removeVmObserverByObservableField() {
        this.mvvmVmObservableField.displayType.removeOnPropertyChangedCallback(changeObserverByObservableField)
    }


    /** ****************************** Implementations ****************************** */

    private val changeObserverByLiveData = Observer<DisplayType> { t ->
        when (t) {
            DisplayType.TEXT -> {
                binding.layoutText.flexboxText.visibility = View.VISIBLE
                binding.layoutImage.flexboxImage.visibility = View.GONE
                binding.layoutEdittext.flexboxEdittext.visibility = View.GONE
            }
            DisplayType.IMAGE -> {
                binding.layoutText.flexboxText.visibility = View.GONE
                binding.layoutImage.flexboxImage.visibility = View.VISIBLE
                binding.layoutEdittext.flexboxEdittext.visibility = View.GONE
            }
            DisplayType.EDITTEXT -> {
                binding.layoutText.flexboxText.visibility = View.GONE
                binding.layoutImage.flexboxImage.visibility = View.GONE
                binding.layoutEdittext.flexboxEdittext.visibility = View.VISIBLE
            }
        }
    }

    private val changeObserverByObservableField = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            when (mvvmVmObservableField.displayType.get()) {
                DisplayType.TEXT -> {
                    binding.layoutText.flexboxText.visibility = View.VISIBLE
                    binding.layoutImage.flexboxImage.visibility = View.GONE
                    binding.layoutEdittext.flexboxEdittext.visibility = View.GONE
                }
                DisplayType.IMAGE -> {
                    binding.layoutText.flexboxText.visibility = View.GONE
                    binding.layoutImage.flexboxImage.visibility = View.VISIBLE
                    binding.layoutEdittext.flexboxEdittext.visibility = View.GONE
                }
                DisplayType.EDITTEXT -> {
                    binding.layoutText.flexboxText.visibility = View.GONE
                    binding.layoutImage.flexboxImage.visibility = View.GONE
                    binding.layoutEdittext.flexboxEdittext.visibility = View.VISIBLE
                }
            }
        }
    }
}
