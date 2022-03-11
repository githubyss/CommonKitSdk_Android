package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.person_finery


open class Person() {
    var name: String = ""

    constructor(name: String) : this() {
        this.name = name
    }

    open fun show() {
        println("装扮的$name")
    }
}