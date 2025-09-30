# Assignment 1: Divide-and-Conquer Algorithms

This project implements several classic divide-and-conquer algorithms in Java with safe recursion patterns, metrics tracking, and automated tests. The project is structured as a Maven project and uses JUnit 5 for testing.

---

## Project Structure

.
├── algorithms
│   ├── MergeSort.java
│   ├── QuickSort.java
│   └── DeterministicSelect.java
├── metrics
│   └── Metrics.java
└── util
    └── ArrayUtils.java



- **algorithms/**: contains implementations of sorting and selection algorithms.  
- **metrics/**: contains `Metrics.java` to track comparisons, allocations, and recursion depth.  
- **util/**: contains `ArrayUtils.java` with helper methods such as swap, shuffle, and partition.

---

## Implemented Algorithms

### 1. MergeSort
- Classic divide-and-conquer sort.  
- Features:
  - Linear merge using an auxiliary array.
  - Small-n cutoff using insertion sort for small subarrays.
  - Metrics tracking of comparisons and recursion depth.

**Recurrence Analysis:**  
- Recurrence: T(n) = 2T(n/2) + Θ(n)  
- Master Theorem Case 2 → Θ(n log n)  

### 2. QuickSort
- Randomized pivot for robustness.  
- Recurse on smaller partition first, iterate on the larger one to bound stack depth (~O(log n) typical).  
- Small-n cutoff with insertion sort.  
- Metrics tracking included.

**Recurrence Analysis:**  
- Recurrence: T(n) = T(k) + T(n-k-1) + Θ(n), pivot random  
- Average Θ(n log n), worst-case Θ(n²), mitigated by randomization.

### 3. Deterministic Select (Median-of-Medians)
- Linear-time selection algorithm to find the k-th smallest element.  
- Groups of 5, median-of-medians pivot, in-place partitioning.  
- Recurses only on the needed side, preferring smaller side.  
- Metrics track comparisons and recursion depth.

**Recurrence Analysis:**  
- Recurrence: T(n) ≤ T(⌈n/5⌉) + T(7n/10) + Θ(n)  
- Akra–Bazzi intuition → Θ(n)  

---

## Metrics and Testing

- **Metrics.java** tracks:
  - Number of comparisons
  - Number of allocations
  - Current recursion depth and maximum depth

- **Testing:**  
  - MergeSort and QuickSort: correctness on random and adversarial arrays; recursion depth is bounded.  
  - Deterministic Select: compared with Java's `Arrays.sort(a)[k]` over multiple trials.  

- **JUnit tests** verify correctness for each algorithm.

---

## Notes on Implementation

- **Safe recursion patterns:** recursion enters and exits are tracked in `Metrics`.  
- **Utilities:** `ArrayUtils` provides common helper methods like `swap`, `shuffle`, and `partition`.  
- **Cutoff optimization:** small subarrays use insertion sort to reduce overhead.  
- **Branching workflow:**  
  - `main` — stable releases only  
  - `feature/metrics` — metrics implementation  
  - `feature/mergesort` — MergeSort  
  - `feature/quicksort` — QuickSort  
  - `feature/select` — Deterministic Select  

---

## Build & Run

**Build with Maven:**

```bash
mvn clean compile
mvn test
```


## Summary
- **All implemented algorithms match theoretical analysis:
- **MergeSort: Θ(n log n)
- **QuickSort: average Θ(n log n)
- **Deterministic Select: Θ(n)
- **Metrics allow observing constant-factor effects, recursion depth, and comparison counts.
- **Project is ready for the next steps: Closest Pair of Points and CLI/benchmarking.
