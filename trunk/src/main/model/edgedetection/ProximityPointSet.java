package main.model.edgedetection;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 *         Date 13/05/2010
 */
public interface ProximityPointSet {

    Point popNearestTo(Stroke stroke, double maxDistance);
    Point popNearestTo(Stroke stroke);
    Point peekNearestTo(Stroke stroke, double maxDistance);
    Point peekNearestTo(Stroke stroke);
    Point popNearestTo(Point point, double maxDistance);
    Point popNearestTo(Point point);
    Point peekNearestTo(Point point);
    void add(Point point);
    boolean isEmpty();
}
