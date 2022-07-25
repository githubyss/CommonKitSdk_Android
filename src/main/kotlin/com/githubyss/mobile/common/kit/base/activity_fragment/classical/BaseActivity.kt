package com.githubyss.mobile.common.kit.base.activity_fragment.classical

import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.logD


/**
 * BaseActivity
 *
 * Activity 的四个状态：Running-Paused-Stopped-Killed。
 * - Running -> 当前显示在屏幕的 Activity (位于任务栈的顶部)，用户可见状态。
 * - Paused -> 依旧在用户可见状态，但是界面焦点已经失去，此 Activity 无法与用户进行交互。
 * - Stopped -> 用户看不到当前界面，也无法与用户进行交互，完全被覆盖。
 * - Killed -> 当前界面被销毁，等待被系统回收。
 *
 * 状态转变和对应的生命周期。
 * - Starting -> Running 所执行的生命周期顺序 onCreate() -> onStart() -> onResume()，此时 Activity 所处于任务栈的 top，可以与用户进行交互。
 * - Running -> Paused 所执行的生命周期为 onPause()，该 Activity 已失去了焦点但仍然是可见的状态(包括部分可见)。
 * - Paused -> Running 所执行的生命周期为 onResume()，当前 Activity 重新回到活动状态 (Running)，此情况发生在用户操作 Home 键，然后重新回到当前 Activity 界面。
 * - Paused -> Stopped 所执行的生命周期为 onStop()，该 Activity 被另一个 Activity 完全覆盖的状态，该 Activity 变得不可见，所以系统经常会由于内存不足而将该 Activity 强行结束。
 * - Stopped -> Killed 所执行的生命周期为 onDestroy()，该 Activity 被系统销毁。当一个 Activity 处于 Paused 状态或 Stopped 状态时就随处可能进入 Killed 状态，因为系统可能因内存不足而强行结束该 Activity。
 *
 * 还有一种情况由于系统内存不足可能在 Paused 状态中直接被系统杀死达到 Killed 状态。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 15:10:38
 */
abstract class BaseActivity(@LayoutRes layoutId: Int = 0) : AppCompatActivity(layoutId) {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = BaseActivity::class.java.simpleName

        @IdRes
        val FRAGMENT_BASE_CONTAINER_ID = R.id.layout_fragment_base_container

        @IdRes
        val FRAGMENT_BASE_TOOLBAR_CONTAINER_ID = R.id.layout_fragment_base_toolbar_container
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private var activityName = this::class.java.simpleName


    /** ****************************** Constructors ****************************** */

    /**  */
    init {
        logD(TAG, "Constructor init")
    }


    /** ****************************** Open ****************************** */

    /** 初始化 UI */
    open fun setupUi() {}

    /** 初始化数据 */
    open fun setupData() {}


    /** ****************************** Override ****************************** */

    /**
     * 点击 Activity 的时候，系统会调用 Activity 的 onCreate() 方法，在这个方法中我们会初始化当前布局 setContentView() 方法。
     * 不可见。
     *
     * @param
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.let {
            it.registerFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle, true)
        }

        val message = "${this::class.java.simpleName} > onCreate"
        logD(TAG, message)

        setupUi()
        setupData()
    }

    /**
     * SingleTop 模式下，启动栈顶的同一个 Activity 会走此生命周期方法。
     *
     * @param
     * @return
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val message = "$activityName > onNewIntent"
        logD(TAG, message)
    }

    /**
     * onCreate() 方法完成后，此时 Activity 进入 onStart() 方法，当前 Activity 是用户可见状态，但没有焦点，与用户不能交互，一般可在当前方法做一些动画的初始化操作。
     * 关键词：可见、不可交互
     *
     * @param
     * @return
     */
    override fun onStart() {
        super.onStart()
        val message = "$activityName > onStart"
        logD(TAG, message)
    }

