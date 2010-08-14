package main.model.fourier.impl;

import main.model.fourier.AbstractDiscreteFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 31/07/2010
 */
public class SimpleDiscreteFunction <T> extends AbstractDiscreteFunction<T> {

    private List<T> points = new ArrayList<T>();

    public int getDomainSize() {
        return points.size();
    }

    /**
     * Agrega el valor en el siguiente indice
     * @param value Punto a agregar
     */
    public void addValue(T value) {
        points.add(value);
    }

    @Override
    protected T doGetValue(int ordinate) {
        return points.get(ordinate);
    }

}
