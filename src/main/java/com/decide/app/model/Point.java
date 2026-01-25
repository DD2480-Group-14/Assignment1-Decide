package com.decide.app.model;
/**
 * Custom datastructure that contain the coordiate information of a planar point.
 */

public class Point {
    

    public double x;
    public double y;

    /**
     * Creates a point with the given x and y coordinates.
     * @param x x-position
     * @param y y-position
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates an array of points from arrays of coordinates.
     * @param xCoords x-coordinates
     * @param yCoords y-coordinates
     * @throws IllegalArgumentException if {@code xCoords.length != yCoords.length}
     */
    public static Point[] fromArrays(double[] xCoords, double[] yCoords) {
        if (xCoords.length != yCoords.length) {
            throw new IllegalArgumentException("Arrays must be of the same length");
        }

        Point[] points = new Point[xCoords.length];
        for (int i = 0; i < xCoords.length; i++) {
            points[i] = new Point(xCoords[i], yCoords[i]);
        }
        return points;
    }
}