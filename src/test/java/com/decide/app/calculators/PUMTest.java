package com.decide.app.calculators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.decide.app.model.Connectors;

/**
 * Unit test for PUM computation.
 */
public class PUMTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * Test the PUM calculation by giving only false LICs.
     * The resulting PUM should contain only false elements.
     */
    @Test
    public void pumAllLICFalse() {
        boolean[] cmv = new boolean[15]; // All default to false
        Connectors[][] lcm = new Connectors[15][15];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.ORR;
            }
        }

        PUM pumCalculator = new PUM(cmv, lcm);
        boolean[][] pum = pumCalculator.calculatePUM();
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                assertFalse(pum[i][j]);
            }
        }
    }

    /*
     * Test the PUM calculation by giving only true LICs.
     * The resulting PUM should contain only true elements.
     */
    @Test
    public void pumAllLICTrue() {
        boolean[] cmv = new boolean[15];
        for (int i = 0; i < 15; ++i) {
            cmv[i] = true;
        }

        Connectors[][] lcm = new Connectors[15][15];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.ORR;
            }
        }

        PUM pumCalculator = new PUM(cmv, lcm);
        boolean[][] pum = pumCalculator.calculatePUM();
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                assertTrue(pum[i][j]);
            }
        }
    }

    /**
     * Test the PUM calculation with the ORR connector.
     * Only the first LIC is set to true, which should
     * result in the first row and column of the PUM
     * being true, while the rest being false.
     */
    @Test
    public void pumORRConnetor() {
        boolean[] cmv = new boolean[15];
        cmv[0] = true;

        Connectors[][] lcm = new Connectors[15][15];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.ORR;
            }
        }

        PUM pumCalculator = new PUM(cmv, lcm);
        boolean[][] pum = pumCalculator.calculatePUM();

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                if (i == 0 || j == 0) {
                    assertTrue(pum[i][j]);
                } else {
                    assertFalse(pum[i][j]);
                }
            }
        }
    }

    /**
     * Test the PUM calculation with the NOTUSED Connector.
     * All elements in the PUM should be set to true, since
     * the NOTUSED Connector should set to true.
     */
    @Test
    public void pumNOTUSEDConnector() {
        boolean[] cmv = new boolean[15];

        Connectors[][] lcm = new Connectors[15][15];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.NOTUSED;
            }
        }

        PUM pumCalculator = new PUM(cmv, lcm);
        boolean[][] pum = pumCalculator.calculatePUM();

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                assertTrue(pum[i][j]);
            }
        }

    }
}
