package com.githubyss.mobile.common.kit.app.page.design_pattern.prototype

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.resume.Resume


fun prototype() {
    val resumeA: Resume = Resume("小A")
    resumeA.setPersonalInfo("男", "25")
    resumeA.setWorkExperience("1998-2005", "XX公司")
    val resumeB: Resume = resumeA.clone()
    resumeB.setWorkExperience("1998-2005","YY公司")
    val resumeC: Resume = resumeA.clone()
    resumeC.setPersonalInfo("男","30")
    resumeC.setWorkExperience("2004-2006","ZZ公司")
    resumeA.display()
    resumeB.display()
    resumeC.display()
    println()
}