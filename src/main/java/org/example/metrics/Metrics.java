package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int depth = 0;
    private int maxDepth = 0;

    public void incComparisons() { comparisons++; }
    public void incAllocations() { allocations++; }

    public void enterRecursion() {
        depth++;
        maxDepth = Math.max(maxDepth, depth);
    }

    public void exitRecursion() {
        depth--;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        depth = 0;
        maxDepth = 0;
    }

    public void saveToCSV(String filename, long n, long timeNs) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(n + "," + timeNs + "," + comparisons + "," + allocations + "," + maxDepth + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}