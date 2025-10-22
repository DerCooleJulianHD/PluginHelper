package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort

abstract class ArraySort : de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.SortAlgorithm {
    protected var sorted: IntArray? = IntArray(0)

    init {
        sort = this
    }

    fun printArray(input: IntArray?) {
        println(input.contentToString())
    }

    fun swap(input: IntArray, i1: Int, i2: Int) {
        val buffer = input[i1]
        input[i1] = input[i2]
        input[i2] = buffer
    }

    fun merge(input: IntArray, leftHalf: IntArray, rightHalf: IntArray) {
        val leftHalfSize = leftHalf.size
        val rightHalfSize = rightHalf.size

        var leftIndex = 0
        var rightIndex = 0
        var mergedIndex = 0

        while (leftIndex < leftHalfSize && rightIndex < rightHalfSize) {
            if (leftHalf[leftIndex] <= rightHalf[rightIndex]) {
                input[mergedIndex] = leftHalf[leftIndex]
                leftIndex++
            } else {
                input[mergedIndex] = rightHalf[rightIndex]
                rightIndex++
            }

            mergedIndex++
        }

        while (leftIndex < leftHalfSize) {
            input[mergedIndex] = leftHalf[leftIndex]
            leftIndex++
            mergedIndex++
        }

        while (rightIndex < rightHalfSize) {
            input[mergedIndex] = rightHalf[rightIndex]
            rightIndex++
            mergedIndex++
        }
    }

    override fun getSorted(): IntArray? {
        return sorted
    }

    protected fun setSorted(sorted: IntArray?) {
        this.sorted = sorted
    }

    companion object {
        var sort: ArraySort?
            protected set
    }
}
