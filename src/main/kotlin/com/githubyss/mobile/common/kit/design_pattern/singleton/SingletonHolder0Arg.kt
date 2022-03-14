package com.githubyss.mobile.common.kit.design_pattern.singleton


/**
 * SingletonHolder0Arg
 * 无参数的单例模式封装
 * 把创建和初始化带无参数的单例的逻辑封装起来。并通过双重检查锁定算法实现逻辑的线程安全。
 *
 * 创建单例：
 * class SomeSingleton private constructor(context: Context) {
 *      companion object : SingletonHolder0Arg<SomeSingleton, Context>(::SomeSingleton)
 *
 *      init {
 *          // Init using context argument
 *          context.getString(R.string.app_name)
 *      }
 * }
 *
 * 使用单例：
 * SomeSingleton.getInstance(context)
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/14 17:20:04
 */
open class SingletonHolder0Arg<out P>(creator: () -> P) {

    private var creator: (() -> P)? = creator

    @Volatile
    private var instance: P? = null

    fun getInstance(): P {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val iS = instance
            if (iS != null) {
                iS
            }
            else {
                val created = creator!!()
                this.instance = created
                creator = null
                created
            }
        }
    }

    // 对上述方法的一种更简洁的写法
    fun getInstance2(): P {
        return instance ?: synchronized(this) {
            instance ?: creator!!().apply {
                instance = this
            }
        }
    }

    // lazy写法
    fun getInstance3(): P {
        val instance: P by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            creator!!()
        }
        return instance
    }
}