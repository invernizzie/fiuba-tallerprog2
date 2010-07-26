package model.edgedetection;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * @author Esteban I. Invernizzi
 *         Date 13/05/2010
 */
public interface PointStroker {

    List<Stroke> determineStrokes(Set<Point> points);

    void setSnapDistance(double snapDistance);
}
