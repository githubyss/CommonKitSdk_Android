package com.githubyss.mobile.common.kit.app.design_pattern.entity.resume


data class Resume(private var name: String) : Cloneable {
    private var sex: String = ""
    private var age: String = ""

    private var workExperience: WorkExperience = WorkExperience()

    fun setPersonalInfo(sex: String, age: String) {
        this.sex = sex
        this.age = age
    }

    fun setWorkExperience(timeArea: String, company: String) {
        this.workExperience.timeArea = timeArea
        this.workExperience.company = company
    }

    fun display() {
        println("$name $age $sex 工作经历：${workExperience.timeArea} ${workExperience.company}")
    }

    public override fun clone(): Resume {
        val resume: Resume = super.clone() as Resume
        resume.workExperience = this.workExperience.clone()
        return resume
    }
}