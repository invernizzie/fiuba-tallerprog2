package model.metrics.impl;

import model.metrics.Distance;

/**
 * @author Esteban I. Invernizzi
 *         Date 24/06/2010
 */
public class ChiSquareDistance extends Distance {

    @Override
    protected double doCompute(double[] sign1, double[] sign2) {
        double result = 0;

        for (int i = 0; i < sign1.length; i++) {
            result += (sign1[i] - sign2[i]) / (sign1[i] + sign2[i]);
        }
        return result;
    }
}
