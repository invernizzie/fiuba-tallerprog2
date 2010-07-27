package main.model.edgedetection.impl;

import main.model.edgedetection.PointStroker;
import main.model.edgedetection.ProximityPointSet;
import main.model.edgedetection.Stroke;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Esteban I. Invernizzi
 *         Date 13/05/2010
 */
public class ProximityPointStroker implements PointStroker{

    private ProximityPointSet pointSet = new DirectionalProximityPointSet();
    private List<Stroke> strokes;
    private double snapDistance = 20;

    public List<Stroke> determineStrokes(Set<Point> points) {
        Iterator<Point> pointIterator = points.iterator();
        if (!pointIterator.hasNext())
            return new ArrayList<Stroke>();

        // Inicializo el primer trazo con el primer punto
        strokes = new ArrayList<Stroke>();
        strokes = new ArrayList<Stroke>();
        strokes.add(new Stroke());
        strokes.get(0).addPoint(pointIterator.next());

        while (pointIterator.hasNext())
            pointSet.add(pointIterator.next());

        extendStrokeAndCreateOthers(strokes.get(0));
        
        return strokes;
    }

    private void extendStrokeAndCreateOthers(Stroke stroke) {
        Point point = pointSet.popNearestTo(stroke, snapDistance);
        while (point != null) {
            stroke.addPoint(point);
            point = pointSet.popNearestTo(stroke, snapDistance);
        }
        if (pointSet.isEmpty())
            return;

        Stroke newStroke = new Stroke();
        strokes.add(newStroke);
        newStroke.addPoint(pointSet.popNearestTo(stroke.getFirstPoint()));
        extendStrokeAndCreateOthers(newStroke);
    }

    public void setSnapDistance(double snapDistance) {
        this.snapDistance = snapDistance;
    }
}
