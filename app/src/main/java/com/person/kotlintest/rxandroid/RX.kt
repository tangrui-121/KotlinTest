package com.person.kotlintest.rxandroid

import com.person.kotlintest.dsl.KDate.ago
import com.person.kotlintest.dsl.KDate.days
import com.person.kotlintest.dsl.KDate.months
import com.person.kotlintest.dsl.fromNow
import com.person.kotlintest.log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @anthor tr
 * @date 2021/5/12
 * @desc https://www.jianshu.com/p/5d761e283f24
 */
object RX {

    fun textRX(){
        val yesterday = 1.days.ago // 也可以这样写： val yesterday = 1 days ago
        val twoMonthsLater = 2 months fromNow

        val observable: Observable<String> =
            Observable.create(ObservableOnSubscribe<String> { emitter ->
                emitter.onNext("A")
                emitter.onNext("B")
                emitter.onNext("C")
                emitter.onComplete()
            })
        val observer: Observer<String> = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                "onSubscribe".log()
            }

            override fun onNext(s: String) {
                "onNext :$s".log()
            }

            override fun onError(e: Throwable) {
                "onError: ${e.message}".log()
            }

            override fun onComplete() {
                "onComplete".log()
            }
        }
        observable.subscribe(observer)
    }
}