package com.decide.app.model;

public class DistMat {
    public double[][] mat;

    public DistMat() {
        mat = new double[100][100];
    }

    public DistMat(Point[] points) {
        mat = new double[points.length][points.length];

        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j) {
                double x_dist = (points[i].x - points[j].x) * (points[i].x - points[j].x);
                double y_dist = (points[i].y - points[j].y) * (points[i].y - points[j].y);
                mat[i][j] = Math.sqrt(x_dist + y_dist);
            }
        }
    }

    public double dist(int x, int y) {
        return mat[x][y];
    }

}
