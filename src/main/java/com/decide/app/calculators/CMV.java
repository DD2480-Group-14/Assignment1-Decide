package com.decide.app.calculators;

import com.decide.app.model.Point;
import com.decide.app.model.Parameters;
import com.decide.app.model.DistanceMatrix;

public class CMV {

    private int numpoints;
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

    public boolean lic1() {
        return false;
    }

    /**
     * Use the Law of Cosines to calculate the angle
     * between three points.
     */
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

    public boolean lic4() {
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
        return false;
    }

    public boolean lic9() {
        return false;
    }

    public boolean lic10() {
        return false;
    }

    public boolean lic11() {
        return false;
    }

    public boolean lic12() {
        return false;
    }

    public boolean lic13() {
        return false;
    }

    public boolean lic14() {
        return false;
    }

}
