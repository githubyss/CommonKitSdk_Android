package com.githubyss.mobile.common.kit.app.page.json_utils.model

import com.githubyss.mobile.common.kit.util.JsonUtils
import com.githubyss.mobile.common.kit.util.LogUtils
import org.json.JSONException
import org.json.JSONObject


/**
 * JsonUtilsModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/09 15:59:56
 */
interface JsonUtilsModel {

    /** ****************************** Class ****************************** */

    /** 信息 */
    class Info(json: JSONObject?) {

        /** ****************************** Properties ****************************** */

        companion object {
            val TAG: String = Info::class.java.simpleName
        }

        var id: String = ""
        var name: String = ""
        var action: String = ""


        /** ****************************** Constructors ****************************** */

        init {
            try {
                json?.let {
                    id = JsonUtils.getString(it, "id")
                    name = JsonUtils.getString(it, "name")
                    action = JsonUtils.getString(it, "action")
                }
            }
            catch (e: JSONException) {
                LogUtils.e(TAG, t = e)
            }
        }
    }

    /** 细节 */
    class Detail(json: JSONObject?) {

        /** ****************************** Properties ****************************** */

        companion object {
            val TAG: String = Detail::class.java.simpleName
        }

        var time: String = ""
        var location: String = ""
        var weather: String = ""


        /** ****************************** Constructors ****************************** */

        init {
            try {
                json?.let {
                    time = JsonUtils.getString(it, "time")
                    location = JsonUtils.getString(it, "location")
                    weather = JsonUtils.getString(it, "weather")
                }
            }
            catch (e: JSONException) {
                LogUtils.e(TAG, t = e)
            }
        }
    }
}
