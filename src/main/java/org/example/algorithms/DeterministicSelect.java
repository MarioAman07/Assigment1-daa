package org.example.algorithms;

import org.example.metrics.Metrics;
import org.example.util.ArrayUtils;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length)
            throw new IllegalArgumentException("Invalid input");
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int lo, int hi, int k) {
        Metrics.enterRecursion();

        while (true) {
            if (hi - lo + 1 <= 5) {
                insertionSort(arr, lo, hi);
                Metrics.exitRecursion();
                return arr[lo + k];
            }

            int pivotIndex = medianOfMedians(arr, lo, hi);
            pivotIndex = ArrayUtils.partition(arr, lo, hi, pivotIndex);

            int rank = pivotIndex - lo;
            if (k == rank) {
                Metrics.exitRecursion();
                return arr[pivotIndex];
            } else if (k < rank) {
                hi = pivotIndex - 1;
            } else {
                k = k - rank - 1;
                lo = pivotIndex + 1;
            }
        }
    }

    private static int medianOfMedians(int[] arr, int lo, int hi) {
        int n = hi - lo + 1;
        int numMedians = (n + 4) / 5;
        for (int i = 0; i < numMedians; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            insertionSort(arr, subLo, subHi);
            ArrayUtils.swap(arr, lo + i, subLo + (subHi - subLo) / 2);
        }
        return select(arr, lo, lo + numMedians - 1, numMedians / 2);
    }

    private static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo && arr[j] > key) {
                Metrics.incComparisons();
                ArrayUtils.swap(arr, j, j + 1);
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
