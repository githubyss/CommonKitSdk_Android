package com.githubyss.mobile.common.kit.util.formatter

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ComkitNumberFormatUtilsTest {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun formatConventionalNonNegativeInteger() {
        Assert.assertEquals("0", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("0"))
        Assert.assertEquals("0", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("00"))
        Assert.assertEquals("0", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("0000"))

        Assert.assertEquals("0", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("-0"))
        Assert.assertEquals("0", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("-00"))
        Assert.assertEquals("0", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("-0000"))

        Assert.assertEquals("1234", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("1234"))
        Assert.assertEquals("1234", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("01234"))
        Assert.assertEquals("1234", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("0001234"))

        Assert.assertEquals("1234", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("-1234"))
        Assert.assertEquals("1234", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("-01234"))
        Assert.assertEquals("1234", ComkitNumberFormatUtils.formatConventionalIntegerNonNegative("-0001234"))
    }
}
