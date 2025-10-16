package de.xcuzimsmart.pluginhelper.code.main.java.arraysort.sorter;

import de.xcuzimsmart.pluginhelper.code.main.java.arraysort.ArraySort;

import java.util.Random;

public final class QuickSort extends ArraySort {

    @Override
    public void sort(int[] input) {
        sort(input, 0, input.length - 1);
    }

    private void sort(int[] input, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) return;
        final Random random = new Random();

        final int pivotIndex = random.nextInt(highIndex - lowIndex) + lowIndex;
        final int pivot = input[pivotIndex];
        swap(input, pivotIndex, highIndex);

        int leftPointer = lowIndex;
        int rightpointer = highIndex;

        // partioning
        while (leftPointer < rightpointer) {
            while (input[leftPointer] <= pivot && leftPointer < rightpointer) leftPointer++;
            while (input[rightpointer] >= pivot && leftPointer < rightpointer) rightpointer--;

            swap(input, leftPointer, rightpointer);
        }

        swap(input, leftPointer, highIndex);

        // recursivly recalling
        sort(input, lowIndex, leftPointer -1);
        sort(input, leftPointer + 1, highIndex);
    }
}
