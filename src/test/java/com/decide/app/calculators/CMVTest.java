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
    void lic0False() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(1.0, 2.0)};
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();

        assertFalse(result);
    }

    @Test 
    void lic0True() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(1.0, 2.0)};
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();

        assertTrue(result);
    }

    @Test 
    void lic0BoundaryCase() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 1.0)};
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();

        assertFalse(result);
    }


    @Test
    void lic0FewerThanTwoPoints() {
        Point[] points = new Point[] {new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();
        
        assertFalse(result);
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
        Point[] points = new Point[] {new Point(1.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic5());
    }

    @Test
    void lic5Negative() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(2.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
    }

    @Test
    void lic5FalseBoundryCase() {
        // lic5 should be false if two concecutive points have the same x value.
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
    }

    @Test
    void lic5FewerThanTwoPoints() {
        Point[] points = new Point[] {new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
    }

    @Test 
    void lic9Positive() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(1.0, -5.0), new Point(1.0, 1.0), new Point(2.0, 0.0)};
        Parameters parameters = new Parameters();
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.EPSILON = Math.PI / 2;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic9());
    }

    @Test
    void lic9BadAngle() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(1.0, -1.0), new Point(1.0, 1.0), new Point(2.0, 0.0)};
        Parameters parameters = new Parameters();
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.EPSILON = 2;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic9());
    }

    @Test
    void lic9TooFewPoints() {
        Point[] points = new Point[] {new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic9());
    }

     @Test
    void lic9Coinciding() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(2.0, 0.0), new Point(1.0, 1.0), new Point(2.0, 0.0)};
        Parameters parameters = new Parameters();
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.EPSILON = 0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic9());
    }

}
