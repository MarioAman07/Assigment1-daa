package org.example.algorithms;

public class DeterministicSelectTest package org.example.algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSelectRandom() {
        int[] arr = {9, 2, 7, 1, 5, 3, 8, 6, 4};
        for (int k = 0; k < arr.length; k++) {
            int[] copy = Arrays.copyOf(arr, arr.length);
            int expected = Arrays.stream(copy).sorted().toArray()[k];
            assertEquals(expected, DeterministicSelect.select(copy, k));
        }
    }

    @Test
    void testSelectSingle() {
        int[] arr = {42};
        assertEquals(42, DeterministicSelect.select(arr, 0));
    }
}
{
}
