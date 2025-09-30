package org.example.algorithms;

import org.example.metrics.Metrics;

import java.awt.geom.Point2D;
import java.util.Arrays;

public class ClosestPair {

    public static double closest(Point2D.Double[] points) {
        if (points == null || points.length < 2)
            throw new IllegalArgumentException("At least 2 points required");

        Metrics.enterRecursion();
        Point2D.Double[] px = points.clone();
        Point2D.Double[] py = points.clone();

        Arrays.sort(px, (a, b) -> Double.compare(a.x, b.x));
        Arrays.sort(py, (a, b) -> Double.compare(a.y, b.y));

        double result = closestRec(px, py);
        Metrics.exitRecursion();
        return result;
    }

    private static double closestRec(Point2D.Double[] px, Point2D.Double[] py) {
        int n = px.length;
        if (n <= 3) return bruteForce(px);

        int mid = n / 2;
        Point2D.Double midPoint = px[mid];

        Point2D.Double[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point2D.Double[] Rx = Arrays.copyOfRange(px, mid, n);

        Point2D.Double[] Qy = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point2D.Double[]::new);
        Point2D.Double[] Ry = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point2D.Double[]::new);

        double dl = closestRec(Qx, Qy);
        double dr = closestRec(Rx, Ry);
        double d = Math.min(dl, dr);

        // Build strip
        Point2D.Double[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point2D.Double[]::new);
        double ds = stripClosest(strip, d);

        return Math.min(d, ds);
    }

    private static double bruteForce(Point2D.Double[] points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                minDist = Math.min(minDist, points[i].distance(points[j]));
            }
        }
        return minDist;
    }

    private static double stripClosest(Point2D.Double[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                min = Math.min(min, strip[i].distance(strip[j]));
            }
        }
        return min;
    }
}
