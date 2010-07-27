package main.model.metrics;

import main.model.metrics.exceptions.IncompatibleSignsException;

/**
 * @author Esteban I. Invernizzi
 *         Date 24/06/2010
 */
public abstract class Distance {

    public final double compute(double[] sign1, double[] sign2) throws IncompatibleSignsException {
        if ((sign1.length != sign2.length) || (sign1.length < 1))
            throw new IncompatibleSignsException();

        return doCompute(sign1, sign2);
    }

    protected abstract double doCompute(double[] sign1, double[] sign2);
}
