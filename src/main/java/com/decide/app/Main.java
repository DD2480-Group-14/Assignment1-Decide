package com.decide.app;

import com.decide.app.calculators.*;
import com.decide.app.model.*;

public class Main {
    private int numpoints;
    private Point[] points;
    private Parameters parameters;
    private Connectors[][] lcm;
    private boolean[] puv;
    
    public boolean launch;

    public Main(int numpoints, Point[] points, Parameters parameters, Connectors[][] lcm, boolean[] puv) {
        this.numpoints = numpoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
    }

    public boolean getDecision() {
        boolean[] cmv = new CMV(points, parameters).calculateCMV();
        boolean[][] pum = new PUM(cmv, lcm).calculatePUM();
        boolean[] fuv = new FUV(puv, pum).calculateFUV();

        for(int i = 0; i < 15; ++i) {
            if(!fuv[i]) {
                return false;
            }
        }

        return true;
    }

    public void decide() {
        boolean launch = getDecision();
        if(launch) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

}
