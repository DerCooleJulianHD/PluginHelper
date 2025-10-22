package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.sorter

import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort

class InsertSort : de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.arraysort.ArraySort() {
    override fun sort(input: IntArray) {
        for (i in 1..<input.size) {
            val current = input[i]
            var j = i - 1

            while (j >= 0 && input[j] > current) {
                input[j + 1] = input[j]
                j--
            }

            input[j + 1] = current
        }

        this.setSorted(input)
    }
}
