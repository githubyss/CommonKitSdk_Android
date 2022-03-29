package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.exam


class ExamPaperA : ExamPaper() {
    override fun answer1(): String {
        return "b"
    }

    override fun answer2(): String {
        return "a"
    }

    override fun answer3(): String {
        return "c"
    }
}