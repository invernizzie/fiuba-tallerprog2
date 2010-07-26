package model.edgedetection.impl;

import model.edgedetection.Stroke;
import model.fourier.Complex;
import model.fourier.TransformableFunction;
import model.fourier.exceptions.OutOfBoundsException;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 24/07/2010
 */
public class InterpolatedVerticalStroke implements TransformableFunction {

    private static final int DEFAULT_DOMAIN_SIZE = 300;

    private Stroke base;
    private int minY;
    private int maxY;

    InterpolatedVerticalStroke(Stroke base) {
        this.base = base;
        this.minY = base.determineMinYValue();
        this.maxY = base.determineMaxYValue();
    }

    public int getDomainSize() {
        return DEFAULT_DOMAIN_SIZE;
    }

    public Complex getPoint(int index) throws OutOfBoundsException {
        testRange(index);
        Point point = base.getPointAtYOrInterpolate(translateYCoordinate(index));
        return new Complex(point.x, point.y);
    }

    private void testRange(int index) throws OutOfBoundsException {
        if ((index < 0) || (index >= getDomainSize()))
            throw new OutOfBoundsException(index);
    }

    private int translateYCoordinate(int index) {
        return index * (maxY - minY) + minY;
    }
}
