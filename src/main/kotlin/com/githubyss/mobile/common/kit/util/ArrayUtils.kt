package com.githubyss.mobile.common.kit.util


/**
 * ArrayUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 17:29:37
 */
object ArrayUtils {
    
    /** ****************************** Properties ****************************** */
    
    private val TAG: String = ArrayUtils::class.java.simpleName
    
    
    /** ****************************** Functions ****************************** */
    
    /** ******************** Checker ******************** */
    
    /**
     * <Description> Return whether the array is null or 0-length.
     * <Details>
     *
     * @param array The array.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(array: Array<*>?): Boolean {
        array ?: return true
        
        return array.isEmpty()
    }
    
    /**
     * <Description> Return whether the bytes is null or 0-length.
     * <Details>
     *
     * @param bytes The bytes.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(bytes: ByteArray?): Boolean {
        bytes ?: return true
        
        return bytes.isEmpty()
    }
    
    /**
     * <Description> Return whether the chars is null or 0-length.
     * <Details>
     *
     * @param chars The chars.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(chars: CharArray?): Boolean {
        chars ?: return true
        
        return chars.isEmpty()
    }
    
    /**
     * <Description> Return whether the shorts is null or 0-length.
     * <Details>
     *
     * @param shorts The shorts.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(shorts: ShortArray?): Boolean {
        shorts ?: return true
        
        return shorts.isEmpty()
    }
    
    /**
     * <Description> Return whether the ints is null or 0-length.
     * <Details>
     *
     * @param ints The ints.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(ints: IntArray?): Boolean {
        ints ?: return true
        
        return ints.isEmpty()
    }
    
    /**
     * <Description> Return whether the longs is null or 0-length.
     * <Details>
     *
     * @param longs The longs.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(longs: LongArray?): Boolean {
        longs ?: return true
        
        return longs.isEmpty()
    }
    
    /**
     * <Description> Return whether the floats is null or 0-length.
     * <Details>
     *
     * @param floats The floats.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(floats: FloatArray?): Boolean {
        floats ?: return true
        
        return floats.isEmpty()
    }
    
    /**
     * <Description> Return whether the doubles is null or 0-length.
     * <Details>
     *
     * @param doubles The doubles.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(doubles: DoubleArray?): Boolean {
        doubles ?: return true
        
        return doubles.isEmpty()
    }
    
    /**
     * <Description> Return whether the booleans is null or 0-length.
     * <Details>
     *
     * @param booleans The booleans.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(booleans: BooleanArray?): Boolean {
        booleans ?: return true
        
        return booleans.isEmpty()
    }
}
