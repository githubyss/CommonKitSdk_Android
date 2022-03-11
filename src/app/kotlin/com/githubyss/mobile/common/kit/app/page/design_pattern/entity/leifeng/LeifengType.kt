package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng


sealed class LeifengType(val value: String) {
    object UNDERGRADUATE : LeifengType("学雷锋大学生")
    object VOLUNTEER : LeifengType("社区志愿者")
}