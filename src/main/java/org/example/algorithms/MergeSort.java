package org.example.algorithms;

import org.example.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16; // small-n cutoff

    public static void sort(int[] arr, Metrics metrics) {
        int[] buffer = new int[arr.length]; // reusable buffer
        sort(arr, buffer, 0, arr.length - 1, metrics);
    }

    private static void sort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        int mid = (left + right) >>> 1;

        metrics.enterRecursion();
        sort(arr, buffer, left, mid, metrics);
        sort(arr, buffer, mid + 1, right, metrics);
        metrics.exitRecursion();

        merge(arr, buffer, left, mid, right, metrics);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = buffer[j++];
            } else if (j > right) {
                arr[k] = buffer[i++];
            } else {
                metrics.incComparisons();
                if (buffer[i] <= buffer[j]) {
                    arr[k] = buffer[i++];
                } else {
                    arr[k] = buffer[j++];
                }
            }
        }
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}
