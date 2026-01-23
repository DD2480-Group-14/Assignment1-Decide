package com.decide.app.model;

public class DistanceMatrix {
    public double[][] distances;

    public DistanceMatrix(Point[] points) {
        distances = new double[points.length][points.length];
        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j) {
                double x_dist = (points[i].x - points[j].x) * (points[i].x - points[j].x);
                double y_dist = (points[i].y - points[j].y) * (points[i].y - points[j].y);
                distances[i][j] = Math.sqrt(x_dist + y_dist);
            }
        }
    }

    public double dist(int x, int y) {
        return distances[x][y];
    }

}
