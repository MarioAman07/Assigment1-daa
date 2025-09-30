package org.example.algorithms;

import org.example.metrics.Metrics;
import org.example.util.ArrayUtils;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics m) {
        ArrayUtils.checkNotNullOrEmpty(arr);
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1, m);
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi, Metrics m) {
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(arr, lo, hi, m);
            return;
        }

        m.enterRecursion();
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid, m);
        sort(arr, aux, mid + 1, hi, m);
        merge(arr, aux, lo, mid, hi, m);
        m.exitRecursion();
    }

    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi, Metrics m) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else {
                m.incComparisons();
                if (aux[j] < aux[i]) arr[k] = aux[j++];
                else arr[k] = aux[i++];
            }
        }
    }

    private static void insertionSort(int[] arr, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo) {
                m.incComparisons();
                if (arr[j] > key) {
                    ArrayUtils.swap(arr, j, j + 1);
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }
}
