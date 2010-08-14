package main.model.metrics.impl;

import main.model.fourier.DiscreteFunction;
import main.model.fourier.exceptions.OutOfBoundsException;
import main.model.metrics.ComplexDistance;

/**
 * @author Esteban I. Invernizzi
 *         Date 24/06/2010
 */
public class EuclideanDistance extends ComplexDistance {

    @Override
    protected double doCompute(DiscreteFunction<Double> fn1, DiscreteFunction<Double> fn2) throws OutOfBoundsException {
        double result = 0;

        for (int i = 0; i < fn1.getDomainSize(); i++) {
            result += (fn2.getValue(i) - fn1.getValue(i));
            result *= result; // Se eleva al cuadrado
        }
        return Math.sqrt(result);
    }

}
