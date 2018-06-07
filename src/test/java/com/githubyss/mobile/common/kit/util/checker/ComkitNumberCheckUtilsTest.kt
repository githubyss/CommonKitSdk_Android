package com.githubyss.mobile.common.kit.util.checker

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ComkitNumberCheckUtilsTest {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkInteger() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("-0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("-00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("-0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("1234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("01234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("0001234"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("-1234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("-01234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkInteger("-0001234"))
    }

    @Test
    fun checkIntegerZero() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerZero("0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerZero("00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerZero("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerZero("-0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerZero("-00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerZero("-0000"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerZero("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerZero("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerZero("0001234"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerZero("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerZero("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerZero("-0001234"))
    }

    @Test
    fun checkIntegerNonNegative() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("-0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("-00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("-0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("1234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("01234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonNegative("0001234"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerNonNegative("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerNonNegative("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerNonNegative("-0001234"))
    }

    @Test
    fun checkIntegerNonPositive() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("-0"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("-00"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("-0000"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerNonPositive("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerNonPositive("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkIntegerNonPositive("0001234"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("-1234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("-01234"))
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkIntegerNonPositive("-0001234"))
    }


    @Test
    fun checkConventionalInteger() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalInteger("0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalInteger("-0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("-00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("-0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalInteger("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("0001234"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalInteger("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalInteger("-0001234"))
    }

    @Test
    fun checkConventionalIntegerZero() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerZero("0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerZero("-0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("-00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("-0000"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("0001234"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerZero("-0001234"))
    }

    @Test
    fun checkConventionalIntegerPositive() {
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("0000"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("-0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("-00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("-0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerPositive("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("0001234"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerPositive("-0001234"))
    }

    @Test
    fun checkConventionalIntegerNegative() {
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("0000"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("-0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("-00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("-0000"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("0001234"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerNegative("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNegative("-0001234"))
    }

    @Test
    fun checkConventionalIntegerNonNegative() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("-0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("-00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("-0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("0001234"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonNegative("-0001234"))
    }

    @Test
    fun checkConventionalIntegerNonPositive() {
        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("0000"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("-0"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("-00"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("-0000"))

        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("0001234"))

        Assert.assertEquals(true, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("-1234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("-01234"))
        Assert.assertEquals(false, ComkitNumberCheckUtils.checkConventionalIntegerNonPositive("-0001234"))
    }
}
