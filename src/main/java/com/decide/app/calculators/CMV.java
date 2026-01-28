package com.decide.app.calculators;

import java.util.Arrays;

import com.decide.app.model.DistanceMatrix;
import com.decide.app.model.Parameters;
import com.decide.app.model.Point;

public class CMV {

    private int numpoints;
    private double[] X;
    private double[] Y;
    private Point[] points;
    private Parameters parameters;
    private DistanceMatrix distanceMatrix;

    public CMV(Point[] points, Parameters parameters) {
        this.numpoints = points.length;
        this.points = points;
        this.parameters = parameters;
        this.distanceMatrix = new DistanceMatrix(points);
    }

    public boolean[] calculateCMV() {
        boolean[] output = new boolean[15];
        output[0] = lic0();
        output[1] = lic1();
        output[2] = lic2();
        output[3] = lic3();
        output[4] = lic4();
        output[5] = lic5();
        output[6] = lic6();
        output[7] = lic7();
        output[8] = lic8();
        output[9] = lic9();
        output[10] = lic10();
        output[11] = lic11();
        output[12] = lic12();
        output[13] = lic13();
        output[14] = lic14();
        return output;
    }

    /**
     * Return the area of a triangle with side lengths specified by
     * the parameters. The area is calculated using Heron's formula.
     */
    public double calculateTriangleArea(double sideLengthA, double sideLengthB, double sideLengthC) {
        double semiperimeter = (sideLengthA + sideLengthB + sideLengthC) / 2;
        double area = Math.sqrt(semiperimeter
                * (semiperimeter - sideLengthA)
                * (semiperimeter - sideLengthB)
                * (semiperimeter - sideLengthC));

        return area;
    }

    private int determineQuadrant(Point p){
        if(p.x >= 0 && p.y >= 0) {
            return 1; // Quadrant I
        }
        if(p.x < 0 && p.y >= 0) {
            return 2; // Quadrant II
        }
        if(p.x <= 0 && p.y < 0) {
            return 3; // Quadrant III
        }
        return 4; // Quadrant IV
    }

    /**
     * Use the Law of Cosines to calculate the angle
     * between three points using only distances
     * between them.
     * @param sideLengthA First side adjacent to vertex
     * @param sideLengthB Second side adjacent to vertex
     * @param sideLengthC Side between first and second point (opposite to vertex)
     * @return Angle between first and second side
     */ 
    public double calculateAngle(double sideLengthA, double sideLengthB, double sideLengthC) {
        double cos_angle = (sideLengthA * sideLengthA
                + sideLengthB * sideLengthB
                - sideLengthC * sideLengthC)
                / (2 * sideLengthA * sideLengthB);

        return Math.acos(cos_angle);
    }

