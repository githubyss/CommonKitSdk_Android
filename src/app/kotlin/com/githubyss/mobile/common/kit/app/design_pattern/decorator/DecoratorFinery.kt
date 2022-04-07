package com.githubyss.mobile.common.kit.app.design_pattern.decorator

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person.Person


open class DecoratorFinery : Person() {
    private var person: Person? = null

    override fun show() {
        person?.show()
    }

    fun decorator(person: Person): Person {
        this.person = person
        return person
    }
}