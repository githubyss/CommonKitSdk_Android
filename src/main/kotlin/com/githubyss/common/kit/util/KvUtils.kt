package com.githubyss.common.kit.util

import com.githubyss.common.kit.util.data_persistent.key_value.KvPersistent
import com.githubyss.common.kit.util.data_persistent.key_value.KvPersistentSharedPreference


fun put() = KvPersistent.put<KvPersistentSharedPreference>()

fun getInt() = KvPersistent.getInt<KvPersistentSharedPreference>()
