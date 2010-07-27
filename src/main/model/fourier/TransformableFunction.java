package main.model.fourier;

import main.model.fourier.exceptions.OutOfBoundsException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 24/07/2010
 */
public interface TransformableFunction {

    /**
     * @return Cantidad de puntos del dominio de la funcion
     */
    int getDomainSize();

    /**
     *
     * @param index Numero de orden del punto devuelto,
     * debe estar entre 0 y getDomainSize() - 1.
     * @return
     */
    Complex getPoint(int index) throws OutOfBoundsException;
}
