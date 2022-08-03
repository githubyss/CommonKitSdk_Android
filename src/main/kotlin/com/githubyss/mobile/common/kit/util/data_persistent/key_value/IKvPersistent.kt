package com.githubyss.mobile.common.kit.util.data_persistent.key_value


/**
 * IKvPersistent
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/01 16:19:07
 */
interface IKvPersistent {
    fun put(key:String,value:String)
    fun getInt(): Int
    fun getLong(): Long
    fun getString(): String
    fun getBoolean(): Boolean
}
