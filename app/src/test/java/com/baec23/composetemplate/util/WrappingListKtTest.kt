package com.baec23.composetemplate.util

import org.junit.Test

class WrappingListKtTest {

    @Test
    fun wrappingListOf() {
    }

    @Test
    fun mutableWrappingListOf() {
    }

    @Test
    fun toWrappedIndex_returns_correct_index() {
        val testList = listOf(0, 1, 2, 3, 4, 5, 6)
        assert(5.toWrappedIndex(0) == 0)
        assert(0.toWrappedIndex(testList.size) == 0)
        assert(5.toWrappedIndex(testList.size) == 5)
        assert((-2).toWrappedIndex(testList.size) == 5)
        assert(7.toWrappedIndex(testList.size) == 0)
    }
}