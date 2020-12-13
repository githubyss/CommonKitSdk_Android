package com.githubyss.mobile.common.kit.converter

/**
 * ComkitTypeConverter
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitTypeConverter {
    private val types = arrayOf("int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte")

    fun <T : Any> object2String(`object`: T?): String {
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

    /**
     * ComkitTypeConverter.array2String(array)
     * <Description>
     * <Details>
     *
     * @param array
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun array2String(array: Array<*>): String {
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
     * ComkitTypeConverter.list2String(list)
     * <Description>
     * <Details>
     *
     * @param list
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun list2String(list: List<*>)
            = list.toString()
}