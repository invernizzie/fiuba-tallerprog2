package main.model.fourier.impl;

import main.model.fourier.AbstractDiscreteComplexFunction;
import main.model.fourier.Complex;
import main.model.fourier.DiscreteComplexFunction;
import main.model.fourier.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 31/07/2010
 */
public class SimpleDiscreteComplexFunction extends AbstractDiscreteComplexFunction {

    private List<Complex> points = new ArrayList<Complex>();

    public int getDomainSize() {
        return points.size();
    }

    /**
     * Agrega el valor en el siguiente indice
     * @param value Punto a agregar
     */
    public void addValue(Complex value) {
        points.add(value);
    }

    @Override
    protected Complex doGetValue(int ordinate) {
        return points.get(ordinate);
    }

}
