package de.xcuzimsmart.pluginhelper.code.main.java.arraysort;

import java.util.Arrays;

public abstract class ArraySort implements SortAlgorithm {

    protected static ArraySort instance;

    public ArraySort() {
        instance = this;
    }

    protected int[] sorted = new int[0];

    public final void printArray(int[] input) {
        System.out.println(Arrays.toString(input));
    }

    public final void swap(int[] input, int i1, int i2) {
        int buffer = input[i1];
        input[i1] = input[i2];
        input[i2] = buffer;
    }

    public final void merge(int[] input, int[] leftHalf, int[] rightHalf) {
        final int leftHalfSize = leftHalf.length;
        final int rightHalfSize = rightHalf.length;

        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        while (leftIndex < leftHalfSize && rightIndex < rightHalfSize) {
            if (leftHalf[leftIndex] <= rightHalf[rightIndex]) {
                input[mergedIndex] = leftHalf[leftIndex];
                leftIndex++;
            } else {
                input[mergedIndex] = rightHalf[rightIndex];
                rightIndex++;
            }

            mergedIndex++;
        }

        while (leftIndex < leftHalfSize) {
            input[mergedIndex] = leftHalf[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < rightHalfSize) {
            input[mergedIndex] = rightHalf[rightIndex];
            rightIndex++;
            mergedIndex++;
        }
    }

    @Override
    public int[] getSorted() {
        return sorted;
    }

    protected void setSorted(int[] sorted) {
        this.sorted = sorted;
    }

    public static ArraySort getSort() {
        return instance;
    }
}
