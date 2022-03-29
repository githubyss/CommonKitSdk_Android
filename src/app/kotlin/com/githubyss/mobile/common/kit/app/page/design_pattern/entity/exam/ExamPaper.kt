package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.exam


abstract class ExamPaper {
    fun question1() {
        println("杨过得到，后来给了郭靖，炼成倚天剑、屠龙刀的玄铁可能是（）")
        println("a.球墨铸铁 b.马口铁 c.高速合金钢 d.碳素纤维")
        println("答案：${answer1()}")
        println()
    }

    fun question2() {
        println("杨过、程、陆无双铲除了情花，造成（）")
        println("a.使这种植物不再害人 b.使一种珍惜物种灭绝 c.破坏了那个生物圈的生态平衡 d.造成该地区沙漠化")
        println("答案：${answer2()}")
        println()
    }

    fun question3() {
        println("蓝凤凰致使华山师徒、桃谷六仙呕吐不止，如果你是大夫，会给他们开什么药（）")
        println("a.阿司匹林 b.牛黄解毒丸 c.氟哌酸 d.让他们喝大量的生牛奶 e.以上全不对")
        println("答案：${answer3()}")
        println()
    }

    abstract fun answer1(): String
    abstract fun answer2(): String
    abstract fun answer3(): String
}