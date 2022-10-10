package com.githubyss.common.kit.util.data_persistent.key_value


/**
 * KvPersistentSharedPreference
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/01 16:24:07
 */
class KvPersistentSharedPreference : IKvPersistent {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = KvPersistentSharedPreference::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    // val instance

    /** ****************************** Override ****************************** */

    /**  */
    override fun put(key: String, value: String) {
    }

    /**  */
    override fun getInt(): Int {
        TODO()
    }

    /**  */
    override fun getLong(): Long {
        TODO()
    }

    /**  */
    override fun getString(): String {
        TODO()
    }

    /**  */
    override fun getBoolean(): Boolean {
        TODO()
    }
}
