package com.githubyss.mobile.common.kit.app.page.json_utils.model

import com.githubyss.mobile.common.kit.util.getJSONArrayFromJSONObject
import com.githubyss.mobile.common.kit.util.getJSONObjectFromJSONArray
import com.githubyss.mobile.common.kit.util.getJSONObjectFromJSONObject
import com.githubyss.mobile.common.kit.util.getStringFromJSONObject
import org.json.JSONException
import org.json.JSONObject


class RespDataJsonObjectAndArray(jsonObject: JSONObject?) {
    var code: String = ""
    var message: String = ""
    var data: ResponseData? = null

    init {
        try {
            jsonObject?.let {
                code = getStringFromJSONObject(it, "code") ?: ""
                message = getStringFromJSONObject(it, "message") ?: ""
                data = ResponseData(getJSONObjectFromJSONObject(it, "data"))
            }
        }
        catch (e: JSONException) {
        }
    }


    class ResponseData(jsonObject: JSONObject?) {
        var infos = ArrayList<JsonUtilsModel.Info>()
            private set

        var detail: JsonUtilsModel.Detail? = null

        init {
            try {
                jsonObject?.let {
                    getJSONArrayFromJSONObject(jsonObject, "infos")?.let { it2 ->
                        for (i in 0 until it2.length()) {
                            infos.add(JsonUtilsModel.Info(getJSONObjectFromJSONArray(it2, i)))
                        }
                    }
                    detail = JsonUtilsModel.Detail(getJSONObjectFromJSONObject(it, "detail"))
                }
            }
            catch (e: JSONException) {
            }
        }


    }
}
