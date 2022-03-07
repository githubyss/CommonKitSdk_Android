package com.githubyss.mobile.common.kit.app.page.json_utils.model

import com.githubyss.mobile.common.kit.util.logE
import com.githubyss.mobile.common.kit.util.getString
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
                    id = getString(it, "id")
                    name = getString(it, "name")
                    action = getString(it, "action")
                }
            }
            catch (e: JSONException) {
                logE(TAG, t = e)
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
                    time = getString(it, "time")
                    location = getString(it, "location")
                    weather = getString(it, "weather")
                }
            }
            catch (e: JSONException) {
                logE(TAG, t = e)
            }
        }
    }
}
