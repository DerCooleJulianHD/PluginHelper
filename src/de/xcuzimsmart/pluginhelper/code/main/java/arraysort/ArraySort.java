package de.xcuzimsmart.pluginhelper.code.main.java.arraysort;

import java.util.Arrays;

public abstract class ArraySort implements ArraySorter {

    protected static ArraySort instance;

    public ArraySort() {
        instance = this;
    }

    protected int[] sorted = new int[0];

    public void printArray(int[] input) {
        System.out.println(Arrays.toString(input));
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
