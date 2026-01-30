package com.decide.app.calculators;

import java.util.Objects;

import com.decide.app.model.Connectors;

public class PUM {


    boolean[] cmv;
    Connectors[][] lcm;

    public PUM(boolean[] cmv, Connectors[][] lcm) {
        this.cmv = cmv;
        this.lcm = lcm;
        validateInput();
    }

    /*
     * Function that compares the CMV with the LCM to generate PUM.
     */
    public boolean[][] calculatePUM() {
        boolean[][] pum = new boolean[15][15];

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                boolean connectorResult;
                switch(lcm[i][j]) {
                    case AND:
                        connectorResult = cmv[i] && cmv[j];
                        break;
                    case ORR:
                        connectorResult = cmv[i] || cmv[j];
                        break;
                    case NOTUSED:
                        connectorResult = true;
                        break;
                    default:
                        connectorResult = false;
                        break;
                }

                pum[i][j] = connectorResult;
            }
        }

        return pum;
    }

    private void validateInput() {
        Objects.requireNonNull(lcm, "lcm cannot be null");
        Objects.requireNonNull(cmv, "cmv cannot be null");
        
        if (cmv.length != 15) {
            throw new IllegalArgumentException("cmv must contain excactly 15 elements.");
        }

        if (lcm.length != 15) {
            throw new IllegalArgumentException("lcm has the wrong dimensions, must be 15x15.");
        }

        for (int i = 0; i < 15; i++) {
            if (lcm[i].length != 15) {
                throw new IllegalArgumentException("lcm has the wrong dimensions, must be 15x15.");
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = i; j < 15; j++) {
                Objects.requireNonNull(lcm[i][j], "lcm cannot contain a null element");
                if (lcm[i][j] != lcm[j][i]) {
                    throw new IllegalArgumentException("lcm is not symmetric.");
                }
            }
        }
    }
}
