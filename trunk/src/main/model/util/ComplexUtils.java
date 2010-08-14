package main.model.util;

import main.model.fourier.Complex;
import main.model.fourier.DiscreteFunction;
import main.model.fourier.exceptions.OutOfBoundsException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 14/08/2010
 */
public class ComplexUtils {

    public static DiscreteFunction<Double> getRealPartFunction(final DiscreteFunction<Complex> fn1) {
        return new DiscreteFunction<Double>() {

            public int getDomainSize() {
                return fn1.getDomainSize();
            }

            public Double getValue(int ordinate) throws OutOfBoundsException {
                return fn1.getValue(ordinate).getReal();
            }
        };
    }

    public static DiscreteFunction<Double> getImaginaryPartFunction(final DiscreteFunction<Complex> fn1) {
        return new DiscreteFunction<Double>() {

            public int getDomainSize() {
                return fn1.getDomainSize();
            }

            public Double getValue(int ordinate) throws OutOfBoundsException {
                return fn1.getValue(ordinate).getImaginary();
            }
        };
    }
}
