package com.githubyss.mobile.common.kit.util.data_persistent.key_value

import com.githubyss.design_pattern.factory.create


/**
 * KvPersistent
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/01 17:02:48
 */
object KvPersistent {
    inline fun <reified S : IKvPersistent> put() {
        return create<S>().put("", "")
    }

    inline fun <reified S : IKvPersistent> getInt(): Int {
        return create<S>().getInt()
    }
}
