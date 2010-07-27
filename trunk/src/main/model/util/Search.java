package main.model.util;

import java.util.Iterator;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 27/07/2010
 */
public abstract class Search <T> {

    private Iterator<T> iterator;

    protected T chosen = null;

    protected abstract boolean isPreferred(T candidate);

    protected final T getChosen() {
        return chosen;
    }

    public Search(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    public T execute() {
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (isPreferred(next)) {
                chosen = next;
            }
        }
        return chosen;
    }
}
