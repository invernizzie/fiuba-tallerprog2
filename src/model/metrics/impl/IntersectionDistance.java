package model.metrics.impl;

import model.metrics.Distance;
import model.metrics.exceptions.IncompatibleSignsException;

/**
 * @author Esteban I. Invernizzi
 *         Date 24/06/2010
 */
public class IntersectionDistance extends Distance {

    @Override
    protected double doCompute(double[] sign1, double[] sign2) {
        double result = 0;
        
        for (int i = 0; i < sign1.length; i++) {
            result += Math.min(Math.abs(sign1[i]), Math.abs(sign2[i]));
        }
        return result;
    }
}
