package org.example.metrics;

public class Metrics {
    private static long comparisons = 0;
    private static long recursionDepth = 0;
    private static long currentDepth = 0;

    public static void incrementComparisons() {
        comparisons++;
    }

    public static void enterRecursion() {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);
    }

    public static void exitRecursion() {
        currentDepth--;
    }

    public static long getComparisons() {
        return comparisons;
    }

    public static long getMaxDepth() {
        return recursionDepth;
    }

    public static void reset() {
        comparisons = 0;
        recursionDepth = 0;
        currentDepth = 0;
    }
}
