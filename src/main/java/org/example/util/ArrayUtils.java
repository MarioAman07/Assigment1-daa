package org.example.util;

import java.util.Random;

public class ArrayUtils {
    private static final Random rand = new Random();

    // =====================
    // Swap
    // =====================
    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    // =====================
    // Shuffle (Fisher-Yates)
    // =====================
    public static void shuffle(int[] arr) {
        checkNotNullOrEmpty(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    // =====================
    // Partition for QuickSort / Select
    // Returns pivot index after partition
    // =====================
    public static int partition(int[] arr, int lo, int hi, int pivotIndex) {
        checkNotNullOrEmpty(arr);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, hi);
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, hi);
        return i;
    }

    // =====================
    // Guard checks
    // =====================
    public static void checkNotNullOrEmpty(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
    }
}
