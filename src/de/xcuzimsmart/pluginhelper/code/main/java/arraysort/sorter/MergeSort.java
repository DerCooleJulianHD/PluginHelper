package de.xcuzimsmart.pluginhelper.code.main.java.arraysort.sorter;

import de.xcuzimsmart.pluginhelper.code.main.java.arraysort.ArraySort;

public final class MergeSort extends ArraySort {

    @Override
    public void sort(int[] input) {
        final int inputLength = input.length;

        if (inputLength < 2) return;

        final int midIndex = inputLength / 2;
        final int[] leftHalf = new int[midIndex];
        final int[] rightHalf = new int[inputLength - midIndex];

        // fill up the left half:
        System.arraycopy(input, 0, leftHalf, 0, midIndex);

        // fill up right half
        if (inputLength - midIndex >= 0)
            System.arraycopy(input, midIndex, rightHalf, 0, inputLength - midIndex);

        // repeat all for every half
        sort(leftHalf);
        sort(rightHalf);

        // merge all half's into one's sorted
        merge(input, leftHalf, rightHalf);
        this.setSorted(input);
    }
}
