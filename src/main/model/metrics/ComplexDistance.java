package main.model.metrics;

import main.model.fourier.Complex;
import main.model.fourier.DiscreteFunction;
import main.model.fourier.exceptions.OutOfBoundsException;
import main.model.metrics.exceptions.IncompatibleSignaturesException;
import main.model.util.ComplexUtils;

/**
 * @author Esteban I. Invernizzi
 *         Date 24/06/2010
 */
public abstract class ComplexDistance {

    public final double compute(DiscreteFunction<Complex> fn1, DiscreteFunction<Complex> fn2) throws IncompatibleSignaturesException {
        if ((fn1.getDomainSize() != fn2.getDomainSize()) || (fn1.getDomainSize() < 1))
            throw new IncompatibleSignaturesException();

        // Se calcula la distancia de la componente real y la imaginaria y se promedian
        try {
            double result = 0;
            result += doCompute(ComplexUtils.getRealPartFunction(fn1), ComplexUtils.getRealPartFunction(fn2));
            result += doCompute(ComplexUtils.getImaginaryPartFunction(fn1), ComplexUtils.getImaginaryPartFunction(fn2));
            return result / 2.0;
        } catch (OutOfBoundsException e) {
            System.err.println("Se arrojara una excepcion que no deberia arrojarse nunca, es un bug");
            e.printStackTrace();
            throw new RuntimeException("Error inesperado", e);
        }
    }

    protected abstract double doCompute(DiscreteFunction<Double> fn1, DiscreteFunction<Double> fn2) throws OutOfBoundsException;
}
