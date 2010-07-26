package model.util;

import model.edgedetection.impl.IdentifiablePoint;

import java.awt.Point;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 29/05/2010
 */
public class PointUtils {

    public static double absDistance(Point p1, Point p2) {
        return Math.abs(p1.distance(p2));
    }

    public static Point difference(Point reference, Point point) {
        return new IdentifiablePoint(
                point.x - reference.x,
                point.y - reference.y );
    }

    public static double dotProduct(Point p1, Point p2) {
        if ((p1 == null) || (p2 == null))
            return 0;
        return p1.x * p2.x + p1.y * p2.y;
    }

    public static Point interpolateOnX(int x, Point p1, Point p2) {

        Point leftmost = (p1.x < p2.x) ? p1 : p2;
        Point rightmost = (leftmost == p2) ? p1 : p2;

        if (leftmost.x == rightmost.x)
            return new IdentifiablePoint(x, (leftmost.y + rightmost.y) / 2);

        double slope = ((double)(rightmost.y - leftmost.y)) / (rightmost.x - leftmost.x);
        return new IdentifiablePoint(x, (int)((x - leftmost.x) * slope + leftmost.y));
    }

    public static Point interpolateOnY(int y, Point p1, Point p2) {

        Point top = (p1.y < p2.y) ? p1 : p2;
        Point bottom = (top == p2) ? p1 : p2;

        if (top.y == bottom.y)
            return new IdentifiablePoint((top.x + bottom.x) / 2, y);

        double slope = ((double)(bottom.x - top.x)) / (bottom.y - top.y);
        return new IdentifiablePoint((int)((y - top.y) * slope + top.x), y);
    }
}
