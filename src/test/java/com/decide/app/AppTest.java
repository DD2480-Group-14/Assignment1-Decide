package com.decide.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.decide.app.calculators.*;
import com.decide.app.model.*;
import com.decide.app.Main;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    /**
     * Set up the parameters of the positive test for decide
     * The parameters not explicitly set in this
     * method are used as their default value
     * as seen in Parameters.java
     */
    public Parameters positiveTestParameters() {
        Parameters parameters = new Parameters();
        parameters.EPSILON = 0.0;
        parameters.LENGTH1 = 1.0;
        parameters.LENGTH2 = 5.0;
        parameters.RADIUS1 = 1.0;
        parameters.RADIUS2 = 10;
        parameters.AREA1 = 1.0;
        parameters.AREA2 = 10.0;
        return parameters;
    }

    @Test
    public void decidePositive() {
        double[] xs = new double[11];
        double[] ys = new double[11];
        for(int i = 0; i < 10; ++i) {
            xs[i] = i;
            ys[i] = i * i;
        }

        xs[10] = -1.0;
        ys[10] = -1.0;



        Point[] points = Point.fromArrays(xs, ys);
        Parameters parameters = positiveTestParameters();

        Connectors[][] lcm = new Connectors[15][15];
        for(int i = 0; i < 15; ++i) {
            for(int j = 0; j < 15; ++j) {
                lcm[i][j] = Connectors.ORR;
            }
        }

        boolean[] puv = new boolean[15];
        for(int i = 0; i < 15; ++i) {
            puv[i] = true;
        }

        // These LICs have not yet been implemented
        puv[7] = false;
        puv[8] = false;
        puv[10] = false;

        Main main = new Main(11, points, parameters, lcm, puv);
        assertTrue(main.getDecision());
    }
}
