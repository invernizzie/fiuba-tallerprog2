package main.model.edgedetection.impl;

import main.model.edgedetection.PointStroker;
import main.model.edgedetection.Stroke;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Esteban I. Invernizzi
 *         Date 13/05/2010
 */
public class ScanningPointStroker implements PointStroker {

    private List<Stroke> strokes;
    private double snapDistance = 20;

    public void setSnapDistance(double snapDistance) {
        this.snapDistance = snapDistance;
    }

    /**
     * Distribuye en trazos el conjunto de puntos
     */
    public List<Stroke> determineStrokes(Set<Point> points) {
        // TODO Dividir en metodos mas cohesivos
        Iterator<Point> pointIterator = points.iterator();
        if (!pointIterator.hasNext())
            return new ArrayList<Stroke>();

        // Inicializo el primer trazo con el primer punto
        strokes = new ArrayList<Stroke>();
        strokes.add(new Stroke());
        strokes.get(0).addPoint(pointIterator.next());

        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();

            /* Se buscan los trazos que esten a distancia del punto
             * menor o igual a snapDistance */
            java.util.List<Stroke> candidateStrokes = searchCandidates(point);

            Stroke chosenStroke;
            chosenStroke = chooseOrCreateStroke(point, candidateStrokes);
            chosenStroke.addPoint(point);
        }
        return strokes;
    }

    /**
     * Elije entre los candidatos un trazo al que pueda agregarse un
     * punto dado. Si no hay candidatos, crea un nuevo trazo.
     *
     * @param point Punto a agregar a un trazo
     * @param candidateStrokes
     * @return
     */
    private Stroke chooseOrCreateStroke(Point point, List<Stroke> candidateStrokes) {
        Stroke chosenStroke;
        Iterator<Stroke> candidatesIterator = candidateStrokes.iterator();
        // Si no hay ningun trazo cercano, se crea uno nuevo
        if (!candidatesIterator.hasNext()) {
            chosenStroke = new Stroke();
            strokes.add(chosenStroke);
            return chosenStroke;
        }
        /* Si hay alguno, se elije el que menos modifica su
         * "derivada" al agregar el punto. */
        chosenStroke = candidatesIterator.next();
        double minDelta = chosenStroke.deltaDifferenceWith(point);
        while (candidatesIterator.hasNext()) {
            Stroke candidate = candidatesIterator.next();
            if (candidate.deltaDifferenceWith(point) < minDelta) {
                chosenStroke = candidate;
                minDelta = chosenStroke.deltaDifferenceWith(point);
            }
        }
        return chosenStroke;
    }

    /**
     * Busca los trazos que esten a distancia del punto
     * menor o igual a snapDistance.
     *
     * @param point
     * @return Lista de trazos a los que puede pertenecer el punto
     */
    private List<Stroke> searchCandidates(Point point) {
        List<Stroke> candidateStrokes = new ArrayList<Stroke>();
        for (Stroke stroke: strokes)
            if (stroke.distanceTo(point) <= snapDistance)
                candidateStrokes.add(stroke);
        return candidateStrokes;
    }
}
