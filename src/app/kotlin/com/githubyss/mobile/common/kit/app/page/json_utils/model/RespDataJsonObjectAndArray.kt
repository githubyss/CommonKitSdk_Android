package com.githubyss.mobile.common.kit.app.page.json_utils.model

import com.githubyss.mobile.common.kit.util.JsonUtils
import org.json.JSONException
import org.json.JSONObject


class RespDataJsonObjectAndArray(jsonObject: JSONObject?) {
    var code: String = ""
    var message: String = ""
    var data: ResponseData? = null

    init {
        try {
            jsonObject?.let {
                code = JsonUtils.getString(it, "code")
                message = JsonUtils.getString(it, "message")
                data = ResponseData(JsonUtils.getJSONObject(it, "data"))
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
                    JsonUtils.getJSONArray(jsonObject, "infos")?.let { it2 ->
                        for (i in 0 until it2.length()) {
                            infos.add(JsonUtilsModel.Info(JsonUtils.getJSONObject(it2, i)))
                        }
                    }
                    detail = JsonUtilsModel.Detail(JsonUtils.getJSONObject(it, "detail"))
                }
            }
            catch (e: JSONException) {
            }
        }



    }
}
