package com.kotlinmvvm.lifecycle

abstract class AbsLifeCycle : ILifeCycle {
    open fun onCreate() {
    }

    open fun onStart() {
    }

    open fun onResume() {
    }

    open fun onPause() {
    }

    open fun onStop() {
    }

    open fun onDestroy() {
    }
}