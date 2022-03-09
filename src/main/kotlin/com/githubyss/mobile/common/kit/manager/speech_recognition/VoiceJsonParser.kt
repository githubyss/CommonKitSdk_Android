package com.githubyss.mobile.common.kit.manager.speech_recognition

import com.githubyss.mobile.common.kit.util.logE
import org.json.JSONObject
import org.json.JSONTokener


/**
 * Json结果解析类
 */
object VoiceJsonParser {
    private val TAG: String = VoiceJsonParser::class.java.simpleName

    const val errorTip = "请确认是否有在 aiui.xfyun.cn 配置语义。（另外，已开通语义，但从1115（含1115）以前的SDK更新到1116以上版本SDK后，语义需要重新到 aiui.xfyun.cn 配置）"
    fun parseIatResult(json: String?): String {
        val ret = StringBuffer()
        try {
            val tokener = JSONTokener(json)
            val joResult = JSONObject(tokener)
            val words = joResult.getJSONArray("ws")
            for (i in 0 until words.length()) {
                // 转写结果词，默认使用第一个结果
                val items = words.getJSONObject(i).getJSONArray("cw")
                val obj = items.getJSONObject(0)
                ret.append(obj.getString("w"))
                //				如果需要多候选结果，解析数组其他字段
                //				for(int j = 0; j < items.length(); j++)
                //				{
                //					JSONObject obj = items.getJSONObject(j);
                //					ret.append(obj.getString("w"));
                //				}
            }
        }
        catch (e: Exception) {
            logE(TAG, t = e)
        }
        return ret.toString()
    }

    fun parseGrammarResult(json: String?): String {
        val ret = StringBuffer()
        try {
            val tokener = JSONTokener(json)
            val joResult = JSONObject(tokener)
            val words = joResult.getJSONArray("ws")
            for (i in 0 until words.length()) {
                val items = words.getJSONObject(i).getJSONArray("cw")
                for (j in 0 until items.length()) {
                    val obj = items.getJSONObject(j)
                    if (obj.getString("w").contains("nomatch")) {
                        ret.append("没有匹配结果.")
                        return ret.toString()
                    }
                    ret.append("【结果】" + obj.getString("w"))
                    ret.append("【置信度】" + obj.getInt("sc"))
                    ret.append("\n")
                }
            }
        }
        catch (e: Exception) {
            logE(TAG, t = e)
            ret.append("没有匹配结果.")
        }
        return ret.toString()
    }

    fun parseLocalGrammarResult(json: String?): String {
        val ret = StringBuffer()
        try {
            val tokener = JSONTokener(json)
            val joResult = JSONObject(tokener)
            val words = joResult.getJSONArray("ws")
            for (i in 0 until words.length()) {
                val items = words.getJSONObject(i).getJSONArray("cw")
                for (j in 0 until items.length()) {
                    val obj = items.getJSONObject(j)
                    if (obj.getString("w").contains("nomatch")) {
                        ret.append("没有匹配结果.")
                        return ret.toString()
                    }
                    ret.append("【结果】" + obj.getString("w"))
                    ret.append("\n")
                }
            }
            ret.append("【置信度】" + joResult.optInt("sc"))
        }
        catch (e: Exception) {
            logE(TAG, t = e)
            ret.append("没有匹配结果.")
        }
        return ret.toString()
    }

    fun parseTransResult(json: String?, key: String?): String {
        val ret = StringBuffer()
        try {
            val tokener = JSONTokener(json)
            val joResult = JSONObject(tokener)
            val errorCode = joResult.optString("ret")
            if (errorCode != "0") {
                return joResult.optString("errmsg")
            }
            val transResult = joResult.optJSONObject("trans_result")
            ret.append(transResult.optString(key))
            /*JSONArray words = joResult.getJSONArray("results");
			for (int i = 0; i < words.length(); i++) {
				JSONObject obj = words.getJSONObject(i);
				ret.append(obj.getString(key));
			}*/
        }
        catch (e: Exception) {
            logE(TAG, t = e)
        }
        return ret.toString()
    }

    fun getResultRCCode(resultText: String?): Int {
        var error = 0
        try {
            val KEY_ERROR = "rc"
            val joResult = JSONObject(resultText)
            error = joResult.optInt(KEY_ERROR)
        }
        catch (e: Throwable) {
            logE(TAG, t = e)
        } //end of try-catch
        return error
    }

    fun getVendor(resultText: String?): String {
        var vendor = ""
        try {
            val KEY_VENDOR = "vendor"
            val joResult = JSONObject(resultText)
            vendor = joResult.optString(KEY_VENDOR)
        }
        catch (e: Throwable) {
            logE(TAG, t = e)
        } //end of try-catch
        return vendor
    }

    fun getIntentCode(resultText: String?): String {
        var action = ""
        try {
            val KEY_SEMANTIC = "semantic"
            val KEY_CODE = "slots"
            val KEY_NAME = "name"
            val KEY_NORMVALUE = "normValue"
            val joResult = JSONObject(resultText)
            val joSemantic = joResult.optJSONObject(KEY_SEMANTIC)
            if (null != joSemantic) {
                val jsonArray = joSemantic.optJSONArray(KEY_CODE)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    if (jsonObject != null && jsonObject.opt(KEY_NAME) != null && "action" == jsonObject.opt(KEY_NAME)) {
                        action = jsonObject.optString(KEY_NORMVALUE)
                    }
                }
            }
        }
        catch (e: Throwable) {
            logE(TAG, t = e)
        } //end of try-catch
        return action
    }

    fun getAnswerResult(resultText: String?): String {
        var answerStr = ""
        try {
            val KEY_ANSWER = "answer"
            val KEY_CODE = "text"
            val joResult = JSONObject(resultText)
            val joAnswer = joResult.optJSONObject(KEY_ANSWER)
            if (null != joAnswer) {
                answerStr = joAnswer.optString(KEY_CODE)
            }
        }
        catch (e: Throwable) {
            logE(TAG, t = e)
        } //end of try-catch
        return answerStr
    }
}