package com.githubyss.common.kit.enumeration.sample


class SexByEnumTest {

    private var sex: SexByEnum = SexByEnum.MAN
    // private var sex_: SexByEnum = ""

    // 获取性别
    fun getSex(): SexByEnum {
        return sex
    }

    // 设置性别
    fun setSex(sex: SexByEnum) {
        this.sex = sex
    }

    fun main(args: Array<String>) {
        println(args)
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(SexByEnum.MAN)
        // setSex("")
        // this.sex = ""

        when (val resultSex: SexByEnum = getSex()) {
            SexByEnum.MAN -> println("MAN: $resultSex")
            SexByEnum.WOMEN -> println("WOMEN: $resultSex")
        }
        // 输出：resultSex: 男
    }
}
