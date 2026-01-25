package com.decide.app.calculators;

import com.decide.app.model.Point;
import com.decide.app.model.Parameters;
import com.decide.app.model.DistanceMatrix;

public class CMV {

    private int numpoints;
    private Point[] points;
    private Parameters parameters;
    private DistanceMatrix distancematrix;

    public CMV(int numpoints, Point[] points, Parameters parameters) {
        this.numpoints = numpoints;
        this.points = points;
        this.parameters = parameters;
        this.distancematrix = new DistanceMatrix(points);
    }

    public boolean[] calcCmv() {
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

    public boolean lic0() {
        return false;
    }

    public boolean lic1() {
        return false;
    }

    public boolean lic2() {
        return false;
    }

    /**
     * Returns true of there exist a triangle with area greater than AREA1,
     * consisting
     * of three consecutive points. It uses Heron's formula to calculate the area
     * from side lengths.
     */
    public boolean lic3() {
        for (int i = 0; i < points.length - 2; ++i) {
            double length_a = distancematrix.dist(i, i + 1);
            double length_b = distancematrix.dist(i + 1, i + 2);
            double length_c = distancematrix.dist(i, i + 2);

            double semiperimeter = (length_a + length_b + length_c) / 2;
            double area = Math.sqrt(semiperimeter * (semiperimeter - length_a) * (semiperimeter - length_b)
                    * (semiperimeter - length_c));
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
