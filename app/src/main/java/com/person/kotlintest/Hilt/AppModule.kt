package com.person.kotlintest.Hilt

import com.person.kotlintest.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @anthor tr
 * @date 2021/11/3
 * @desc
 */

//@Module
//@InstallIn(App::class)
//object AppModule {
//
//    @Singleton
//    @Provides
//    fun provideExecutor(): ExecutorService{
//        return ThreadPoolExecutor(5,39,1,TimeUnit.MINUTES,LinkedBlockingDeque(10000))
//    }
//
//    @Singleton
//    @Provides
//    fun getMyA(): String{
//        return "w s A"
//    }
//}