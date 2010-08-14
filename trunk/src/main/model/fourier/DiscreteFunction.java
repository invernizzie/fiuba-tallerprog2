package main.model.fourier;

import main.model.fourier.exceptions.OutOfBoundsException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 14/08/2010
 */
public interface DiscreteFunction <T> {
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
    T getValue(int ordinate) throws OutOfBoundsException;
}
