package com.nourelhoudaeleuch.meteo.utils

import kotlinx.coroutines.*

//execute a suspending lazy deferred function
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        //start lazy
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}