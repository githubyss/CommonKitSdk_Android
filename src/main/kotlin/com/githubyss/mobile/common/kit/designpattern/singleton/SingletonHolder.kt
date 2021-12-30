package com.githubyss.mobile.common.kit.designpattern.singleton


/**
 * SingletonHolder
 * 含参数的单例模式封装
 * 把创建和初始化带有参数的单例的逻辑封装起来。并通过双重检查锁定算法实现逻辑的线程安全。
 *
 * 创建单例：
 * class SomeSingleton private constructor(context: Context) {
 *      init {
 *          // Init using context argument
 *          context.getString(R.string.app_name)
 *      }
 *
 *      companion object : SingletonHolder<SomeSingleton, Context>(::SomeSingleton)
 * }
 *
 * 使用单例：
 * SomeSingleton.getInstance(context)
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/22 10:29:55
 */
open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    
    private var creator: ((A) -> T)? = creator
    
    @Volatile
    private var instance: T? = null
    
    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }
        
        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
    
    // 对上述方法的一种更简洁的写法
    fun getInstance2(arg: A): T = instance ?: synchronized(this) {
        instance ?: creator!!(arg).apply {
            instance = this
        }
    }
}
