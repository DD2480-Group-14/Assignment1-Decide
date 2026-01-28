package com.decide.app;

import com.decide.app.calculators.*;
import com.decide.app.model.*;

public class Main {
    private int numpoints;
    private Point[] points;
    private Parameters parameters;
    private Connectors[][] lcm;
<<<<<<< HEAD
    private boolean[] puv;<<<<<<<HEAD

    =======

    >>>>>>>6e0736b (feat: add file containing decide method)

=======
    private boolean[] puv;
    
>>>>>>> 31ff061 (fix: remove unused launch variable)
    public Main(int numpoints, Point[] points, Parameters parameters, Connectors[][] lcm, boolean[] puv) {
        this.numpoints = numpoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
    }

    <<<<<<<HEAD

    public boolean getDecision() {
=======
>>>>>>> 6e0736b (feat: add file containing decide method)
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

    <<<<<<<HEAD

    public void decide() {
        boolean launch = getDecision();
        if(launch) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    =======>>>>>>>6e0736b (feat: add file containing decide method)
}
