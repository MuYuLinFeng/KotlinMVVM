package com.kotlinmvvm.lifecycle

/*
    管理注册进来的接口
    保存当前View的生命周期状态
 */
class LifeCycleProvider {
    private var currentState: LifeState = LifeState.DESTROY
    private val lifecycleListener = arrayListOf<AbsLifeCycle>()
    fun addLifeListener(listener: AbsLifeCycle) {
        if (!lifecycleListener.contains(listener)) {
            lifecycleListener.add(listener)
        }
    }

    fun removeLifeListener(listener: AbsLifeCycle) {
        if (lifecycleListener.contains(listener)) {
            lifecycleListener.remove(listener)
        }
    }

    fun makeLifeState(state: LifeState) {
        lifecycleListener.forEach {
            it.onViewLifeStateChange(state)
        }
        when (state) {
            LifeState.CREATE -> {
                dispatchCreateState()
            }
            LifeState.START -> {
                dispatchStartState()
            }
            LifeState.RESUME -> {
                dispatchResumeState()
            }
            LifeState.PAUSE -> {
                dispatchPauseState()
            }
            LifeState.STOP -> {
                dispatchStopState()
            }
            LifeState.DESTROY -> {
                dispatchDestroyState()
            }

        }
    }


    private fun dispatchCreateState() {
        lifecycleListener.forEach { it.onCreate() }
    }

    private fun dispatchStartState() {
        lifecycleListener.forEach { it.onStart() }
    }

    private fun dispatchResumeState() {
        lifecycleListener.forEach { it.onResume() }
    }

    private fun dispatchPauseState() {
        lifecycleListener.forEach { it.onPause() }
    }

    private fun dispatchStopState() {
        lifecycleListener.forEach { it.onStop() }
    }

    private fun dispatchDestroyState() {
        lifecycleListener.forEach { it.onDestroy() }
        lifecycleListener.clear()
    }

    fun isAtLeast(state: LifeState): Boolean {
        println("current state $currentState ===> $state")
        return currentState > state
    }

    fun isAtMost(state: LifeState): Boolean {
        println("current state $currentState ===> $state")
        return currentState < state
    }
}