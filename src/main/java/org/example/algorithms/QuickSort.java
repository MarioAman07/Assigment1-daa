package org.example.algorithms;

import org.example.metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics m) {
        sort(arr, 0, arr.length - 1, m);
    }

    private static void sort(int[] arr, int lo, int hi, Metrics m) {
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(arr, lo, hi, m);
            return;
        }

        m.enterRecursion();
        int pivotIndex = partition(arr, lo, hi, m);

        if (pivotIndex - lo < hi - pivotIndex) {
            sort(arr, lo, pivotIndex - 1, m);
            sort(arr, pivotIndex + 1, hi, m);
        } else {
            sort(arr, pivotIndex + 1, hi, m);
            sort(arr, lo, pivotIndex - 1, m);
        }

        m.exitRecursion();
    }

    private static int partition(int[] arr, int lo, int hi, Metrics m) {
        int pivotIndex = lo + rand.nextInt(hi - lo + 1);
        swap(arr, pivotIndex, hi, m);
        int pivot = arr[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            m.incComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j, m);
                i++;
            }
        }
        swap(arr, i, hi, m);
        return i;
    }

    private static void insertionSort(int[] arr, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo) {
                m.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j, Metrics m) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            m.incAllocations();
        }
    }
}
