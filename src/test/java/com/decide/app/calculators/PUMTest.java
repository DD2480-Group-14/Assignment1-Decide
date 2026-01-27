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

    @Test
    public void pumAllLICFalse() {
        boolean[] cmv = new boolean[15]; // All default to false
        Connectors[][] lcm = new Connectors[15][15];
        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.ORR;
            }
        }

        PUM pum = new PUM();
        boolean[][] pumResult = pum.calculatePUM(cmv, lcm);
        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                assertFalse(pumResult[i][j]);
            }
        }
    }

    @Test
    public void pumAllLICTrue() {
        boolean[] cmv = new boolean[15];
        for(int i = 0; i < 15; ++i) {
            cmv[i] = true;
        }

        Connectors[][] lcm = new Connectors[15][15];
        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.ORR;
            }
        }

        PUM pum = new PUM();
        boolean[][] pumResult = pum.calculatePUM(cmv, lcm);
        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                assertTrue(pumResult[i][j]);
            }
        }
    }

    @Test
    public void pumMixed() {
        boolean[] cmv = new boolean[15];
        cmv[0] = true;

        Connectors[][] lcm = new Connectors[15][15];
        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.AND;
            }
        }
        lcm[0][0] = Connectors.AND;
        lcm[1][0] = Connectors.ORR;
        lcm[0][1] = Connectors.ORR;
        lcm[14][14] = Connectors.NOTUSED;

        PUM pum = new PUM();
        boolean[][] pumResult = pum.calculatePUM(cmv, lcm);

        assertTrue(pumResult[0][0]);
        assertTrue(pumResult[1][0]);
        assertTrue(pumResult[0][1]);
        assertTrue(pumResult[14][14]);

        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                if((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 14 && j == 14) || (i == 0 && j == 1)) {
                    continue;
                }
                assertFalse(pumResult[i][j]);
            }
        }
    }
}
