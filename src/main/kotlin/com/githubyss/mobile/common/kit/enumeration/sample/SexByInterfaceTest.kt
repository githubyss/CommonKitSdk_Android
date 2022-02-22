package com.githubyss.mobile.common.kit.enumeration.sample


class SexByInterfaceTest {

    @SexByInterface
    private var sex: String = SexByInterface.MAN

    @SexByInterface
    private var sex_: String = ""

    // 获取性别
    @SexByInterface
    fun getSex(): String {
        return sex
    }

    // 设置性别
    fun setSex(@SexByInterface sex: String) {
        this.sex = sex
    }

    fun main(args: Array<String>) {
        println(args)
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(SexByInterface.MAN)
        setSex("")
        this.sex = ""

        when (val resultSex: String = getSex()) {
            SexByInterface.MAN -> println("MAN: $resultSex")
            SexByInterface.WOMEN -> println("WOMEN: $resultSex")
        }
        // 输出：resultSex: 男
    }
}
