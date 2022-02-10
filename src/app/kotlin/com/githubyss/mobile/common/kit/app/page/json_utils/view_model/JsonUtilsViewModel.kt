package com.githubyss.mobile.common.kit.app.page.json_utils.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.app.page.json_utils.model.JsonUtilsModel
import com.githubyss.mobile.common.kit.app.page.json_utils.model.RespDataJsonObjectAndArray
import com.githubyss.mobile.common.kit.util.JsonUtils


/**
 * JsonUtilsViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/09 16:00:06
 */
class JsonUtilsViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** context */
    // var context: Activity? = null

    private val JSON_FILE_PATH = "json/netres/resp_data_sample/resp_data_object_and_array.json"

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
        this.jsonText?.value = JsonUtils.getJsonStringFromAssets(JSON_FILE_PATH)
    }

    fun onButtonParseJsonClick() {
        val response = RespDataJsonObjectAndArray(JsonUtils.jsonStringToJSONObject(this.jsonText?.value))
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
