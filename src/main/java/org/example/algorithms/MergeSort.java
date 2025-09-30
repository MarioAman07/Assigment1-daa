package org.example.algorithms;

import org.example.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr) {
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi) {
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(arr, lo, hi);
            return;
        }

        Metrics.enterRecursion();

        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);

        Metrics.exitRecursion();
    }

    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else {
                Metrics.incComparisons();  // исправлено
                if (aux[j] < aux[i]) arr[k] = aux[j++];
                else arr[k] = aux[i++];
            }
        }
    }

    private static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo) {
                Metrics.incComparisons();  // исправлено
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }
}

