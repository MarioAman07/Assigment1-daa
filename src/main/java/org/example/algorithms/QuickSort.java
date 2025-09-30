package org.example.algorithms;

import org.example.metrics.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();
    private static final int CUTOFF = 16; // small-n cutoff

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(arr, lo, hi);
            return;
        }

        Metrics.enterRecursion();

        int pivotIndex = partition(arr, lo, hi);
        // recurse on smaller partition first
        if (pivotIndex - lo < hi - pivotIndex) {
            sort(arr, lo, pivotIndex - 1);
            sort(arr, pivotIndex + 1, hi);
        } else {
            sort(arr, pivotIndex + 1, hi);
            sort(arr, lo, pivotIndex - 1);
        }

        Metrics.exitRecursion();
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivotIndex = lo + rand.nextInt(hi - lo + 1);
        swap(arr, pivotIndex, hi);
        int pivot = arr[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            Metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, hi);
        return i;
    }

    private static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo) {
                Metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
