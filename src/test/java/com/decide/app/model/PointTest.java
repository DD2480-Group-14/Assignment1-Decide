package com.decide.app.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Unit test for Point dataclass.
 */
public class PointTest {
	@Test
	public void pointNotEqualToDifferentType() {
		Point a = new Point(1, 2);
		int b = 5;
		boolean equal = a.equals(b);
		assertFalse(equal);
	}

	@Test
	public void pointsAreEqual() {
		Point a = new Point(1, 2);
		Point b = new Point(1, 2);
		boolean equal = a.equals(b);
		assertTrue(equal);
	}
	
	@Test
	public void pointsAreTheSame() {
		Point a = new Point(1, 2);
		boolean equal = a.equals(a);
		assertTrue(equal);
	}

	@Test
	public void pointsAreNotEqual() {
		Point a = new Point(1, 4);
		Point b = new Point(3, 2);
		boolean equal = a.equals(b);
		assertFalse(equal);
	}

	@Test
	public void pointNotEqualToNull() {
		Point a = new Point(1, 2);
		boolean equal = a.equals(null);
		assertFalse(equal);
	}
}
