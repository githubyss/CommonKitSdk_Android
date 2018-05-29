package com.githubyss.mobile.common.kit.util

/**
 * ComkitTypeCastUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitTypeCastUtils {
    private val types = arrayOf("int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte")
    fun <T : Any> objectToString(`object`: T?): String {
        if (`object` == null) {
            return "Object{object is null}"
        }
        if (`object`.toString().startsWith(`object`.javaClass.name + "@")) {
            val stringBuilder = StringBuilder(`object`.javaClass.simpleName + " { ")
            val fields = `object`.javaClass.declaredFields
            for (field in fields) {
                field.isAccessible = true
                var flag = false
                for (type in types) {
                    if (field.type.name.equals(type, ignoreCase = true)) {
                        flag = true
                        var value: Any? = null
                        value = try {
                            field.get(`object`)
                        } catch (e: IllegalAccessException) {
                            e
                        } finally {
                            stringBuilder.append(String.format("%s=%s, ", field.name,
                                    if (value == null) "null" else value.toString()))
                            break
                        }
                    }
                }
                if (!flag) {
                    stringBuilder.append(String.format("%s=%s, ", field.name, "Object"))
                }
            }
            return stringBuilder.replace(stringBuilder.length - 2, stringBuilder.length - 1, " }").toString()
        } else {
            return `object`.toString()
        }
    }

    fun doubleArrayToFloatArray(doubleArray: Array<Double>): Array<Float> {
        val floatArray = Array(doubleArray.size, { 0.toFloat() })

        for (idx in 0 until doubleArray.size) {
            floatArray[idx] = doubleArray[idx].toFloat()
        }

        return floatArray
    }

    fun doubleArrayToShortArray(doubleArray: Array<Double>): Array<Short> {
        val shortArray = Array(doubleArray.size, { 0.toShort() })

        for (idx in 0 until doubleArray.size) {
            shortArray[idx] = doubleArray[idx].toShort()
        }

        return shortArray
    }

    fun doubleArrayToByteArray(doubleArray: Array<Double>): Array<Byte> {
        val byteArray = Array(doubleArray.size, { 0.toByte() })

        for (idx in 0 until doubleArray.size) {
            byteArray[idx] = doubleArray[idx].toByte()
        }

        return byteArray
    }

    /**
     * ComkitTypeCastUtils.arrayOfLongToString(array)
     * <Description>
     * <Details>
     *
     * @param array
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun arrayToString(array: Array<*>): String {
        val stringBuilder = StringBuilder()
        val arraySize = array.size
        for (idx in 0 until arraySize) {
            if (idx == 0) {
                stringBuilder.append("[")
            }
            stringBuilder.append(array[idx].toString())
            if (idx == (arraySize - 1)) {
                stringBuilder.append("]")
            } else {
                stringBuilder.append(", ")
            }
        }

        return stringBuilder.toString()
    }

    /**
     * ComkitTypeCastUtils.listToString(list)
     * <Description>
     * <Details>
     *
     * @param list
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun listToString(list: List<*>): String {
        return list.toString()
    }
}
