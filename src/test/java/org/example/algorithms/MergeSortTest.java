package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expected = arr.clone();

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {6, 5, 4, 3, 2, 1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomLargeArray() {
        Random rand = new Random(42);
        int[] arr = rand.ints(10_000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }
}
