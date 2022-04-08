package com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder


class Product {
    val parts: ArrayList<String> = ArrayList()

    fun add(part: String) {
        parts.add(part)
    }

    fun show() {
        println("产品创建")
        parts.forEach {
            println(it)
        }
    }
}