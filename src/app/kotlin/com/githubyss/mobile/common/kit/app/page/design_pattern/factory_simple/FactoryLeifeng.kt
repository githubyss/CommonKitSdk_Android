package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.Leifeng
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.LeifengType
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.LeifengUndergraduate
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.LeifengVolunteer


object FactoryLeifeng {
    fun create(type: LeifengType): Leifeng {
        return when (type) {
            LeifengType.UNDERGRADUATE -> LeifengUndergraduate()
            LeifengType.VOLUNTEER -> LeifengVolunteer()
        }
    }
}