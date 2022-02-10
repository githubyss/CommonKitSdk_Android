// package com.githubyss.mobile.common.kit.app.page.json_utils.model
//
// import com.githubyss.mobile.common.kit.util.JsonUtils
// import com.githubyss.mobile.common.ui.recycler_view.three_layer.old.ProductInfo
// import org.json.JSONArray
// import org.json.JSONException
// import org.json.JSONObject
//
//
// class RespDataJsonObject<T>(json: JSONObject?) {
//     var code: String = ""
//     var message: String = ""
//     var data: ResponseData<T>? = null
//
//     init {
//         try {
//             json?.let {
//                 code = JsonUtils.getString(it, "code")
//                 message = JsonUtils.getString(it, "message")
//                 data = ResponseData(JsonUtils.getJSONObject(it, "data"))
//             }
//         }
//         catch (e: JSONException) {
//         }
//     }
//
//
//     class ResponseData<T>(json: JSONObject?) {
//         var infos: T? = null
//             private set
//
//         init {
//             try {
//                 json?.let {
//                     val productInfos: JSONArray? = JsonUtils.getJSONArray(json, "productInfos")
//                     productInfos?.let {
//                         for (i in 0 until it.length()) {
//                             infoList.add(ProductInfo(JsonUtils.getJSONObject(it, i)))
//                         }
//                     }
//                 }
//             }
//             catch (e: JSONException) {
//             }
//         }
//     }
// }
