package com.decide.app.calculators;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.decide.app.model.*;

/**
 * Unit test for CMV computation.
 */
public class CMVTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    void lic3ObtuseAngledTriangle() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(0.5, 0.1) };
        Parameters parameters = new Parameters();
        parameters.AREA1 = 1;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic3());
    }

    @Test
    void lic3TooFewPoints() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 1.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic3());
    }

    @Test
    void lic3ValidTriangle() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(0.0, 1.0), new Point(1.0, 0.0) };
        Parameters parameters = new Parameters();
        parameters.AREA1 = 0.1;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic3());
    }

    @Test
    void lic3ManySmallTriangles() {
        int numpoints = 100;
        Point[] points = new Point[numpoints];
        for (int i = 0; i < numpoints; ++i) {
            points[i] = new Point(i, i % 2);
        }
        Parameters parameters = new Parameters();
        parameters.AREA1 = 1.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic3());
    }

    @Test
    void lic3OnlyOneValidTriangle() {
        int numpoints = 100;
        Point[] points = new Point[numpoints];
        points[97] = new Point(10, 10);
        points[98] = new Point(10, 20);
        points[99] = new Point(20, 10);
        for (int i = 0; i < 97; ++i) {
            points[i] = new Point(i, i % 2);
        }
        Parameters parameters = new Parameters();
        parameters.AREA1 = 1.0;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic3());
    }

    @Test
    void lic5Positive() {
        Point[] points = new Point[] { new Point(1.0, 0.0), new Point(0.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic5());
    }

    @Test
    void lic5FalseBoundryCase() {
        // lic5 should be false if two concecutive points have the same x value.
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(0.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
    }

    @Test
    void lic5FewerThanTwoPoints() {
        Point[] points = new Point[] { new Point(0.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
    }

}
