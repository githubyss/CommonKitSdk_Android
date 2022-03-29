package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.resume


class WorkExperience : Cloneable {
    var timeArea: String = ""
    var company: String = ""

    public override fun clone(): WorkExperience {
        return super.clone() as WorkExperience
    }
}