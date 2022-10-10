package com.githubyss.common.kit.enumeration.sample


class SexBySealedTest {

    private var sex: SexBySealed = SexBySealed.MAN
    // private var sex_: SexBySealed = ""

    // 获取性别
    fun getSex(): SexBySealed {
        return sex
    }

    // 设置性别
    fun setSex(sex: SexBySealed) {
        this.sex = sex
    }

    fun main(args: Array<String>) {
        println(args)
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(SexBySealed.MAN)
        // setSex("")
        // this.sex = ""

        when (val resultSex: SexBySealed = getSex()) {
            SexBySealed.MAN -> println("MAN: $resultSex")
            SexBySealed.WOMEN -> println("WOMEN: $resultSex")
        }
        // 输出：resultSex: 男
    }
}
