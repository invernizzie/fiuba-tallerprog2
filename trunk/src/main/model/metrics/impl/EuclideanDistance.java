package main.model.metrics.impl;

import main.model.metrics.Distance;

/**
 * @author Esteban I. Invernizzi
 *         Date 24/06/2010
 */
public class EuclideanDistance extends Distance {


    public double doCompute(double[] sign1, double[] sign2) {
        double result = 0;

        for (int i = 0; i < sign1.length; i++) {
            result += (sign2[i] - sign1[i]) * (sign2[i] - sign1[i]);
        }
        return Math.sqrt(result);
    }
}
