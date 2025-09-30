package org.example.algorithms;

public class ClosestPairTest package org.example.algorithms;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testSmallSet() {
        Point2D.Double[] points = {
                new Point2D.Double(0,0),
                new Point2D.Double(1,1),
                new Point2D.Double(2,2)
        };
        double expected = Math.sqrt(2);
        assertEquals(expected, ClosestPair.closest(points), 1e-9);
    }

    @Test
    void testTwoPoints() {
        Point2D.Double[] points = {
                new Point2D.Double(5,5),
                new Point2D.Double(1,2)
        };
        double expected = points[0].distance(points[1]);
        assertEquals(expected, ClosestPair.closest(points), 1e-9);
    }
}
{
}
