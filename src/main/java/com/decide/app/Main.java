package com.decide.app;

import com.decide.app.calculators.*;
import com.decide.app.model.*;

public class Main {
    private int numpoints;
    private Point[] points;
    private Parameters parameters;
    private Connectors[][] lcm;
    private boolean[] puv;

    private void validateInput() {
        if(points == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }

        if(parameters == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if(lcm == null) {
            throw new IllegalArgumentException("LCM cannot be null");
        }

        if(puv == null) {
            throw new IllegalArgumentException("PUV cannot be null");
        }

        for(int i = 0; i < points.length; ++i) {
            if(points[i] == null) {
                throw new IllegalArgumentException("A point cannot be null");
            }
        }

        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                if(lcm[i][j] == null) {
                    throw new IllegalArgumentException("A connector cannot be null");
                }
            }
        }
    }

    public Main(int numpoints, Point[] points, Parameters parameters, Connectors[][] lcm, boolean[] puv) {
        this.numpoints = numpoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
        validateInput();
    }

    public Output getOutput() {

        boolean[] cmv = new CMV(points, parameters).calculateCMV();
        boolean[][] pum = new PUM(cmv, lcm).calculatePUM();
        boolean[] fuv = new FUV(puv, pum).calculateFUV();

        for(int i = 0; i < 15; ++i) {
            if(!fuv[i]) {
                return new Output(cmv, pum, fuv, false);
            }
        }

        return new Output(cmv, pum, fuv, true);
    }

    public void decide() {
        Output output = getOutput();
        if(output.getDecision()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