    /**
     * onRestart() 方法在用户按下 Home 之后，再次进入到当前 Activity 的时候调用。
     * 调用顺序 onPause()->onStop()->onRestart()->onStart()->onResume().
     *
     * @param
     * @return
     */
    override fun onRestart() {
        super.onRestart()
        val message = "$activityName > onRestart"
        logD(TAG, message)
    }

    /**
     * onStart() 方法完成之后，此时 Activity 进入 onResume() 方法中，当前 Activity 状态属于运行状态 (Running)，可与用户进行交互。
     * 关键词：Running，可见、可交互
     *
     * @param
     * @return
     */
    override fun onResume() {
        super.onResume()
        val message = "$activityName > onResume"
        logD(TAG, message)
    }

    /**
     * 当另外一个 Activity 覆盖当前的 Activity 时，此时当前 Activity 会进入到 onPause() 方法中，当前 Activity 是可见的，但不能与用户交互状态。
     * 关键词：可见、不可交互
     *
     * @param
     * @return
     */
    override fun onPause() {
        super.onPause()
        val message = "$activityName > onPause"
        logD(TAG, message)
    }

    /**
     * onPause() 方法完成之后，此时 Activity 进入 onStop() 方法，此时 Activity 对用户是不可见的，在系统内存紧张的情况下，有可能会被系统进行回收。所以一般在当前方法可做资源回收。
     * 关键词：不可见
     *
     * @param
     * @return
     */
    override fun onStop() {
        super.onStop()
        val message = "$activityName > onStop"
        logD(TAG, message)
    }

    /**
     * 调用时机：
     * - Activity 被销毁的时候调用，也可能没有销毁就调用了；
     * - 按下 Home 键，Activity 进入了后台，此时会调用该方法；
     * - 按下电源键，屏幕关闭，Activity 进入后台；
     * - 启动其它 Activity，Activity 被压入了任务栈的栈底；
     * - 横竖屏切换，会销毁当前 Activity 并重新创建。
     *
     * 注意事项：
     * - 用户主动销毁不会调用：当用户点击 Back 键或者调用了 finish() 方法，不会调用该方法；
     * - 调用时机不固定：该方法一定是在 onStop() 方法之前调用，但是不确定是在 onPause() 方法之前还是之后调用；
     * - 布局中组件状态存储：每个组件都实现了 onSaveInstance() 方法，在调用函数的时候，会自动保存组件的状态，注意，只有有 id 的组件才会保存；
     * - 关于默认的 super.onSaveInstanceState(outState)：该默认的方法是实现组件状态保存的。
     *
     * @param outState 数据保存。Activity 生命周期结束的时候, 需要保存 Activity 状态的时候, 会将要保存的数据使用键值对的形式保存在 Bundle 对象中。
     * @return
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val message = "$activityName > onSaveInstanceState"
        logD(TAG, message)
    }

    /**
     * 方法回调时机：在 Activity 被系统销毁之后，恢复 Activity 时被调用，只有销毁了之后重建的时候才调用，如果内存充足，系统没有销毁这个 Activity，就不需要调用。
     * Bundle 对象传递：该方法保存的 Bundle 对象在 Activity 恢复的时候也会通过参数传递到 onCreate() 方法中。
     *
     * @param savedInstanceState
     * @return
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val message = "$activityName > onRestoreInstanceState"
        logD(TAG, message)
    }

    /**
     * onStop() 方法完成之后，此时 Activity 进入到 onDestroy() 方法中，结束当前 Activity。
     *
     * @param
     * @return
     */
    override fun onDestroy() {
        val message = "$activityName > onDestroy"
        logD(TAG, message)

        supportFragmentManager.let {
            it.unregisterFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle)
        }
        super.onDestroy()
    }


    /** ****************************** Functions ****************************** */

    /** Switch fragment to activity. */
    protected fun switchFragment(fragment: Fragment, fragmentTag: String? = null, @IdRes containerId: Int, addToBackStack: Boolean = true) {
        FragmentUtils.switchFragmentByAddHideShow(fragment, fragmentTag, null, supportFragmentManager, containerId, addToBackStack, intent.extras)
    }
}
