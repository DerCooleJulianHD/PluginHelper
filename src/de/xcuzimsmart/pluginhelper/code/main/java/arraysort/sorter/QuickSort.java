package de.xcuzimsmart.pluginhelper.code.main.java.arraysort.sorter;

import de.xcuzimsmart.pluginhelper.code.main.java.arraysort.ArraySort;

public final class QuickSort extends ArraySort {

    @Override
    public void sort(int[] input) {
        this.quicksort(input);
    }

    void quicksort(int[] input) {
        final int highIndex = input.length - 1;
        final int lowIndex = 0;

        final int pivot = input[highIndex];
    }

    void swap() {

    }
}
