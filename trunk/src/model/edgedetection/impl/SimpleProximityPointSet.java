package model.edgedetection.impl;

import model.edgedetection.ProximityPointSet;
import model.edgedetection.Stroke;
import model.util.PointUtils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Esteban I. Invernizzi
 *         Date 13/05/2010
 */
public class SimpleProximityPointSet implements ProximityPointSet {

    protected Set<Point> points = new HashSet<Point>();

    public Point popNearestTo(Stroke stroke, double maxDistance) {
        Point result = peekNearestTo(stroke, maxDistance);
        return pop(result);
    }

    public Point popNearestTo(Stroke stroke) {
        Point result = peekNearestTo(stroke);
        return pop(result);
    }

    public Point peekNearestTo(Stroke stroke, double maxDistance) {
        Point result = peekNearestTo(stroke);
        if ((result == null) || (stroke.distanceTo(result) > maxDistance))
            return null;
        return result;
    }

    public Point peekNearestTo(Stroke stroke) {
        Point nearestToFirst = peekNearestTo(stroke.getFirstPoint());
        Point nearestToLast = peekNearestTo(stroke.getLastPoint());
        if (nearestToFirst == null)
            return nearestToLast;
        if (PointUtils.absDistance(nearestToFirst, stroke.getFirstPoint()) < PointUtils.absDistance(nearestToLast, stroke.getLastPoint()))
            return nearestToFirst;
        return nearestToLast;
    }

    public Point popNearestTo(Point point, double maxDistance) {
        Point result = peekNearestTo(point);
        if (result == null)
            return null;
        if (PointUtils.absDistance(result, point) > maxDistance )
            return null;
        else
            return pop(result);
    }

    public Point popNearestTo(Point point) {
        Point result = peekNearestTo(point);
        return pop(result);
    }

    public Point peekNearestTo(Point point) {
        if (point == null)
            return null;
        
        Point result = null;
        double minDistance = Double.MAX_VALUE;
        for (Point setPoint: points)
            if (PointUtils.absDistance(setPoint, point) < minDistance) {
                result = setPoint;
                minDistance = PointUtils.absDistance(setPoint, point);
            }
        return result;
    }

    public void add(Point point) {
        if (point != null)
            points.add(point);
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    protected Point pop(Point point) {
        points.remove(point);
        return point;
    }

    /**
     * Selecciona, de entre los puntos del set, aquellos que esten
     * una distancia menor o igual a maxDistance de un punto.
     *
     * @param point Punto alrededor del cual se buscan los cercanos
     * @param maxDistance Distancia maxima entre un punto devuelto y point
     * @return Lista de puntos a distancia menor o igual a maxDistance de point 
     */
    public List<Point> nearbyPointsTo(Point point, double maxDistance) {
        List<Point> result = new ArrayList<Point>();
        for (Point setPoint: points)
            if (PointUtils.absDistance(setPoint, point) <= maxDistance)
                result.add(setPoint);

        return result;
    }
}
