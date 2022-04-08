package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.PersonBuilder


class PersonDirector(private val personBuilder: PersonBuilder) {
    fun build() {
        personBuilder.buildHead()
        personBuilder.buildBody()
        personBuilder.buildArnLeft()
        personBuilder.buildArmRight()
        personBuilder.buildLegLeft()
        personBuilder.buildLegRight()
    }
}