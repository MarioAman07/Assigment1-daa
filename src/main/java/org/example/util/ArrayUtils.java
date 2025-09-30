package org.example.util;

public class ArrayUtils {

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            swap(arr, i, j);
        }
    }

    public static int partition(int[] arr, int lo, int hi, int pivotIndex) {
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

    public static void checkNotNullOrEmpty(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("Array must not be null or empty");
    }
}
