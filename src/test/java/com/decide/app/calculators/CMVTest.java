package com.decide.app.calculators;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.decide.app.model.*;

/**
 * Unit test for CMV computation.
 */
public class CMVTest {
    private Parameters randomParameters = new Parameters(1.0, 2.0, 3.0, 4.0, 5, 6, 7.0, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17.0, 18.0, 19.0);

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    void lic5Positive() {
        int numpoints = 2;
        Point[] points = new Point[] {new Point(1.0, 0.0), new Point(0.0, 0.0)};
        CMV cmv = new CMV(numpoints, points, randomParameters);

        assertTrue(cmv.lic5());
    }

    @Test
    void lic5FalseBoundryCase() {
        // lic5 should be false if two concecutive points have the same x value.
        int numpoints = 2;
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        CMV cmv = new CMV(numpoints, points, randomParameters);

        assertFalse(cmv.lic5());
    }

    @Test
    void lic5FewerThanTwoPoints() {
        int numpoints = 1;
        Point[] points = new Point[] {new Point(0.0, 0.0)};
        CMV cmv = new CMV(numpoints, points, randomParameters);

        assertFalse(cmv.lic5());
    }

}
