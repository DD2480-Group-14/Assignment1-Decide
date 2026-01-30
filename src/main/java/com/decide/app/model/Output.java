package com.decide.app.model;
/**
 * Custom datastructure that contain the CMV, PUM and FUV generated in Main.java
 */

public class Output {
    private boolean[] cmv;
    private boolean[][] pum;
    private boolean[] fuv;
    private boolean launch;

    public Output(boolean[] cmv, boolean[][] pum, boolean[] fuv, boolean launch) {
        this.cmv = cmv;
        this.pum = pum;
        this.fuv = fuv;
        this.launch = launch;
    }

    public boolean[] getCMV() {
        return this.cmv;
    }

    public boolean[][] getPUM() {
        return this.pum;
    }

    public boolean[] getFUV() {
        return this.fuv;
    }

    public boolean getDecision() {
        return this.launch;
    }
}

