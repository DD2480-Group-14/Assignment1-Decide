package com.decide.app.calculator;

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
    void lic5Positive() {
        int numpoints = 2;
        Point[] points = new Point[] {new Point(1.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(numpoints, points, parameters);

        assertTrue(cmv.lic5());
    }

    @Test
    void lic5FalseBoundryCase() {
        // lic5 should be false if two concecutive points have the same x value.
        int numpoints = 2;
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(numpoints, points, parameters);

        assertFalse(cmv.lic5());
    }

    @Test
    void lic5FewerThanTwoPoints() {
        int numpoints = 1;
        Point[] points = new Point[] {new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(numpoints, points, parameters);

        assertFalse(cmv.lic5());
    }

}
