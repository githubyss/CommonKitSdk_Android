package com.githubyss.mobile.common.kit.design_pattern.singleton


/**
 * SingletonHolder2Arg
 * 双参数的单例模式封装
 * 把创建和初始化带双参数的单例的逻辑封装起来。并通过双重检查锁定算法实现逻辑的线程安全。
 *
 * 创建单例：
 * class SomeSingleton private constructor(context: Context, view: View) {
 *      companion object : SingletonHolder2Arg<SomeSingleton, Context, View>(::SomeSingleton)
 *
 *      init {
 *          // Init using context argument
 *          context.getString(R.string.app_name)
 *      }
 * }
 *
 * 使用单例：
 * SomeSingleton.getInstance(context, view)
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/14 17:12:56
 */
open class SingletonHolder2Arg<out P, in C, in D>(creator: (C, D) -> P) {

    private var creator: ((C, D) -> P)? = creator

    @Volatile
    private var instance: P? = null

    fun getInstance(argC: C, argD: D): P {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            }
            else {
                val created = creator!!(argC, argD)
                instance = created
                creator = null
                created
            }
        }
    }

    // 对上述方法的一种更简洁的写法
    fun getInstance2(argC: C, argD: D): P = instance ?: synchronized(this) {
        instance ?: creator!!(argC, argD).apply {
            instance = this
        }
    }

    // lazy写法
    fun getInstance3(argC: C, argD: D): P {
        val instance: P by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            creator!!(argC, argD)
        }
        return instance
    }
}
