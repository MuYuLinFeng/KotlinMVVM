package com.kotlinmvvm.lifecycle

interface ILifeCycleOwner {
    fun getLifeCycleProvider(): LifeCycleProvider
}