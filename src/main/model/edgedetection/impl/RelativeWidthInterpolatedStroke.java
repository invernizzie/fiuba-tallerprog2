package main.model.edgedetection.impl;

import main.model.edgedetection.Stroke;
import main.model.fourier.AbstractDiscreteFunction;
import main.model.fourier.Complex;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 24/07/2010
 */
public class RelativeWidthInterpolatedStroke extends AbstractDiscreteFunction<Complex> {

    private static final int DEFAULT_DOMAIN_SIZE = 300;
    private static final int SCALE = 100;
    
    private Stroke base;
    private int minY;
    private int maxY;
    private int rightmostX;
    private int width;

    RelativeWidthInterpolatedStroke(Stroke base) {
        this.base = base;
        minY = base.determineMinYValue();
        maxY = base.determineMaxYValue();
        rightmostX = base.determineMaxXValue();
        width = rightmostX - base.determineMinXValue();
    }

    public int getDomainSize() {
        return DEFAULT_DOMAIN_SIZE;
    }

    @Override
    protected Complex doGetValue(int index) {
        Point point = base.getPointAtYOrInterpolate(translateYCoordinate(index));
        return new Complex((rightmostX - point.x) * SCALE / width, point.y);
    }

    private int translateYCoordinate(int index) {
        return index * (maxY - minY) + minY;
    }
}
