package model.edgedetection;

import model.util.PointUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *
 * Date: 09/05/2010
 */
public class Stroke {

    Deque<Point> points = new ArrayDeque<Point>();

    public Point getFirstPoint() {
        return points.peekFirst();
    }

    public Point getLastPoint() {
        return points.peekLast();
    }

    /**
	 * Agrega el punto al extremo mas cercano del trazo.
	 *
	 * @param point
	 */
	public void addPoint(Point point){
        if (Math.abs(distanceToFirst(point)) < Math.abs(distanceToLast(point)))
            points.addFirst(point);
        else
            points.addLast(point);
	}

    /**
     * Devuelve la distancia del punto al extremo mas cercano del trazo.
     *
     * @param point Punto cuya distancia al trazo se calcula
     */
    public double distanceTo(Point point) {
        return Math.min(distanceToFirst(point), distanceToLast(point));
    }

    private double distanceToFirst(Point point) {
        if (points.isEmpty() || (point == null))
            return Double.MAX_VALUE;
        return Math.abs(points.getFirst().distance(point));
    }

    private double distanceToLast(Point point) {
        if (points.isEmpty() || (point == null))
            return Double.MAX_VALUE;
        return Math.abs(points.getLast().distance(point));
    }

	/**
	 * Devuelve una aproximacion a la derivada direccional al final del trazo, de
	 * agregarse el punto pasado por parametro. El calculo se realiza sobre el
	 * extremo del trazo mas cercano al punto.
	 *
	 * @param point
	 */
	public double deltaDifferenceWith(Point point){
        // TODO
        return distanceTo(point);
	}

    /**
     * Dibuja el trazo en un Graphics, como tramos rectos entre punto y punto.
     * @param graphics
     * @param color
     */
    public void paint(Graphics graphics, Color color) {
        graphics.setColor(color);
        paintAsLines(graphics);
        //paintAsDots(graphics);
    }

    private void paintAsLines(Graphics graphics) {
        Iterator<Point> pointIterator = points.iterator();
        Point firstPoint = pointIterator.next(), secondPoint;
        while (pointIterator.hasNext()) {
            secondPoint = pointIterator.next();
            graphics.drawLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);
            firstPoint = secondPoint;
        }
        // Descomentar lo siguiente para dibujar cerrado 
        /*
        if (points.size() > 1) {
            secondPoint = points.getFirst();
            graphics.drawLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);
        }
        */
    }

    private void paintAsDots(Graphics graphics) {
        Iterator<Point> pointIterator = points.iterator();
        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();
            graphics.fillOval(point.x-2, point.y-2, 4, 4);
        }
    }

    public Point getFirstDirection() {
        // Calcula la direccion entre el primero y el segundo restandolos.
        Point first = points.pollFirst();
        if (first == null)
            return new Point(0 ,0);
        Point second = points.peekFirst();
        points.addFirst(first);
        if (second == null)
            return new Point(0 ,0);

        return PointUtils.difference(first, second);
    }

    public Point getLastDirection() {
        // Calcula la direccion entre el ultimo y el anteultimo restandolos.
        // Calcula la direccion entre el primero y el segundo restandolos.
        Point last = points.pollLast();
        if (last == null)
            return new Point(0 ,0);
        Point penultimate = points.peekLast();
        points.addLast(last);
        if (penultimate == null)
            return new Point(0 ,0);

        return PointUtils.difference(last, penultimate);
    }

    /**
     * Duplica el trazo en espejo.
     * Mas precisamente, aplica un eje de simetria de linea vertical,
     * sobre la coordenada X del punto mas a la derecha del trazo.
     */
    public void mirror() {
        if ((points == null) || (points.size() < 2))
            return;

        int rightmost = rightmostEnd();
        int symmetryLimit;
        List<Point> duplicated = new ArrayList<Point>();
        Iterator<Point> pointIterator;
        if (rightmost == FIRST_END)
            pointIterator = points.iterator();
        else
            pointIterator = points.descendingIterator();

        symmetryLimit = 2 * pointIterator.next().x;
        while (pointIterator.hasNext()) {
            Point original = pointIterator.next();
            duplicated.add(new Point(symmetryLimit - original.x, original.y));
        }

        if (rightmost == FIRST_END)
            for (Point duplicate: duplicated)
                points.addFirst(duplicate);
        else
            for (Point duplicate: duplicated)
                points.addLast(duplicate);
    }

    private static final int FIRST_END = 0;
    private static final int LAST_END = 1;

    /**
     * Devuelve 0 si el extremo mas a la derecha
     * es el primero, y 1 si es el ultimo.
     */
    private int rightmostEnd() {
        if (points.peekFirst().x > points.peekLast().x)
            return FIRST_END;
        return LAST_END;
    }

    public static Stroke determineLongest(List<Stroke> strokes) {
        if ((strokes == null) || strokes.isEmpty())
            return null;

        Stroke result = null;
        int maxPointAmount = -1;
        for (Stroke stroke: strokes) {
            if (stroke.points.size() > maxPointAmount) {
                maxPointAmount = stroke.points.size();
                result = stroke;
            }
        }
        return result;
    }
}
