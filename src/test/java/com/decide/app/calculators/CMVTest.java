package com.decide.app.calculators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.decide.app.model.Parameters;
import com.decide.app.model.Point;

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
    void lic7TooFewPoints() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 1.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 0.5;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic7());
    }

    @Test
    void lic7Positive() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 1.0), new Point(2.0, 2.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic7());
    }

    @Test
    void lic7Negative() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.5, 0.0), new Point(1.0, 0.0), new Point(1.5, 0.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic7());
    }

    @Test
    void lic7DistanceIsLength1() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(2.0, 0.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic7());
    }

    @Test
    void lic7ZeroKPTS() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(3.0, 1.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 0;
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic7());
    }
}
