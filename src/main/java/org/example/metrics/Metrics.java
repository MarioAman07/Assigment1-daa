package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private static long comparisons = 0;
    private static long allocations = 0;
    private static int depth = 0;
    private static int maxDepth = 0;

    // Методы для работы с подсчетами — все static
    public static void incComparisons() { comparisons++; }
    public static void incAllocations() { allocations++; }

    public static void enterRecursion() {
        depth++;
        maxDepth = Math.max(maxDepth, depth);
    }

    public static void exitRecursion() {
        depth--;
    }

    public static void reset() {
        comparisons = 0;
        allocations = 0;
        depth = 0;
        maxDepth = 0;
    }

    public static void saveToCSV(String filename, long n, long timeNs) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(n + "," + timeNs + "," + comparisons + "," + allocations + "," + maxDepth + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
