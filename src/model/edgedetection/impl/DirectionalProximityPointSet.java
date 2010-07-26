package model.edgedetection.impl;

import model.edgedetection.Stroke;
import model.util.PointUtils;

import java.awt.Point;
import java.util.List;

/**
 * Extiende SimpleProximityPointSet, teniendo en cuenta al obtener
 * los puntos cercanos a un trazo que coincidan con su sentido.
 * Si ninguno de los puntos disponibles va en el mismo sentido
 * del trazo, se devuelve el mas cercano.
 * 
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 29/05/2010
 */
public class DirectionalProximityPointSet extends SimpleProximityPointSet{
    
    public Point popNearestTo(Stroke stroke, double maxDistance) {
        return pop(peekNearestTo(stroke,maxDistance));
    }

    private Point nearest(Stroke stroke, Point point1, Point point2) {
        if (point1 != null) {
            if (point2 == null)
                return point1;
            if (stroke.distanceTo(point1) < stroke.distanceTo(point2))
                return point1;
            return point2;
        }
        return point2;
    }

    public Point peekNearestTo(Stroke stroke, double maxDistance) {
        /*
         * Para cada extremo:
         * - Seleccionar los puntos mas cercanos a un extremo.
         * - Dividir en los que coinciden con la direccion y los que no.
         * - Encontrar el mas cercano en cada subconjunto.
         * Seleccionar con la siguiente prioridad:
         * - Si ambos tienen coincidentes con la direccion, el de menor distancia.
         * - Si uno solo tiene coincidencia con la direccion, ese.
         * - Si ninguno tiene coincidencia con la direccion, el de menor distancia.
         */
        Point firstEnd = stroke.getFirstPoint();
        Point lastEnd = stroke.getLastPoint();
        List<Point> nearToFirst = nearbyPointsTo(firstEnd, maxDistance);
        List<Point> nearToLast = nearbyPointsTo(lastEnd, maxDistance);
        Point[] firstSubsets = nearestByDirection(nearToFirst, firstEnd, stroke.getFirstDirection());
        Point[] lastSubsets = nearestByDirection(nearToLast, lastEnd, stroke.getLastDirection());

        Point directedNearest = nearest(stroke, firstSubsets[0], lastSubsets[0]);
        Point undirectedNearest = nearest(stroke, firstSubsets[1], lastSubsets[1]);
        return (directedNearest != null) ? directedNearest : undirectedNearest;
    }

    /**
     * Determina (si existen) dos puntos cercanos al de referencia
     * de entre los pasados. Uno coincide con el sentido de la direccion
     * pasada, el otro tiene sentido contrario.
     *
     * @param points Puntos entre los cuales elegir los mas cercanos
     * @param reference Punto respecto del cual se buscan los mas cercanos
     * @param direction Direccion que divide los puntos segun su sentido
     *
     * @return En el indice 0, el punto mas cercano en el sentido de la
     * direccion indicada. En el indice 1, el mas cercano en el sentido
     * opuesto. Si alguno no existiera, se devuelve null.
     */
    private Point[] nearestByDirection(List<Point> points, Point reference, Point direction) {
        Point directedResult = null;
        double minDirectedDistance = Double.MAX_VALUE;
        Point undirectedResult = null;
        double minUndirectedDistance = Double.MAX_VALUE;

        for (Point point: points) {
            Point difference = PointUtils.difference(reference, point);
            // Se proyecta sobre la direccion la diferencia con la referencia
            if (PointUtils.dotProduct(direction, difference) > 0) {
                if (PointUtils.absDistance(reference, point) < minDirectedDistance) {
                    minDirectedDistance = PointUtils.absDistance(reference, point);
                    directedResult = point;
                }
            }
            else {
                if (PointUtils.absDistance(reference, point) < minUndirectedDistance) {
                    minUndirectedDistance = PointUtils.absDistance(reference, point);
                    undirectedResult = point;
                }
            }
        }
        return new Point[] {directedResult, undirectedResult};
    }

    public Point popNearestTo(Stroke stroke) {
        // TODO (No se utiliza)
        return super.popNearestTo(stroke);
    }

    public Point peekNearestTo(Stroke stroke) {
        // TODO (No se utiliza)
        return null;
    }
}
