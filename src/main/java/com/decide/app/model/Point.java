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
}