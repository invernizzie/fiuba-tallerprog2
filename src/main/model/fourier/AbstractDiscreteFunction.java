package main.model.fourier;

import main.model.fourier.exceptions.OutOfBoundsException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 31/07/2010
 */
public abstract class AbstractDiscreteFunction <T> implements DiscreteFunction <T> {

    protected void testRange(int ordinate) throws OutOfBoundsException {
        if ((ordinate < 0) || (ordinate >= getDomainSize()))
            throw new OutOfBoundsException(ordinate);
    }

    public final T getValue(int ordinate) throws OutOfBoundsException {
        testRange(ordinate);
        return doGetValue(ordinate);
    }

    protected abstract T doGetValue(int index);
}
