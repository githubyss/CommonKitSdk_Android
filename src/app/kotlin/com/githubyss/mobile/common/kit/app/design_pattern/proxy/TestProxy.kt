package com.githubyss.mobile.common.kit.app.design_pattern.proxy

import com.githubyss.mobile.common.kit.app.design_pattern.entity.give_gift.SchoolGirl


fun proxy() {
    println("送礼物")
    val mm = SchoolGirl("美眉")
    val proxy = Proxy("中介")
    proxy.mm = mm
    proxy.giveDolls()
    proxy.giveFlowers()
    proxy.giveChocolate()
    println()
}