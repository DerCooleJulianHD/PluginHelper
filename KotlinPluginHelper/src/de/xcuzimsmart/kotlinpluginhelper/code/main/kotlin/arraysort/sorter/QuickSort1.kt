package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.sorter

import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort
import java.util.*

class QuickSort : de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort() {
    override fun sort(input: IntArray) {
        sort(input, 0, input.size - 1)
        setSorted(input)
    }

    private fun sort(input: IntArray, lowIndex: Int, highIndex: Int) {
        if (lowIndex >= highIndex) return
        val random = Random()

        val pivotIndex = random.nextInt(highIndex - lowIndex) + lowIndex
        val pivot = input[pivotIndex]
        swap(input, pivotIndex, highIndex)

        var leftPointer = lowIndex
        var rightpointer = highIndex

        // partioning
        while (leftPointer < rightpointer) {
            while (input[leftPointer] <= pivot && leftPointer < rightpointer) leftPointer++
            while (input[rightpointer] >= pivot && leftPointer < rightpointer) rightpointer--

            swap(input, leftPointer, rightpointer)
        }

        swap(input, leftPointer, highIndex)

        // recursivly recalling
        sort(input, lowIndex, leftPointer - 1)
        sort(input, leftPointer + 1, highIndex)
    }
}
