package com.decide.app.calculators;

import com.decide.app.model.Connectors;

public class PUM {


    boolean[] cmv;
    Connectors[][] lcm;

    public PUM(boolean[] cmv, Connectors[][] lcm) {
        this.cmv = cmv;
        this.lcm = lcm;
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
}
