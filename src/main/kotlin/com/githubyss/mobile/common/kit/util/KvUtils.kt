package com.githubyss.mobile.common.kit.util

import com.githubyss.mobile.common.kit.util.data_persistent.key_value.KvPersistent
import com.githubyss.mobile.common.kit.util.data_persistent.key_value.KvPersistentSharedPreference


fun put() = KvPersistent.put<KvPersistentSharedPreference>()

fun getInt() = KvPersistent.getInt<KvPersistentSharedPreference>()
