package de.xcuzimsmart.pluginhelper.code.main.java.arraysort.sorter;

import de.xcuzimsmart.pluginhelper.code.main.java.arraysort.ArraySort;

public final class SelectionSort extends ArraySort {

    @Override
    public void sort(int[] input) {
        int length = input.length;

        for (int i = 0; i < length - 1; i++) {
            int min = input[i];
            int index = i;

            for (int j = i + 1; j < length; j++) {
                if (input[j] < min) {
                    min = input[j];
                    index = j;
                }
            }

            swap(input, i, index);
        }
    }
}
