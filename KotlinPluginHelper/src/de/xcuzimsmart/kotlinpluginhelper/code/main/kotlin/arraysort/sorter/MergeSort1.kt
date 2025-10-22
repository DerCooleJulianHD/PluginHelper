package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.sorter

import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort

class MergeSort : de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort() {
    override fun sort(input: IntArray) {
        val inputLength = input.size

        if (inputLength < 2) return

        val midIndex = inputLength / 2
        val leftHalf = IntArray(midIndex)
        val rightHalf = IntArray(inputLength - midIndex)

        // fill up the left half:
        System.arraycopy(input, 0, leftHalf, 0, midIndex)

        // fill up right half
        if (inputLength - midIndex >= 0) System.arraycopy(input, midIndex, rightHalf, 0, inputLength - midIndex)

        // repeat all for every half
        sort(leftHalf)
        sort(rightHalf)

        // merge all half's into one's sorted
        merge(input, leftHalf, rightHalf)
        this.setSorted(input)
    }
}
