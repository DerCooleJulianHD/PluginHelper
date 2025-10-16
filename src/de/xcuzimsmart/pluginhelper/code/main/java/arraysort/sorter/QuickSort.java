package de.xcuzimsmart.pluginhelper.code.main.java.arraysort.sorter;

import de.xcuzimsmart.pluginhelper.code.main.java.arraysort.ArraySorter;

public final class QuickSort implements ArraySorter {

    int[] sorted = null;

    @Override
    public void sort(int[] input) {
        this.quicksort(input);
    }

    void quicksort(int[] input) {
        final int highIndex = input.length - 1;
        final int lowIndex = 0;

        final int pivot = input[highIndex];
    }

    @Override
    public int[] getSorted() {
        return sorted;
    }

    void swap() {

    }
}
