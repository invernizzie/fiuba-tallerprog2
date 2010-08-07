package main.model.fourier;

import main.model.fourier.exceptions.OutOfBoundsException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 24/07/2010
 */
public interface DiscreteComplexFunction {

    /**
     * @return Cantidad de puntos del dominio de la funcion
     */
    int getDomainSize();

    /**
     *
     * @param ordinate Numero de orden del punto devuelto,
     * debe estar entre 0 y getDomainSize() - 1.
     * @return valor de la funcion para la ordenada dada
     */
    Complex getValue(int ordinate) throws OutOfBoundsException;
}
