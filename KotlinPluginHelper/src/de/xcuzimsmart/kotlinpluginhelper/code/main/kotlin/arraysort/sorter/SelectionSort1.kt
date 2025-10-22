package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.sorter

import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort

class SelectionSort : de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort() {
    override fun sort(input: IntArray) {
        val length = input.size

        for (i in 0..<length - 1) {
            var min = input[i]
            var index = i

            for (j in i + 1..<length) {
                if (input[j] < min) {
                    min = input[j]
                    index = j
                }
            }

            swap(input, i, index)
        }

        setSorted(input)
    }
}
