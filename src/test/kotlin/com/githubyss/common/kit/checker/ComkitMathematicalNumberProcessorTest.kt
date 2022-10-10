// package com.githubyss.common.kit.checker
//
// import com.githubyss.common.kit.processor.MathematicalNumberProcessor
// import org.junit.After
// import org.junit.Assert
// import org.junit.Before
// import org.junit.Test
//
// class ComkitMathematicalNumberProcessorTest {
//     @Before
//     fun setUp() {
//     }
//
//     @After
//     fun tearDown() {
//     }
//
//     @Test
//     fun formatConventionalNonNegativeInteger() {
//         Assert.assertEquals("0", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("0"))
//         Assert.assertEquals("0", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("00"))
//         Assert.assertEquals("0", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("0000"))
//
//         Assert.assertEquals("0", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("-0"))
//         Assert.assertEquals("0", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("-00"))
//         Assert.assertEquals("0", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("-0000"))
//
//         Assert.assertEquals("1234", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("1234"))
//         Assert.assertEquals("1234", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("01234"))
//         Assert.assertEquals("1234", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("0001234"))
//
//         Assert.assertEquals("1234", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("-1234"))
//         Assert.assertEquals("1234", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("-01234"))
//         Assert.assertEquals("1234", ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative("-0001234"))
//     }
//
//     @Test
//     fun checkInteger() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("-0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("-00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("-0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("1234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("01234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("0001234"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("-1234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("-01234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkInteger("-0001234"))
//     }
//
//     @Test
//     fun checkIntegerZero() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerZero("0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerZero("00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerZero("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerZero("-0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerZero("-00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerZero("-0000"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerZero("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerZero("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerZero("0001234"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerZero("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerZero("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerZero("-0001234"))
//     }
//
//     @Test
//     fun checkIntegerNonNegative() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("-0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("-00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("-0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("1234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("01234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("0001234"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerNonNegative("-0001234"))
//     }
//
//     @Test
//     fun checkIntegerNonPositive() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("-0"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("-00"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("-0000"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("0001234"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("-1234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("-01234"))
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkIntegerNonPositive("-0001234"))
//     }
//
//
//     @Test
//     fun checkConventionalInteger() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalInteger("0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalInteger("-0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("-00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("-0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalInteger("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("0001234"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalInteger("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalInteger("-0001234"))
//     }
//
//     @Test
//     fun checkConventionalIntegerZero() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("-0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("-00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("-0000"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("0001234"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerZero("-0001234"))
//     }
//
//     @Test
//     fun checkConventionalIntegerPositive() {
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("0000"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("-0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("-00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("-0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("0001234"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerPositive("-0001234"))
//     }
//
//     @Test
//     fun checkConventionalIntegerNegative() {
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("0000"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("-0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("-00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("-0000"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("0001234"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNegative("-0001234"))
//     }
//
//     @Test
//     fun checkConventionalIntegerNonNegative() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("-0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("-00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("-0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("0001234"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonNegative("-0001234"))
//     }
//
//     @Test
//     fun checkConventionalIntegerNonPositive() {
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("0000"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("-0"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("-00"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("-0000"))
//
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("0001234"))
//
//         Assert.assertEquals(true, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("-1234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("-01234"))
//         Assert.assertEquals(false, ComkitMathematicalNumberProcessor.checkConventionalIntegerNonPositive("-0001234"))
//     }
// }
