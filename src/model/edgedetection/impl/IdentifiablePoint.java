package model.edgedetection.impl;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 *         Date 13/05/2010
 */
public class IdentifiablePoint extends Point {

    public IdentifiablePoint(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;

        Point p = (Point) o;
        if ((p.x == x) && (p.y == y))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return x + y;
    }
}
