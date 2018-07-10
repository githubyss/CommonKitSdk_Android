package com.githubyss.mobile.common.kit.checker

import com.githubyss.mobile.common.kit.processor.ComkitNumberProcessor
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ComkitNumberProcessorTest {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkInteger() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("-0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("-00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("-0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("1234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("01234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("0001234"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("-1234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("-01234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkInteger("-0001234"))
    }

    @Test
    fun checkIntegerZero() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerZero("0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerZero("00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerZero("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerZero("-0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerZero("-00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerZero("-0000"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerZero("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerZero("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerZero("0001234"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerZero("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerZero("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerZero("-0001234"))
    }

    @Test
    fun checkIntegerNonNegative() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("-0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("-00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("-0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("1234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("01234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonNegative("0001234"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerNonNegative("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerNonNegative("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerNonNegative("-0001234"))
    }

    @Test
    fun checkIntegerNonPositive() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("-0"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("-00"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("-0000"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerNonPositive("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerNonPositive("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkIntegerNonPositive("0001234"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("-1234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("-01234"))
        Assert.assertEquals(true, ComkitNumberProcessor.checkIntegerNonPositive("-0001234"))
    }


    @Test
    fun checkConventionalInteger() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalInteger("0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalInteger("-0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("-00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("-0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalInteger("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("0001234"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalInteger("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalInteger("-0001234"))
    }

    @Test
    fun checkConventionalIntegerZero() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerZero("0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerZero("-0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("-00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("-0000"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("0001234"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerZero("-0001234"))
    }

    @Test
    fun checkConventionalIntegerPositive() {
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("0000"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("-0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("-00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("-0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerPositive("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("0001234"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerPositive("-0001234"))
    }

    @Test
    fun checkConventionalIntegerNegative() {
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("0000"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("-0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("-00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("-0000"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("0001234"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerNegative("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNegative("-0001234"))
    }

    @Test
    fun checkConventionalIntegerNonNegative() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerNonNegative("0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerNonNegative("-0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("-00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("-0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerNonNegative("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("0001234"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonNegative("-0001234"))
    }

    @Test
    fun checkConventionalIntegerNonPositive() {
        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerNonPositive("0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("0000"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerNonPositive("-0"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("-00"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("-0000"))

        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("0001234"))

        Assert.assertEquals(true, ComkitNumberProcessor.checkConventionalIntegerNonPositive("-1234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("-01234"))
        Assert.assertEquals(false, ComkitNumberProcessor.checkConventionalIntegerNonPositive("-0001234"))
    }
}
