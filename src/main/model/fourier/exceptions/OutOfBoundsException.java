package main.model.fourier.exceptions;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 24/07/2010
 */
public class OutOfBoundsException extends Exception {

    private int conflictingIndex;

    public OutOfBoundsException(int index) {
        conflictingIndex = index;
    }

    public int getConflictingIndex() {
        return conflictingIndex;
    }
}
