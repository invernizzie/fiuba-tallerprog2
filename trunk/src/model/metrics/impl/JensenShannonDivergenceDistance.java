package model.metrics.impl;

import model.metrics.Distance;

/**
 * @author Esteban I. Invernizzi
 *         Date 24/06/2010
 */
public class JensenShannonDivergenceDistance extends Distance {

    @Override
    protected double doCompute(double[] sign1, double[] sign2) {
        double result = 0;

        for (int i = 0; i < sign1.length; i++) {
            double term1 = sign1[i] * Math.log10(2 * sign1[i] / (sign1[i] + sign2[i]));
            double term2 = sign2[i] * Math.log10(2 * sign2[i] / (sign1[i] + sign2[i]));
            result += term1 + term2;
        }
        return result;
    }
}
