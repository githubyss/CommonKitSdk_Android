package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.Leifeng
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.LeifengUndergraduate


object FactoryLeifengUndergraduate : IFactoryLeifeng {
    override fun create(): Leifeng {
        return LeifengUndergraduate()
    }
}