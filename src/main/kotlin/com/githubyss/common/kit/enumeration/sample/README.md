# Android 中不使用枚举类（enum）替代为 @IntDef @StringDef

## 概述

Enum 是 java 中一种包含固定常数的类型。

当我们需要预先定义一些值，并限定范围时，使用 Enum，来做到编写和编译都查错。

- Java 的 Enum 的实质是特殊单例的静态成员变量；
- Enum 可以在编写器，编译器做到各种静态检查防呆；
- Enum 在运行期，所有枚举类作为单例，全部加载到内存中。

因为上述原因，Enum 增加了 APK 的内存占用，比常量多 5 到 10 倍的内存占用。

所以放弃枚举，就是关于安卓应用性能的内存占用部分的最佳实践方法之一。


## Android 单例类占用内存原理

单例增加了 APK 的内存占用的原因是：

- apk 经常初始化加载到虚拟机，作为一个新的用户应用来使用，而服务端 java 应用不怎么初始化加载到虚拟机；
- 另外每个 Android 应用的可用内存是非常有限的。

因为 Android 系统下，每个 APP 就是一个独立用户，Android 系统分配给这个用户的内存非常小，一般只有 32MB 64MB 左右。

这个配置一般在文件 /system/build.prop 中。

具体看 ROM 厂商的定制，别希望 ROM 厂商修改这个的大小。

- 初始化单例太多，设备在不能处理情况下，非常容易出现 ANR 错误；
- AndroidManifest.xml 中 application 节点下 android:largeHeap 开启也不会给太多额外的内存。

/system/build.prop 中

```
dalvik.vm.heapsize=128m
dalvik.vm.heapgrowthlimit=64m
```

- heapgrowthlimit 是普通应用的内存限制
- heapsize 是开启后的内存限制，也没大多少


## 为什么要使用枚举

#### 举一个例子，例如我们要为一个 bean。

赋值一个 person 的性别属性，因为性别只有男女，所以我们通常的做法是定义两个整型 int，来区分男女性别。

```
class SexTest {

    private val MAN = 101
    private val WOMEN = 102
    
    private var sex = 0

    // 获取性别
    fun getSex(): Int {
        return sex
    }

    // 设置性别
    fun setSex(sex: Int) {
        this.sex = sex
    }

    fun main(args: Array<String>) {
        setSex(101)
    
        val resultSex: Int = getSex()
        println("resultSex: ${when (resultSex) {
            MAN   -> println("男")
            WOMEN -> println("女")
            else  -> println("未知")
        }}")
        // 输出：resultSex: 男
    }
}
```

由上面的例子可以看出：

当我们定义了一个男女的 final 整型作为入参时，不一定保证入参的都是我们想要的入参。
这里就有一个“类型不安全”的问题出现，而枚举就可以解决这个问题。

#### 首先定义一个枚举类，里面有男、女两个枚举常量

```
enum class Sex {
    MAN,
    WOMEN
}

class SexTest {

    private var sex: Sex? = null

    // 获取性别
    fun getSex(): Int {
        return sex
    }

    // 设置性别
    fun setSex(sex: Int) {
        this.sex = sex
    }
    
    fun main(args: Array<String>) {
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(Sex.MAN)
        
        val resultSex: Int = getSex()
        println("resultSex: ${when (resultSex) {
            MAN   -> println("男")
            WOMEN -> println("女")
            else  -> println("未知")
        }}")
        // 输出：resultSex: 男
    }
}
```

- 利用枚举，在 setSex() 方法里面对入参做了枚举 Sex 的限制；
- 对于想输入任何非枚举类 Sex 里面定义的枚举常量，编译都是不能通过的；
- 这就很好的限制了入参混乱的问题。


## 使用 Enum 的缺点

- 每一个枚举值都是一个单例对象,在使用它时会增加额外的内存消耗,所以枚举相比与 Integer 和 String 会占用更多的内存；
- 较多的使用 Enum 会增加 DEX 文件的大小,会造成运行时更多的 IO 开销,使我们的应用需要更多的空间；
- 特别是分 dex 多的大型 APP，枚举的初始化很容易导致 ANR。


## 不使用枚举类型的解决方案

既然是因为参数的类型太泛了造成的类型不安全，那么我只要将参数限定在某一个类型集合里面，使用注解来进行限定参数。

- Java：@IntDef/@StringDef + @interface
- Kotlin：@IntDef/@StringDef + annotation

build.gradle 文件中添加依赖：

```
dependencies {
    api "androidx.appcompat:appcompat:1.2.0"
}
```

然后就可以使用注解帮助检查参数，代码如下：

Kotlin 写法

```
/** 只能使用 {@link #MAN} {@link #WOMEN} */
// 开启Doc文档
@MustBeDocumented
// 限定为 MAN、WOMEN
@IntDef(SexTypeKotlin.MAN, SexTypeKotlin.WOMEN)
// 注解作用范围，参数注解，成员注解，方法注解
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
// 注解所存活的时间，在运行时，而不会存在 .class 文件中
@Retention(AnnotationRetention.SOURCE)
annotation class SexTypeKotlin {
    // 接口，定义新的注解类型
    companion object {
        const val MAN = 0x02
        const val WOMEN = 0x03
    }
}

class SexTypeTest {
    
    @SexTypeKotlin
    private var sex = SexTypeKotlin.MAN
    
    // 获取性别
    @SexTypeKotlin
    fun getSex(): Int {
        return sex
    }
    
    // 设置性别
    fun setSex(@SexTypeKotlin sex: Int) {
        this.sex = sex
    }
    
    fun main(args: Array<String>) {
        // 这里的入参必须为 Sex 枚举类中的其中一个枚举常量
        // 绝对不允许输入没有再 Sex 枚举里面定义的常量
        setSex(SexTypeKotlin.MAN)
        
        val resultSex: Int = getSex()
        println("resultSex: ${when (resultSex) {
            SexTypeKotlin.MAN   -> println("男")
            SexTypeKotlin.WOMEN -> println("女")
            else                -> println("未知")
        }}")
        // 输出：resultSex: 男
    }
}
```

Java 写法

```
@Documented
@IntDef({SexTypeJava.MAN, SexTypeJava.WOMEN})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SexTypeJava {
    final int MAN = 0x02;
    final int WOMEN = 0x03;
}
```

如果我们尝试在调用 setSex() 方法的时候，传入不在限定之内的值，那么build就不会通过，有错误提示。但是如果是运行安装的话，是不会报错的。

目前 Kotlin 写法也不会报错，
