package com.githubyss.mobile.common.kit.processor

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
    fun formatConventionalNonNegativeInteger() {
        Assert.assertEquals("0", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("0"))
        Assert.assertEquals("0", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("00"))
        Assert.assertEquals("0", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("0000"))

        Assert.assertEquals("0", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("-0"))
        Assert.assertEquals("0", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("-00"))
        Assert.assertEquals("0", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("-0000"))

        Assert.assertEquals("1234", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("1234"))
        Assert.assertEquals("1234", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("01234"))
        Assert.assertEquals("1234", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("0001234"))

        Assert.assertEquals("1234", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("-1234"))
        Assert.assertEquals("1234", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("-01234"))
        Assert.assertEquals("1234", ComkitNumberProcessor.string2ConventionalIntegerNonNegative("-0001234"))
    }
}
