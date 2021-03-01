package com.githubyss.mobile.common.kit.enumeration


class SexTypeTest {
    
    @SexType
    private var sex: Int = SexType.MAN
    
    // 获取性别
    @SexType
    fun getSex(): Int {
        return sex
    }
    
    // 设置性别
    fun setSex(@SexType sex: Int) {
        this.sex = sex
    }
    
    fun main(args: Array<String>) {
        println(args)
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(SexType.MAN)
        setSex(-1)
        this.sex = -1
        
        val resultSex: Int = getSex()
        println("resultSex: ${when (resultSex) {
            SexType.MAN -> println("男")
            SexType.WOMEN -> println("女")
            else -> println("未知")
        }}")
        // 输出：resultSex: 男
    }
}