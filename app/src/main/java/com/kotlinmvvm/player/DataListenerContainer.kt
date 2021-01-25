package com.kotlinmvvm.player

import android.os.Looper
import androidx.arch.core.executor.ArchTaskExecutor
import com.kotlinmvvm.App
import com.kotlinmvvm.lifecycle.*

/*
    数据容器，可以监听数据的变化
 */
class DataListenerContainer<T> {
    private val blocks = ArrayList<(T?) -> Unit>()
    private val viewLifeCycleProviders = hashMapOf<(T?) -> Unit, LifeCycleProvider>()

    var value: T? = null
        set(value) {
            //判断线程
            if (Looper.getMainLooper().thread === Thread.currentThread()) {
                blocks.forEach {
                    dispatchValue(it, value)
                }
            } else {
                App.handler.post {
                    blocks.forEach {
                        dispatchValue(it, value)
                    }
                }
            }
        }

    private fun dispatchValue(it: (T?) -> Unit, value: T?) {
        val viewModelProvider = viewLifeCycleProviders[it]
        viewModelProvider?.let { provider ->
            if (provider.isAtLeast(LifeState.START) &&
                provider.isAtMost(LifeState.STOP)
            ) {
                println("更新数据")
                it.invoke(value)
            }
        }
    }

    /*
        可能有多个View进行监听
        所有owner-block
     */
    fun addListener(owner: ILifeCycleOwner, valueObserver: (T?) -> Unit) {
        val lifeCycleProvider = owner.getLifeCycleProvider()
        viewLifeCycleProviders[valueObserver] = lifeCycleProvider
        //当View Destroy的时候从集合删除
        val observerWrapper = ValueObserverWrapper(valueObserver)
        lifeCycleProvider.addLifeListener(observerWrapper)
        if (!blocks.contains(valueObserver)) {
            blocks.add(valueObserver)
        }
    }

    inner class ValueObserverWrapper(private val valueObserver: (T?) -> Unit) :
        AbsLifeCycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            //当监听到View生命周期Destroy的时候就把provider移出
            if (state == LifeState.DESTROY) {
                viewLifeCycleProviders.remove(valueObserver)
            }
        }

    }
}