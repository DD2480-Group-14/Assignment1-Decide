package com.decide.app.model;
/**
 * Custom datastructure that contain the coordiate information of a planar point.
 */

public class Point {
    
    public final double x;
    public final double y;

    /**
     * Creates a point with the given x and y coordinates.
     * @param x x-position
     * @param y y-position
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
			return true;
		}
		if (!(object instanceof Point)) {
			return false;
		}
        Point otherPoint = (Point) object;
        if (this.x == otherPoint.x && this.y == otherPoint.y) {
                return true;
        } else {
                return false;
        }
    }

    @Override 
    public int hashCode() {
	    return java.util.Objects.hash(x ,y);
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
