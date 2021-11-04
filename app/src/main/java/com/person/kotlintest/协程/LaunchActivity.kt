//package com.person.kotlintest.协程
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.person.kotlintest.R
//import kotlinx.android.synthetic.main.activity_launch.*
//import kotlinx.coroutines.*
//
//class LaunchActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_launch)
//    }
//
//    private fun getdata() {
//        /**
//         * 使用 runBlocking 顶层函数
//         * 通常适用于单元测试的场景，而业务开发中不会用到这种方法，因为它是线程阻塞的
//         */
//        runBlocking {
//
//        }
//
//        /**
//         * 使用 GlobalScope 单例对象
//         * 使用 runBlocking 的区别在于不会阻塞线程。
//         * 但在 Android 开发中同样不推荐这种用法，因为它的生命周期会和 app 一致，
//         * 且不能取消（什么是协程的取消后面的文章会讲）。
//         */
//        GlobalScope.launch {
//
//        }
//
//        /**
//         * 自行通过 CoroutineContext 创建一个 CoroutineScope 对象
//         * 需要一个类型为 CoroutineContext 的参数
//         * 是比较推荐的使用方法，我们可以通过 context 参数去管理和控制协程的生命周期
//         * （这里的 context 和 Android 里的不是一个东西，是一个更通用的概念，会有一个 Android 平台的封装来配合使用）。
//         */
//        val coroutineScope = CoroutineScope(Dispatchers.Main)
//        coroutineScope.launch {
//
//        }
//        coroutineScope.launch(Dispatchers.IO) {
//            withContext(Dispatchers.Main) {}
//            withContext(Dispatchers.IO) {}
//            withContext(Dispatchers.Main) {}
//        }
//        coroutineScope.launch(Dispatchers.Main) {
//            val iamge = getImage()
//            showImage()
//        }
//
//
//        /**
//         * 多层网络请求
//         */
//        coroutineScope.launch(Dispatchers.Main) {       // 开始协程：主线程
//            val token = api.getToken()                  // 网络请求：IO 线程
//            val user = api.getUser(token)               // 网络请求：IO 线程
//            nameTv.text = user.name                     // 更新 UI：主线程
//        }
//
//        /**
//         * 多个网络请求之后一起show
//         * 嵌套加深，强行串行，时间加倍
//         */
//        api.getAvatar(user) { avatar ->
//            api.getCompanyLogo(user) { logo ->
//                show(merge(avatar, logo))
//            }
//        }
//
//        /**
//         * async
//         * 合并结果
//         */
//        coroutineScope.launch(Dispatchers.Main) {
//            val avatar = async { getImage() }    // 获取用户头像
//            val logo = async { getImage() } // 获取用户所在公司的 logo
//            val merged = suspendingMerge(avatar, logo)    // 合并结果
//            //                  👆
//            show(merged) // 更新 UI
//            show(avatar.await(), logo.await()) // 更新 UI
//        }
//
//
//    }
//
//    suspend fun getImage() = withContext(Dispatchers.IO) {
//
//    }
//
//    /**
//     * 协程的挂起 == 切个线程   并不是结束
//     * 从当前线程切个线程去做协程闭包里面的代码，执行结束后再切线程回来，内部也是post了一个runnable
//     * 这也就解释了为什么挂起函数要写在协程或者其他挂起函数里面
//     */
//
//    /**
//     * 证明了suspend并不能挂起函数，没有给它指派线程
//     * suspend只是一个提醒作用，让开发者知道这是个耗时线程
//     * 还需要函数内部直接或者间接的调用协程的suspend函数
//     */
//    suspend fun suspendingPrint() {
//        //I/System.out: Thread: main
//        println("Thread: ${Thread.currentThread().name}")
//    }
//
//
//
//}