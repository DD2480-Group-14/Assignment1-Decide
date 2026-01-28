package com.decide.app.calculators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Unit test for FUV computation.
 */
public class FUVTest {

	/**
	 * Test the FUV calculation by giving only false PUV and PUM
	 * The resulting FUV should contain only true elements.
	 */
	@Test
	public void allPUMFalseAndAllPUVFalse() {
		boolean[] puv = new boolean[15]; // All default to false
		boolean[][] pum = new boolean[15][15];

		FUV fuvCalculator = new FUV(puv, pum);
		boolean[] fuv = fuvCalculator.calculateFUV();
		for (int i = 0; i < 15; ++i) {
			assertTrue(fuv[i]);
		}
	}

	/**
	 * Test the FUV calculation by giving only false PUM and true PUV
	 * The resulting FUV should contain only false elements.
	 */
	@Test
	public void allPUMFalseAndAllPUVTrue() {
		boolean[] puv = new boolean[15];
		Arrays.fill(puv, true);
		boolean[][] pum = new boolean[15][15]; // Defaults to false

		FUV fuvCalculator = new FUV(puv, pum);
		boolean[] fuv = fuvCalculator.calculateFUV();
		for (int i = 0; i < 15; ++i) {
			assertFalse(fuv[i]);
		}
	}

	/**
	 * Test the FUV calculation by giving only true PUM and false PUV
	 * The resulting FUV should contain only true elements.
	 */
	@Test
	public void allPUMTrueAndAllPUVFalse() {
		boolean[] puv = new boolean[15]; // Defaults to false
		boolean[][] pum = new boolean[15][15];
		for (int i = 0; i < pum.length; i++) {
			Arrays.fill(pum[i], true);
		}

		FUV fuvCalculator = new FUV(puv, pum);
		boolean[] fuv = fuvCalculator.calculateFUV();
		for (int i = 0; i < 15; ++i) {
			assertTrue(fuv[i]);
		}
	}

	/**
	 * Test the FUV calculation by giving only true PUM and true PUV
	 * The resulting FUV should contain only true elements.
	 */
	@Test
	public void allPUMTrueAndAllPUVTrue() {
		boolean[] puv = new boolean[15];
		Arrays.fill(puv, true);
		boolean[][] pum = new boolean[15][15];
		for (int i = 0; i < pum.length; i++) {
			Arrays.fill(pum[i], true);
		}

		FUV fuvCalculator = new FUV(puv, pum);
		boolean[] fuv = fuvCalculator.calculateFUV();
		for (int i = 0; i < 15; ++i) {
			assertTrue(fuv[i]);
		}
	}

	/**
	 * Test the FUV calculation where only PUM's diagonal is false
	 * and rest of it is true and only PUV is only true
	 * The resulting FUV should contain only false elements.
	 */
	@Test
	public void pumDiagonalFalseAndAllPUVTrue() {
		boolean[] puv = new boolean[15];
		Arrays.fill(puv, true);
		boolean[][] pum = new boolean[15][15];
		for (int i = 0; i < pum.length; i++) {
			Arrays.fill(pum[i], true);
			pum[i][i] = false;
		}

		FUV fuvCalculator = new FUV(puv, pum);
		boolean[] fuv = fuvCalculator.calculateFUV();

		for (int i = 0; i < 15; ++i) {
			assertFalse(fuv[i]);
		}
	}
}
