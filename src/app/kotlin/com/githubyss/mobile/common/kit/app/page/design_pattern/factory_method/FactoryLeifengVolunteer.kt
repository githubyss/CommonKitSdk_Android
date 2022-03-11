package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.Leifeng
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.LeifengVolunteer


object FactoryLeifengVolunteer : IFactoryLeifeng {
    override fun create(): Leifeng {
        return LeifengVolunteer()
    }
}