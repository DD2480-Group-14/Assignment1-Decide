package com.decide.app.calculators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void tooFewPoints() {
        double[] xs = new double[1];
		double[] ys = new double[1];
		Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2.0;
        
        assertThrows(IllegalStateException.class, () -> new CMV(points, parameters));
    }

    @Test
    void tooManyPoints() {
        double[] xs = new double[101];
		double[] ys = new double[101];
		Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2.0;
        
        assertThrows(IllegalStateException.class, () -> new CMV(points, parameters));
    }

    @Test
    void lic0False() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 1.0), new Point(2.0, 2.0),
                new Point(1.0, 2.0) };
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();

        assertFalse(result);
    }

    @Test
    void lic0True() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 1.0), new Point(2.0, 2.0),
                new Point(1.0, 2.0) };
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();

        assertTrue(result);
    }

    @Test
    void lic0BoundaryCase() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(0.0, 1.0) };
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();

        assertFalse(result);
    }

	@Test
	void lic1AcuteTriangle() {
		Point[] points = new Point[] { new Point(0.5, 1.0), new Point(0.0, 0.0), new Point(2.0, 0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 1.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertTrue(result);
	}

	@Test
	void lic1ObtuseTriangle() {
		Point[] points = new Point[] { new Point(0.0, 3.0), new Point(4.0, 0.0), new Point(9.0, 0.0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 10.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertFalse(result);
	}

	@Test
	void lic1EqualPoints() {
		Point[] points = new Point[] { new Point(1.0, 2.0), new Point(1.0, 2.0), new Point(1.0, 2.0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 0.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertFalse(result);
	}

	@Test
	void lic1FewerThanThreePoints() {
		Point[] points = new Point[] { new Point(0.0, 0.0) , new Point(0.0, 0.0)};
		Parameters parameters = new Parameters();
		CMV cmv = new CMV(points, parameters);

		assertFalse(cmv.lic1());
	}

	@Test
	void lic1PointsOnTheSameLine() {
		Point[] points = new Point[] { new Point(1.0, 5.0), new Point(1.0, 42.0), new Point(1.0, 100.0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 15.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertTrue(result);
	}

    @Test
    void lic2CoincidingPoints() {
        int numPoints = 10;
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; ++i) {
            points[i] = new Point(1, 1);
        }
        Parameters parameters = new Parameters();
        parameters.EPSILON = Math.PI - 0.00001;
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic2());

    }

    @Test
    void lic2OneFalseTriple() {
        Point[] points = new Point[] { new Point(0, 0), new Point(1, -1), new Point(2, 0), new Point(1.5, -1) };
        Parameters parameters = new Parameters();
        parameters.EPSILON = 2;
        CMV cmv = new CMV(points, parameters);
        assertTrue(cmv.lic2());
    }

    @Test
    void lic2TooFewPoints() {
        Point[] points = new Point[] { new Point(0, 0), new Point(1, 1) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic2());
    }

    @Test
    void lic2ManyPointsAllFalse() {
        int numPoints = 100;
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; ++i) {
            points[i] = new Point(i, 0);
        }

        Parameters parameters = new Parameters();
        parameters.EPSILON = 0;
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic2());
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

    /**
     * Three consecutive points lie in more than QUADS quadrants.
     * Should return true.
     */
    @Test
    void lic4MoreThanQUADS() {
        Point[] points = new Point[] {
            new Point(1.0, 0.0),    // Quadrant I 
            new Point(-1.0, 0.0),   // Quadrant II
            new Point(0.0, -1.0),   // Quadrant III
        };  

        Parameters parameters = new Parameters();
        parameters.Q_PTS = 3;
        parameters.QUADS = 2;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic4());
    }

    /**
     * Three consecutive points lie in less than QUADS quadrants.
     * Should return false.
     */
    @Test
    void lic4QLessThanQUADS() {
        Point[] points = new Point[] {
            new Point(1.0, 0.0),    // Quadrant I 
            new Point(1.0, 2.0),    // Quadrant I
            new Point(2.0, 3.0),    // Quadrant I
        };  

        Parameters parameters = new Parameters();
        parameters.Q_PTS = 3;
        parameters.QUADS = 2;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic4());
    }

    /**
     * Two consecutive points lie in more than QUADS quadrants.
     * Should return true.
     */
    @Test
    void lic4CheckIfConsecutive() {
        Point[] points = new Point[] {
            new Point(1.0, 0.0),    // Quadrant I 
            new Point(1.0, 2.0),    // Quadrant I
            new Point(2.0, 3.0),    // Quadrant I
            new Point(1.0, -3.0),    // Quadrant III
        };  

        Parameters parameters = new Parameters();
        parameters.Q_PTS = 2;
        parameters.QUADS = 1;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic4());
    }

    @Test
    void lic5Positive() {
        Point[] points = new Point[] { new Point(1.0, 0.0), new Point(0.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic5());
    }

    @Test
    void lic5Negative() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(2.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
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
    void lic6ConsecutiveInLaterWindow() {
        Point[] points = new Point[] { 
            new Point(0.0, 0.0),
            new Point(1.0,0.0),
            new Point(2.0,0.0), 
            new Point(3.0,5.0), 
            new Point(4.0,0.0)};

        Parameters parameters = new Parameters();
        parameters.N_PTS = 3;
        parameters.DIST = 3.0; 
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic6());
    }

    @Test
    void lic6AllPointsCloseToLine() {
        Point[] points = new Point[] { 
            new Point(0.0, 0.0),    // First point in line
            new Point(1.0,1.0),     // False point
            new Point(1.0,1.5),     // False point
            new Point(0.0,1.0)};    // Last point in line

        Parameters parameters = new Parameters();
        parameters.N_PTS = 4;
        parameters.DIST = 1.0; 
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic6());
    }

    @Test
    void lic6SameFirstAndLastPoint() {
        Point[] points = new Point[] { 
            new Point(0.0, 0.0),
            new Point(1.0,4.0),
            new Point(0.0,0.0)};

        Parameters parameters = new Parameters();
        parameters.N_PTS = 3;
        parameters.DIST = 2.0; 
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic6());
    }
  
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
        double[] xs = {0.0, 0.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
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

	@Test
	void lic11FewerThanThreePoints() {
		Point[] points = new Point[] { new Point(0.0, 0.0), new Point(0.0, 0.0) };
		Parameters parameters = new Parameters();
		parameters.G_PTS = 1;
		CMV cmv = new CMV(points, parameters);
		assertThrows(AssertionError.class, () -> {
			cmv.lic11();
		});
	}

	@Test
	void lic11True() {
		double[] xs = { 24.0, 0.0, 2.0, 23.0 };
		double[] ys = { 28.0, 1.0, 28.0, 0.0 };
		Point[] points = Point.fromArrays(xs, ys);
		Parameters parameters = new Parameters();
		parameters.G_PTS = 2;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic11();

		assertTrue(result);
	}

	@Test
	void lic11False() {
		double[] xs = { 1.0, 0.0, 2.0, 0.0 };
		double[] ys = { 1.0, 2.0, 2.0, 100.0 };
		Point[] points = Point.fromArrays(xs, ys);
		Parameters parameters = new Parameters();
		parameters.G_PTS = 1;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic11();

		assertFalse(result);
	}
	
	@Test
	void lic11SmallestG_PTS() {
		double[] xs = { 3.0, 2.0, 1.0 };
		double[] ys = { 0.0, 0.0, 0.0 };
		Point[] points = Point.fromArrays(xs, ys);
		Parameters parameters = new Parameters();
		parameters.G_PTS = 1;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic11();

		assertTrue(result);
	}

    @Test
    void lic13TooFewPoints() {
        double[] xs = {0.0, 1.0, 2.0, 3.0};
        double[] ys = {0.0, 1.0, 2.0, 3.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic13());
    }

    @Test
    void lic13OnlyFirstConditionTrue() {
        double[] xs = {0.0, 10.0, 1.0, 20.0, 2.0};
        double[] ys = {0.0, 10.0, 1.0, 20.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.RADIUS1 = 0.9;
        parameters.RADIUS2 = 0.0;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic13());
    }

    @Test
    void lic13OnlySecondConditionTrue() {
        double[] xs = {0.0, 10.0, 1.0, 20.0, 2.0};
        double[] ys = {0.0, 10.0, 1.0, 20.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.RADIUS1 = 2.0;
        parameters.RADIUS2 = 1.1;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic13());
    }

    @Test
    void lic13Positive() {
        double[] xs = {0.0, 10.0, 1.0, 20.0, 2.0};
        double[] ys = {0.0, 10.0, 1.0, 20.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.RADIUS1 = 0.9;
        parameters.RADIUS2 = 1.1;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        CMV cmv = new CMV(points, parameters);
        assertTrue(cmv.lic13());
    }


    @Test
    void lic12TooFewPoints() {
        double[] xs = {0.0, 0.0};
        double[] ys = {0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        parameters.LENGTH2 = 1.0;
        parameters.K_PTS = 1;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic12());
    }

    @Test
    void lic12Positive() {
        double[] xs = {0.0, 0.0, 1.0};
        double[] ys = {0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 0.9;
        parameters.LENGTH2 = 1.1;
        parameters.K_PTS = 1;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic12());
    }

    @Test
    void lic12Condition1TrueCondition2False() {
        double[] xs = {0.0, 0.0, 1.0};
        double[] ys = {0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 0.9;
        parameters.LENGTH2 = 0.9;
        parameters.K_PTS = 1;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic12());
    }

    @Test
    void lic12Condition1FalseCondition2True() {
        double[] xs = {0.0, 0.0, 1.0};
        double[] ys = {0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.1;
        parameters.LENGTH2 = 1.1;
        parameters.K_PTS = 1;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic12());
    }


    @Test
    void lic14TooFewPoints() {
        double[] xs = {0.0, 0.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 0.0;
        parameters.AREA2 = 0.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic14());
    }

    @Test
    void lic14Positive() {
        double[] xs = {0.0, 0.0, 1.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0, 1.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 0.4;
        parameters.AREA2 = 0.6;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic14());
    }

    @Test
    void lic14Condition1FalseCondition2True() {
        double[] xs = {0.0, 0.0, 1.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0, 1.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 0.6;
        parameters.AREA2 = 0.6;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic14());
    }

    @Test
    void lic14Condition1TrueCondition1False() {
        double[] xs = {0.0, 0.0, 1.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0, 1.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 0.4;
        parameters.AREA2 = 0.4;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic14());
    }

    /*
     * Tests for additional public methods in the CMV class.
     * These include:
     * calculateTriangleArea
     * calculateAngle
     */

    @Test
    void triangleAreaRightAngle() {
        double sideLengthA = 1.0;
        double sideLengthB = 1.0;
        double sideLengthC = Math.sqrt(2.0);
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        double area = cmv.calculateTriangleArea(sideLengthA, sideLengthB, sideLengthC);
        double expectedArea = 0.5;

        // Area should be B * H / 2 = 0.5
        assertEquals(expectedArea, area, 1e-3);
    }

    @Test
    void triangleAreaSameSides() {
        double sideLengthA = 1.0;
        double sideLengthB = 1.0;
        double sideLengthC = 1.0;
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        double area = cmv.calculateTriangleArea(sideLengthA, sideLengthB, sideLengthC);
        double expectedArea = Math.sqrt(0.75) * 0.5;

        // Area should be B * H / 2, where
        // H = sqrt(1 - 0.5^2)
        assertEquals(expectedArea, area, 1e-3);
    }

    @Test
    void triangleAreaZeroLength() {
        double sideLengthA = 1.0;
        double sideLengthB = 1.0;
        double sideLengthC = 0.0;
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        double area = cmv.calculateTriangleArea(sideLengthA, sideLengthB, sideLengthC);
        double expectedArea = 0.0;

        assertEquals(expectedArea, area, 1e-3);
    }

    @Test
    void calculateAngleRight() {
        double sideLengthA = 1.0;
        double sideLengthB = 1.0;
        double sideLengthC = Math.sqrt(2);
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        double angle = cmv.calculateAngle(sideLengthA, sideLengthB, sideLengthC);
        double expectedAngle = Math.PI / 2;

        assertEquals(expectedAngle, angle, 1e-3);
    }

    @Test
    void calculateAnglePi() {
        double sideLengthA = 1.0;
        double sideLengthB = 1.0;
        double sideLengthC = 2.0;
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        double angle = cmv.calculateAngle(sideLengthA, sideLengthB, sideLengthC);
        double expectedAngle = Math.PI;

        assertEquals(expectedAngle, angle, 1e-3);
    }

    @Test
    void calculateAngleZero() {
        double sideLengthA = 1.0;
        double sideLengthB = 1.0;
        double sideLengthC = 0.0;
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.0, 0.0)};
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        double angle = cmv.calculateAngle(sideLengthA, sideLengthB, sideLengthC);
        double expectedAngle = 0;

        assertEquals(expectedAngle, angle, 1e-3);
    }
}
