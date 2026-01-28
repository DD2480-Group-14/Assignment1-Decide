package com.decide.app.calculators;

public class FUV {
	private boolean[][] pum;
	private boolean[] puv;

	public FUV(boolean[] puv, boolean[][] pum) {
		this.puv = puv;
		this.pum = pum;
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
