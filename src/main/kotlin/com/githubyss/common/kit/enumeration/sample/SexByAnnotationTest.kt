package com.githubyss.common.kit.enumeration.sample


class SexByAnnotationTest {

    @SexByAnnotation
    private var sex = SexByAnnotation.MAN

    @SexByAnnotation
    private var sex_ = ""

    // 获取性别
    @SexByAnnotation
    fun getSex(): String {
        return sex
    }

    // 设置性别
    fun setSex(@SexByAnnotation sex: String) {
        this.sex = sex
    }

    fun main(args: Array<String>) {
        println(args)
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(SexByAnnotation.MAN)
        setSex("")
        this.sex = ""

        when (val resultSex: String = getSex()) {
            SexByAnnotation.MAN -> println("MAN: $resultSex")
            SexByAnnotation.WOMEN -> println("WOMEN: $resultSex")
        }
        // 输出：resultSex: 男
    }
}
