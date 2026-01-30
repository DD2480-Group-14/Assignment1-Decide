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

    /**
     * Out of bounds.
     * An exception should be thrown if CMV is
     * instantiated with numpoints = 1
     */
    @Test
    void tooFewPoints() {
        double[] xs = new double[1];
		double[] ys = new double[1];
		Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2.0;
        
        assertThrows(IllegalStateException.class, () -> new CMV(points, parameters));
    }

    /**
     * Out of bounds.
     * An exception should be thrown if CMV is
     * instantiated with numpoints = 101.
     */
    @Test
    void tooManyPoints() {
        double[] xs = new double[101];
		double[] ys = new double[101];
		Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2.0;
        
        assertThrows(IllegalStateException.class, () -> new CMV(points, parameters));
    }

    /**
    * All consecutive points lie within distance LENGTH1. 
    * Should return false.
    */
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

    /**
     * One set of two consecutive points with a distance greater than LENGTH1 apart. 
     * Should return true.
     */
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

    /**
     * Consecutive points with exactly distance LENGTH1 apart. 
     * Should return false.
     */
    @Test
    void lic0BoundaryCase() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(0.0, 1.0) };
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        CMV cmv = new CMV(points, parameters);
        boolean result = cmv.lic0();

        assertFalse(result);
    }

    /**
     * Three consecutive create acute triangle which 
     * does not fit within a circle of radius RADIUS1. 
     * Should return true.
     */
	@Test
	void lic1AcuteTriangle() {
		Point[] points = new Point[] { new Point(0.5, 1.0), new Point(0.0, 0.0), new Point(2.0, 0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 1.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertTrue(result);
	}

    /**
     * Three consecutive points create obtuse triangle 
     * which fit within a circle with RADIUS1. 
     * Should return false.
     */
	@Test
	void lic1ObtuseTriangle() {
		Point[] points = new Point[] { new Point(0.0, 3.0), new Point(4.0, 0.0), new Point(9.0, 0.0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 10.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertFalse(result);
	}

    /**
     * Three identical consecutive points and RADIUS1 is zero. 
     * Should return false.
     */
	@Test
	void lic1EqualPoints() {
		Point[] points = new Point[] { new Point(1.0, 2.0), new Point(1.0, 2.0), new Point(1.0, 2.0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 0.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertFalse(result);
	}

    /**
     * Fewer than three points are passed in. 
     * Should return false.
     */
	@Test
	void lic1FewerThanThreePoints() {
		Point[] points = new Point[] { new Point(0.0, 0.0) , new Point(0.0, 0.0)};
		Parameters parameters = new Parameters();
		CMV cmv = new CMV(points, parameters);

		assertFalse(cmv.lic1());
	}

    /**
     * Three consecutive points are on the same line 
     * and cant be contained within radius RADIUS1. 
     * Should return true.
     */
	@Test
	void lic1PointsOnTheSameLine() {
		Point[] points = new Point[] { new Point(1.0, 5.0), new Point(1.0, 42.0), new Point(1.0, 100.0) };
		Parameters parameters = new Parameters();
		parameters.RADIUS1 = 15.0;
		CMV cmv = new CMV(points, parameters);
		boolean result = cmv.lic1();

		assertTrue(result);
	}

    /*
     * Positive test
     * LIC2 should be false if all points coincide,
     * which results in no possible angles.
     */
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

    /*
     * Positive test
     * LIC2 should be true, since numpoints = 4,
     * and the first three points does not satisy
     * the requirement, whereas the last three does.
     */
    @Test
    void lic2OneFalseTriple() {
        Point[] points = new Point[] { new Point(0, 0), new Point(1, -1), new Point(2, 0), new Point(1.5, -1) };
        Parameters parameters = new Parameters();
        parameters.EPSILON = 2;
        CMV cmv = new CMV(points, parameters);
        assertTrue(cmv.lic2());
    }

    /*
     * Negative test
     * LIC2 needs at least 3 points, but this test
     * only provides 2.
     */
    @Test
    void lic2TooFewPoints() {
        Point[] points = new Point[] { new Point(0, 0), new Point(1, 1) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic2());
    }

    /*
     * Negative test
     * 100 points on the x-axis, but since epsilon = 0
     * all angles are exactly Pi, which results in 
     * no angle being greater than Pi + 0 or smaller than
     * Pi - 0.
     */
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

    /*
     * Negative test
     * Obtuse-angled triangle with area smaller than 1.0,
     * since B * H / 2 = 1.0 * 0.1 / 2 = 0.05, and Parameters.area
     * = 1. Therefore, LIC3 should be false.
     */
    @Test
    void lic3ObtuseAngledTriangle() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(0.5, 0.1) };
        Parameters parameters = new Parameters();
        parameters.AREA1 = 1;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic3());
    }

    /*
     * Negative test
     * LIC3 needs 3 points, but this test
     * only provides 2.
     */ 
    @Test
    void lic3TooFewPoints() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 1.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic3());
    }

    /*
     * Positive test
     * Area of this triangle is 1.0 * 1.0 / 2 = 0.5, and 
     * parameters.area = 0.1, therefore LIC3 should be true.
     */ 
    @Test
    void lic3ValidTriangle() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(0.0, 1.0), new Point(1.0, 0.0) };
        Parameters parameters = new Parameters();
        parameters.AREA1 = 0.1;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic3());
    }

    /*
     * Negative test
     * Many points, but all possible triangles have
     * area = 0.5, and parameters.area = 1.0. Therefore
     * LIC3 should be false.
     */ 
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

    /*
     * Positive test
     * Many small triangles can be formed, but only 
     * one large at the end of the array points. Therefore
     * the LIC should be true.
     */ 
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
     * Positive case.
     * Q_PTS = 3, QUADS = 2, and all three consecutive points lie in three different quadrants.
     * Since the number of quadrants visited is greater than QUADS,
     * LIC4 should return true.
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
     * Negative case.
     * Q_PTS = 3, QUADS = 2, and all three consecutive points lie in the same quadrant.
     * Since the number of quadrants visited is not greater than QUADS,
     * LIC4 should return false.
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
     * Positive case.
     * Q_PTS = 2, QUADS = 1, and there exists a pair of consecutive 
     * points that lie in two different quadrants.
     * Since the number of quadrants visited is greater than QUADS,
     * LIC4 should return true.
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

    /**
     * Positive case.
     * lic5 should return true if there are two consecutive points
     * such that point1.x - point0.x < 0.
     */
    @Test
    void lic5Positive() {
        Point[] points = new Point[] { new Point(1.0, 0.0), new Point(0.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic5());
    }

    /**
     * Negative case.
     * lic5 should return false if there are no two consecutive points
     * such that point1.x - point0.x < 0.
     */
    @Test
    void lic5Negative() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(2.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
    }

    /**
     * Negative case.
     * lic5 should return false if there are no two consecutive points
     * such that point1.x - point0.x < 0 but there are two consecutive points
     * such that point1.x - point0.x = 0.
     */
    @Test
    void lic5FalseBoundryCase() {
        Point[] points = new Point[] { new Point(0.0, 0.0), new Point(0.0, 0.0) };
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic5());
    }

    /**
     * Positive case.
     * N_PTS = 3, DIST = 3.0, and the first window of three consecutive points
     * does not contain any point farther than DIST from the line.
     * However, since a later window of three consecutive points contains a point whose
     * distance to the line formed by the first and last points is greater than DIST,
     * LIC6 should return true.
     */
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

    /**
     * Negative case.
     * N_PTS = 4, DIST = 1.0, and all points in the window lie at a distance less
     * than or equal to DIST from the line formed by the first and last points.
     * Since no points fulfill the distance requirement,
     * LIC6 should return false.
     */
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

    /**
     * Positive case.
     * N_PTS = 3, DIST = 2.0, and the first and last points of the window are equal.
     * In this case, the distance is calculated from the midpoint to the first point in the formed line.
     * Since the distance is greater than DIST,
     * LIC6 should return true.
     */
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

    /**
     * Too few points case
     * lic7 should return false if there are less than 3 points
     * but distance is greater than LENGTH1
     */
    @Test
    void lic7TooFewPoints() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 1.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 0.5;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic7());
    }

    /**
     * Positive case
     * lic7 should return true if there are equal to and more than 3 points
     * and distance is greater than LENGTH1.
    */
    @Test
    void lic7Positive() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 1.0), new Point(2.0, 2.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic7());
    }

    /**
     * Negative case
     * lic7 should return false if there are equal to and more than 3 points
     * but distance is lesser than LENGTH1.
    */
    @Test
    void lic7Negative() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(0.5, 0.0), new Point(1.0, 0.0), new Point(1.5, 0.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic7());
    }

     /**
     * Distance is exactly LENGTH1 case
     * lic7 should return false if there are equal to and more than 3 points
     * but distance is equal to LENGTH1.
    */   
    @Test
    void lic7DistanceIsLength1() {
        Point[] points = new Point[] {new Point(0.0, 0.0), new Point(1.0, 0.0), new Point(2.0, 0.0)};
        Parameters parameters = new Parameters();
        parameters.K_PTS = 1;
        parameters.LENGTH1 = 2.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic7());
    }

    /**
     * Too few points case
     * lic8 should return false if there are less than 5 points
     * but radius is greater than RADIUS1
     */
    @Test
    void lic8TooFewPoints() {
        double[] xs = {0.0, 0.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.RADIUS1 = 1.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic8());
    }

    /**
     * Positive case
     * lic8 should return true if there are equal to and more than 5 points
     * and radius is greater than RADIUS1.
    */
    @Test
    void lic8Positive() {
        double[] xs = {0.0, 10.0, 0.0, 1.0, 2.0};
        double[] ys = {0.0, 0.0, 10.0, 1.0, 2.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.RADIUS1 = 3.0;
        CMV cmv = new CMV(points, parameters);

        assertTrue(cmv.lic8());
    }

    /**
     * Triples case
     * lic8 should return false if there are equal to and more than 5 points
     * and all triples fit in RADIUS1.
    */
    @Test
    void lic8FalseAllTriplesFitInRadius() {
        double[] xs = {0.0, 0.5, 1.0, 0.5, 0.2};
        double[] ys = {0.0, 0.5, 0.0, 0.2, 0.1};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.RADIUS1 = 5.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic8());
    }

    /**
     * Collinear Points case
     * lic8 should return false if there are equal to and more than 5 points
     * and all points fit in RADIUS1.
    */
    @Test
    void lic8CollinearPoints() {
        double[] xs = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] ys = {0.0, 1.0, 2.0, 3.0, 4.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.RADIUS1 = 5.0;
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic8());
    }

    /*
     * Positive test
     * The angle between first, third and last point
     * is smaller than Pi - Pi / 2.
     */ 
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

    /*
     * Negative test
     * No angle greater than Pi + 2 or smaller than
     * Pi - 2 exist.
     */ 
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

    /*
     * Negative test
     * The LIC requires >= 5 points, whereas this
     * test only provides 4. Therefore the LIC 
     * should be false.
     */ 
    @Test
    void lic9TooFewPoints() {
        double[] xs = {0.0, 0.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        CMV cmv = new CMV(points, parameters);

        assertFalse(cmv.lic9());
    }

    /*
     * Negative test
     * Coinciding points can not form an angle that
     * fulfills the requirements, and since this test contains
     * exactly 5 points, the LIC should be false.
     */ 
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

    /**
     * Too few points case
     * lic10 should return false if there are less than 5 points
     * but triangle area is greater than AREA1
     */
    @Test
    void lic10TooFewPoints() {
        double[] xs = {0.0, 0.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 1.0;
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic10());
    }

    /**
     * Positive case
     * lic10 should return true if there are equal to and more than 5 points
     * and triangle area is greater than AREA1.
    */
    @Test
    void lic10Positive(){
        double[] xs = {0.0, 0.0, 1.0, 0.0, 0.0};
        double[] ys = {0.0, 0.0, 0.0, 0.0, 1.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 0.4;
        CMV cmv = new CMV(points, parameters);
        assertTrue(cmv.lic10());
    }


    /**
     * Collinear Points case
     * lic10 should return false if there are equal to and more than 5 points
     * but triangle area is less than or equal to AREA1.
    */
    @Test
    void lic10CollinearPoints(){
        double[] xs = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] ys = {0.0, 1.0, 2.0, 3.0, 4.0};
        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = new Parameters();
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 0.1;
        CMV cmv = new CMV(points, parameters);
        assertFalse(cmv.lic10());
    }

   /**
    * Two points separated by exactly G_PTS consecutive
    * intervening points so X[j] - X[i] < 0. 
    * Should return true.
    */
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

   /**
    * Two points separated by exactly G_PTS 
    * consecutive intervening points so X[j] - X[i] >= 0. 
    * Should return false.
    */
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

   /**
    * Two points separated by the minimum 
    * G_PTS so X[j] - X[i] < 0. 
    * Should return true.
    */
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


    /**
     * Out of bounds.
     * lic12 should return false if numpoints < 3
     */
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

    /**
     * Positive case.
     * lic12 should return true if:
     * 1. There are two points seperated by K_PTS which are 
     * seperated by a distance greater than LENGTH1,
     * 2. There are two points seperated by K_PTS which are 
     * seperated by a distance smaller than LENGTH2.
     */
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

    /**
     * Negative case.
     * lic12 should return false if:
     * 1. There are two points seperated by K_PTS which are 
     * seperated by a distance greater than LENGTH1,
     * 2. There are no two points seperated by K_PTS which are 
     * seperated by a distance smaller than LENGTH2.
     */
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

    /**
     * Negative case.
     * lic12 should return false if:
     * 1. There are no two points seperated by K_PTS which are 
     * seperated by a distance greater than LENGTH1,
     * 2. There are two points seperated by K_PTS which are 
     * seperated by a distance smaller than LENGTH2.
     */
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

    /*
     * Negative test
     * The LIC requires >= 5 points, whereas
     * this test only provides 4. Therefore
     * the LIC should be false.
     */ 
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

    /*
     * Negative test
     * A circle exists that cannot be contained within radius 0.9,
     * but no circle with radius 0.0 can contain
     * three of these points separated by A_PTS and B_PTS.
     */ 
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

    /*
     * Negative test
     * There is a circle with radius 2.0 that can contain three
     * of these points separated by A_PTS and B_PTS, respectively.
     * Thus, condition 1 is false. However, a circle
     * with radius 1.1 can also contain three of these points,
     * thus condition 2 is true.
     */ 
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

    /*
     * Positive test
     * No circle with radius 0.9 can contain three of these 
     * points, but a circle with radius 1.1 can, with regards
     * to A_PTS and B_PTS separated points.
     */
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

    /**
     * Out of bounds.
     * If numpoints < 5, lic14 should return false
     */
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

    /**
     * Positve case.
     * lic14 should return true if there is a triangle
     * with area greater than AREA1 and a triangle
     * with area smaller than AREA2.
     */
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
    
    /**
     * Negative case.
     * lic14 should return false if theres is no triangle
     * larger than AREA1, but there is a triangle smaller
     * than AREA2.
     */
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

    /**
     * Negative case.
     * lic14 should return false if theres is a triangle
     * larger than AREA1, but there is no triangle smaller
     * than AREA2.
     */
    @Test
    void lic14Condition1TrueCondition2False() {
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
