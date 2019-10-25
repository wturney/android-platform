package com.wtl.base.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wtl.base.ui.Resource

/**
 * MutableLiveData implementation that disallows null values. Provides non-null aware observe method a well
 */
class NonNullMutableLiveData<T : Any>(default: T) : MutableLiveData<T>() {
    init {
        value = default
    }

    override fun getValue(): T {
        return super.getValue()!!
    }

    @MainThread
    fun observe(owner: LifecycleOwner, body: (T) -> Unit) {
        observe(owner, Observer<T> { t -> body(t!!) })
    }
}

/**
 * Lazily creates a non-null mutable live-data instance with the provided
 * default data. This will also trigger the provided loader function
 */
fun <T : Resource<*, *>> lazyLiveData(
    default: T,
    loadFn: (() -> Unit)?
): Lazy<NonNullMutableLiveData<T>> {
    return lazy {
        loadFn?.invoke()
        NonNullMutableLiveData(default)
    }
}

/**
 * Lazily creates a nullable mutable live-data instance. This will also
 * trigger the provided loader function.
 */
fun <T : Any?> lazyLiveData(
    loadFn: (() -> Unit)?
): Lazy<MutableLiveData<T>> {
    return lazy {
        loadFn?.invoke()
        MutableLiveData<T>()
    }
}
