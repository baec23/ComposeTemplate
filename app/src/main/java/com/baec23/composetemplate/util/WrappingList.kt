package com.baec23.composetemplate.util

import android.util.Log
import com.baec23.composetemplate.ui.screen.samplescreen.TAG
import kotlin.math.abs

fun <T> wrappingListOf(vararg elements: T): WrappingList<T> =
    if (elements.isNotEmpty()) WrappingList(elements.asList()) else WrappingList()

class WrappingList<T>(
    private val list: List<T> = emptyList()
) : AbstractList<T>() {
    override val size: Int
        get() = list.size

    override fun get(index: Int): T {
        val mIndex = index.toWrappedIndex(list.size)
        if(mIndex == 60){
            Log.d(TAG, "get: WHAT")
        }
        return list[mIndex]
    }
}

fun <T> mutableWrappingListOf(vararg elements: T): MutableWrappingList<T> =
    if (elements.isNotEmpty()) MutableWrappingList(elements.asList()) else MutableWrappingList()

class MutableWrappingList<T>(
    list: List<T> = emptyList()
) : AbstractMutableList<T>() {
    private val mutableList: MutableList<T> = mutableListOf()

    init {
        mutableList.addAll(list)
    }

    override fun add(index: Int, element: T) {
        val mIndex = index.toWrappedIndex(mutableList.size + 1)
        mutableList.add(index = mIndex, element = element)
    }

    override val size: Int
        get() = mutableList.size

    override fun get(index: Int): T {
        val mIndex = index.toWrappedIndex(mutableList.size)
        return mutableList[mIndex]
    }

    override fun removeAt(index: Int): T {
        val mIndex = index.toWrappedIndex(mutableList.size)
        return mutableList.removeAt(mIndex)
    }

    override fun set(index: Int, element: T): T {
        val mIndex = index.toWrappedIndex(mutableList.size)
        return mutableList.set(index = mIndex, element = element)
    }

    fun toWrappingList(): WrappingList<T> {
        return WrappingList(mutableList.toList())
    }
}

fun Int.toWrappedIndex(size: Int): Int {
    if (size == 0) {
        return 0
    }
    return if (this < 0) {
        (size - (abs(this) % size)) % size
    } else {
        this % size
    }
}