    public boolean lic0() {
        for (int i = 0; i < points.length - 1; i++) {
            double distanceBetweenPoints = distanceMatrix.distances[i][i + 1];
            if (distanceBetweenPoints > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }
	
	private boolean triangleIsObtuse(double sideLengthA, double sideLengthB, double sideLengthC) {
		double[] sideList = { sideLengthA, sideLengthB, sideLengthC };
		Arrays.sort(sideList);
		if (Math.pow(sideList[0], 2) + Math.pow(sideList[1], 2) < Math.pow(sideList[2], 2)) {
			return true;
		} else {
			return false;
		}
	}

	private double calculateSmallestRadiusThreePoints(int i, int j, int k) {
		double sideLengthA = distanceMatrix.dist(i, j);
		double sideLengthB = distanceMatrix.dist(j, k);
		double sideLengthC = distanceMatrix.dist(i, k);
		double area = calculateTriangleArea(sideLengthA, sideLengthB, sideLengthC);
		if (area == 0.0 || triangleIsObtuse(sideLengthA, sideLengthB, sideLengthC)) {
			double sideLongest = Math.max(sideLengthA, Math.max(sideLengthB, sideLengthC));
			double radius = sideLongest / 2;
			return radius;
		} else {
			double radius = (sideLengthA * sideLengthB * sideLengthC) / (4 * area);
			return radius;
		}

	}

	/**
	 * Returns true if there exists at least one set of three consecutive data
	 * points that cannot all be contained within or on a circle of radius RADIUS1.
	 */
	public boolean lic1() {
		if (numpoints < 3) {
			return false;
		}
		for (int i = 0; i < numpoints - 2; ++i) {
			double smallestRadius = calculateSmallestRadiusThreePoints(i, i + 1, i + 2);
			if (smallestRadius > parameters.RADIUS1) {
				return true;
			}
		}
		return false;
	}

    public boolean lic2() {
        if (points.length < 3) {
            return false;
        }

        for (int i = 0; i < points.length - 2; ++i) {
            double sideLengthA = distanceMatrix.dist(i, i + 1);
            double sideLengthB = distanceMatrix.dist(i + 1, i + 2);
            double sideLengthC = distanceMatrix.dist(i, i + 2);

            if (sideLengthA == 0 || sideLengthB == 0) {
                continue;
            }

            double angle = calculateAngle(sideLengthA, sideLengthB, sideLengthC);
            if (angle < Math.PI - parameters.EPSILON || angle > Math.PI + parameters.EPSILON) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true of there exist a triangle with area greater than AREA1,
     * consisting
     * of three consecutive points. It uses Heron's formula to calculate the area
     * from side lengths.
     */
    public boolean lic3() {
        if (points.length < 3) {
            return false;
        }
        for (int i = 0; i < points.length - 2; ++i) {
            double sideLengthA = distanceMatrix.dist(i, i + 1);
            double sideLengthB = distanceMatrix.dist(i + 1, i + 2);
            double sideLengthC = distanceMatrix.dist(i, i + 2);

            double area = calculateTriangleArea(sideLengthA, sideLengthB, sideLengthC);
            if (area > parameters.AREA1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if there exists at least one set of Q_PTS consecutive data points that lie
     * in more than QUADS quadrants.
    */
    public boolean lic4() {
        for (int i = 0; i <= numpoints - parameters.Q_PTS; i++) {
            boolean[] visited = new boolean[4];
            int quadrantCounter = 0;

            for (int j = i; j < i + parameters.Q_PTS; j++) {
                int quadrant = determineQuadrant(points[j]) - 1;

                if (!visited[quadrant]) {
                    visited[quadrant] = true;
                    quadrantCounter++;
                }
            }

            if (quadrantCounter > parameters.QUADS) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is true if there esists at leat one set of two consecutive points such that
     * points[j].x - points[i].x < 0, where i = j-1.
     */
    public boolean lic5() {
        for (int i = 0, j = 1; j < numpoints; i++, j++) {
            if (points[j].x - points[i].x < 0.0) {
                return true;
            }
        }
        return false;
    }

    public boolean lic6() {
        return false;
    }

    public boolean lic7() {
        return false;
    }

    public boolean lic8() {
        if (numpoints < 5)
            return false;

            for (int i = 0; i + parameters.A_PTS + parameters.B_PTS + 2 < numpoints; i++){
                int i1 = i;
                int i2 = i + parameters.A_PTS + 1;
                int i3 = i + parameters.A_PTS + parameters.B_PTS + 2;

                double x1 = X[i1], y1 = Y[i1];
                double x2 = X[i2], y2 = Y[i2];
                double x3 = X[i3], y3 = Y[i3];

                double a = Math.hypot(x2 - x3, y2 - y3);
                double b = Math.hypot(x1 - x3, y1 - y3);
                double c = Math.hypot(x1 - x2, y1 - y2);

                double area = Math.abs(
                    x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2
                    ) / 2.0 );

                if (area == 0.0)
                    continue;

                double radius = (a * b * c) / (4.0 * area);

                if (radius > parameters.RADIUS1)
                    return true;
            }
    
        return false;
            
    }

    public boolean lic9() {
        if(numpoints < 5) {
            return false;
        }
        int C = parameters.C_PTS;
        int D = parameters.D_PTS;
        for(int i = 0; (i + C + D + 2) < numpoints; ++i) {
            double sideLengthA = distanceMatrix.dist(i, i + C + 1);
            double sideLengthB = distanceMatrix.dist(i + C + 1, i + C + D + 2);
            double sideLengthC = distanceMatrix.dist(i, i + C + D + 2);
            if(sideLengthA == 0 || sideLengthB == 0) {
                continue;
            }
            double angle = calculateAngle(sideLengthA, sideLengthB, sideLengthC);

            if(angle < Math.PI - parameters.EPSILON || angle > Math.PI + parameters.EPSILON) {
                return true;
            }
        }
        return false;
    }

    public boolean lic10() {
        return false;
    }

	/**
	 * Returns true if it exists at least one set of two data points, (X[i],Y[i]) 
	 * and (X[j],Y[j]), separated by exactly G PTS consecutive intervening
	 * points, such that X[j] - X[i] < 0. (where i < j ) The condition is
	 * not met when NUMPOINTS < 3.
	 */
	public boolean lic11() {
		int G_PTS = parameters.G_PTS;
		boolean parametersConstraints = 1 <= G_PTS && G_PTS <= numpoints - 2;
		assert parametersConstraints;
		for (int i = 0; i + G_PTS + 1 < numpoints; i++) {
			int j = i + G_PTS + 1;
			if (points[j].x - points[i].x < 0) {
				return true;
			}
		}
		return false;
	}

    public boolean lic12() {
        return false;
    }

    public boolean lic13() {
        if(numpoints < 5) {
            return false;
        }

        boolean condition1 = false;
        boolean condition2 = false;

        double RADIUS1 = parameters.RADIUS1;
        double RADIUS2 = parameters.RADIUS2;
        int A_PTS = parameters.A_PTS;
        int B_PTS = parameters.B_PTS;

        for(int i = 0; i + A_PTS + B_PTS + 2 < numpoints; ++i) {
            int j = i + A_PTS + 1;
            int k = j + B_PTS + 1;
            double smallestRadius = calculateSmallestRadiusThreePoints(i, j, k);
            if(smallestRadius > RADIUS1) {
                condition1 = true;
            }
            if(smallestRadius <= RADIUS2) {
                condition2 = true;
            }

            if(condition1 && condition2) {
                return true;
            }
        }
        
        return false;
    }

    public boolean lic14() {
        if (numpoints < 5) {
            return false;
        }

        int E_PTS = parameters.E_PTS;
        int F_PTS = parameters.F_PTS;
        double AREA1 = parameters.AREA1;
        double AREA2 = parameters.AREA2;
        boolean condition1 = false;
        boolean condition2 = false;

        for (int i = 0; i + E_PTS + F_PTS + 2 < numpoints; i++) {
            int j = i + E_PTS + 1;
            int k = j + F_PTS + 1;

            double sideLengthA = distanceMatrix.dist(i, j);
            double sideLengthB = distanceMatrix.dist(j, k);
            double sideLengthC = distanceMatrix.dist(k, i);
            double area = calculateTriangleArea(sideLengthA, sideLengthB, sideLengthC);

            if (area > AREA1) {
                condition1 = true;
            }
            if (area < AREA2) {
                condition2 = true;
            }

            if (condition1 && condition2) {
                return true;
            }
        }

        return false;
    }

}
