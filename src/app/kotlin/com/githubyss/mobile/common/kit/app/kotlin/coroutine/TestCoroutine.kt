package com.githubyss.mobile.common.kit.app.kotlin.call

import com.githubyss.mobile.common.kit.util.currentTimeMillis


/**
 * TestCoroutine
 *
 * - Dispatchers.Default：
 * *默认的调度器，适合处理后台计算，是一个CPU密集型任务调度器。如果创建 Coroutine 的时候没有指定 dispatcher，则一般默认使用这个作为默认值。Default dispatcher 使用一个共享的后台线程池来运行里面的任务。注意它和IO共享线程池，只不过限制了最大并发数不同。*
 *
 * - Dispatchers.IO：
 * *顾名思义这是用来执行阻塞 IO 操作的，是和Default共用一个共享的线程池来执行里面的任务。根据同时运行的任务数量，在需要的时候会创建额外的线程，当任务执行完毕后会释放不需要的线程。*
 *
 * - Dispatchers.Unconfined：
 * *由于Dispatchers.Unconfined未定义线程池，所以执行的时候默认在启动线程。遇到第一个挂起点，之后由调用resume的线程决定恢复协程的线程。*
 *
 * - Dispatchers.Main：
 * *指定执行的线程是主线程，在Android上就是UI线程。*
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/18 17:17:09
 */
fun coroutine() {

}