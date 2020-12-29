package com.githubyss.mobile.common.kit.enumeration


class SexTypeTest {
    
    @SexTypeKotlin
    private var sex = SexTypeKotlin.MAN
    
    // 获取性别
    @SexTypeKotlin
    fun getSex(): Int {
        return sex
    }
    
    // 设置性别
    fun setSex(@SexTypeKotlin sex: Int) {
        this.sex = sex
    }
    
    fun main(args: Array<String>) {
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(SexTypeKotlin.MAN)
        // setSex(999)
    
        val resultSex: Int = getSex()
        println("resultSex: ${when (resultSex) {
            SexTypeKotlin.MAN   -> println("男")
            SexTypeKotlin.WOMEN -> println("女")
            else                -> println("未知")
        }}")
        // 输出：resultSex: 男
    }
}