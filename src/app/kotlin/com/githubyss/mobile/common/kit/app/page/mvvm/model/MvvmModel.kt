package com.githubyss.mobile.common.kit.app.page.mvvm.model

import com.githubyss.mobile.common.kit.util.LogUtils
import org.json.JSONException
import org.json.JSONObject


/**
 * MvvmModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:40:36
 */
interface MvvmModel {


    /** ****************************** Class ****************************** */

    /** 文本 */
    class TextBean {

        /** ****************************** Properties ****************************** */

        companion object {
            val TAG: String = TextBean::class.java.simpleName
        }

        var text: String = ""


        /** ****************************** Constructors ****************************** */

        constructor()

        constructor(text: String) {
            this.text = text
        }

        constructor(json: JSONObject?) {
            setProperties(json)
        }


        /** ****************************** Functions ****************************** */

        private fun setProperties(json: JSONObject?) {
            try {
                json ?: return

                text = json.optString("text")
            }
            catch (e: JSONException) {
                LogUtils.e(TAG, t = e)
            }
        }
    }

    /** 图片 */
    class ImageBean {

        /** ****************************** Properties ****************************** */

        companion object {
            val TAG: String = TextBean::class.java.simpleName
        }

        var imageUrl: String = ""


        /** ****************************** Constructors ****************************** */

        constructor()

        constructor(imageUrl: String) {
            this.imageUrl = imageUrl
        }

        constructor(json: JSONObject?) {
            setProperties(json)
        }


        /** ****************************** Functions ****************************** */

        private fun setProperties(json: JSONObject?) {
            try {
                json ?: return

                imageUrl = json.optString("imageUrl")
            }
            catch (e: JSONException) {
                LogUtils.e(TAG, t = e)
            }
        }
    }
}
