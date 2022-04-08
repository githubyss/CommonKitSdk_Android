package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.PersonBuilderAbstract


class PersonBuilderDirector(private val builder: PersonBuilderAbstract) {
    fun build() {
        builder.addHead()
        builder.addBody()
        builder.addArmLeft()
        builder.addArmRight()
        builder.addLegLeft()
        builder.addLegRight()
    }
}