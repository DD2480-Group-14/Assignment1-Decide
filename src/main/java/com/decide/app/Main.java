package com.decide.app;

import java.util.Objects;

import com.decide.app.calculators.*;
import com.decide.app.model.*;

public class Main {
    private int numpoints;
    private Point[] points;
    private Parameters parameters;
    private Connectors[][] lcm;
    private boolean[] puv;

    private void validateInput() {
        Objects.requireNonNull(points, "Points cannot be null");
        // We do not check if the attributes of parameters are
        // null since they are primitives
        Objects.requireNonNull(parameters, "Parameters cannot be null");
        Objects.requireNonNull(lcm, "lcm cannot be null");
        Objects.requireNonNull(puv, "puv cannot be null");
        
        for(int i = 0; i < points.length; ++i) {
            Objects.requireNonNull(points[i], "points cannot contain a null pointer");
        }

        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                Objects.requireNonNull(lcm[i][j], "lcm cannot contain a null element");
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
