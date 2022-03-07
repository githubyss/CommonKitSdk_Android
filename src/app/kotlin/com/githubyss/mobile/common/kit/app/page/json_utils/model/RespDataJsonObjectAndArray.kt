package com.githubyss.mobile.common.kit.app.page.json_utils.model

import com.githubyss.mobile.common.kit.util.getJSONArray
import com.githubyss.mobile.common.kit.util.getJSONObject
import com.githubyss.mobile.common.kit.util.getString
import org.json.JSONException
import org.json.JSONObject


class RespDataJsonObjectAndArray(jsonObject: JSONObject?) {
    var code: String = ""
    var message: String = ""
    var data: ResponseData? = null

    init {
        try {
            jsonObject?.let {
                code = getString(it, "code")
                message = getString(it, "message")
                data = ResponseData(getJSONObject(it, "data"))
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
                    getJSONArray(jsonObject, "infos")?.let { it2 ->
                        for (i in 0 until it2.length()) {
                            infos.add(JsonUtilsModel.Info(getJSONObject(it2, i)))
                        }
                    }
                    detail = JsonUtilsModel.Detail(getJSONObject(it, "detail"))
                }
            }
            catch (e: JSONException) {
            }
        }



    }
}
