package com.decide.app.calculators;

import java.util.Objects;

public class FUV {
	private boolean[][] pum;
	private boolean[] puv;

	private void validateInput() {
        Objects.requireNonNull(puv, "puv cannot be null");
        Objects.requireNonNull(pum, "pum cannot be null");

		if (puv.length != 15) {
			throw new IllegalArgumentException("PUV must contain exactly 15 elements.");
		}

		if (pum.length != 15) {
			throw new IllegalArgumentException("PUM must have exactly 15 rows.");
		}

		for (int i = 0; i < pum.length; i++) {
			if (pum[i].length != 15) {
				throw new IllegalArgumentException(
						"Each PUM row must have exactly 15 columns.");
			}
		}

		for (int i = 0; i < 15; i++) {
			for (int j = i; j < 15; j++) {
				if (pum[i][j] != pum[j][i]) {
					throw new IllegalArgumentException("PUM must be symmetric.");
				}
			}
		}
	}

	public FUV(boolean[] puv, boolean[][] pum) {
		this.puv = puv;
		this.pum = pum;
		validateInput();
	}

	private boolean isPumRowAllTrue(boolean[] pumRow) {
		for (int i = 0; i < pumRow.length; i++) {
			if (!pumRow[i]) {
				return false;
			}
		}
		return true;
	}

	public boolean[] calculateFUV() {
		boolean[] fuv = new boolean[15];
		for (int i = 0; i < fuv.length; i++) {
			boolean[] pumRow = pum[i];
			boolean puvIsFalse = !puv[i];
			if (puvIsFalse || isPumRowAllTrue(pumRow)) {
				fuv[i] = true;
			} else {
				fuv[i] = false;
			}
		}
		return fuv;
	}
}
