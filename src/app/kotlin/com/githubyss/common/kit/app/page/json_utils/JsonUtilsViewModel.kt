package com.githubyss.common.kit.app.page.json_utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.common.kit.app.page.json_utils.model.JsonUtilsModel
import com.githubyss.common.kit.app.page.json_utils.model.RespDataJsonObjectAndArray
import com.githubyss.common.kit.util.getJsonStringFromAssets
import com.githubyss.common.kit.util.string2JSONObject


/**
 * JsonUtilsViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/09 16:00:06
 */
class JsonUtilsViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    /**  */
    private val DATA_OBJECT_AND_ARRAY = "json/sample/data_object_and_array.json"
    private val DATA_ARRAY = "json/sample/data_array.json"
    private val DATA_OBJECT = "json/sample/data_object.json"

    /** model（数据源 Java Bean） */
    private var infos: List<JsonUtilsModel.Info>? = null
    private var detail: JsonUtilsModel.Detail? = null

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var jsonText: MutableLiveData<String>? = null
    var parsedInfos: MutableLiveData<List<JsonUtilsModel.Info>>? = null
    var parsedDetail: MutableLiveData<JsonUtilsModel.Detail>? = null


    /** ****************************** Constructors ****************************** */

    init {
        initViewModelField()
    }


    /** ****************************** Override ****************************** */

    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /** ******************** Data Handling ******************** */

    private fun initViewModelField() {
        this.jsonText = MutableLiveData()
        this.parsedInfos = MutableLiveData()
        this.parsedDetail = MutableLiveData()
    }

    private fun clearData() {
        this.infos = null
        this.detail = null
    }

    /** ******************** Event Handling ******************** */

    fun onButtonReadJsonTextClick() {
        this.jsonText?.value = getJsonStringFromAssets(DATA_OBJECT_AND_ARRAY)
    }

    fun onButtonParseJsonClick() {
        val response = RespDataJsonObjectAndArray(this.jsonText?.value.string2JSONObject())
        this.infos = response.data?.infos
        this.detail = response.data?.detail

        this.parsedInfos?.value = infos
        this.parsedDetail?.value = detail
    }


    /** ****************************** Class ****************************** */

    // class ViewStyle {
    //     var isTimeShow = ObservableBoolean()
    // }
}
