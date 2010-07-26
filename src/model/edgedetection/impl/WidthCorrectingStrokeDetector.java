package model.edgedetection.impl;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.edgedetection.PointStroker;
import model.edgedetection.Stroke;
import model.edgedetection.StrokeDetector;
import model.filters.masks.impl.Convolver;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 09/05/2010
 */
public class WidthCorrectingStrokeDetector implements StrokeDetector {

    private Image image;
    private StrokeWidthCorrectionConvolver convolver = new StrokeWidthCorrectionConvolver();
    private Set<Point> points = new HashSet<Point>();
    private PointStroker pointStroker;

    public WidthCorrectingStrokeDetector(Image image, PointStroker pointStroker) {
        this.image = image;
        this.pointStroker = pointStroker;
    }

    public void setThreshold(int threshold) {
        convolver.threshold = threshold;
    }

    public List<Stroke> generateStrokes() {
        detectPoints();
        List<Stroke> strokes = pointStroker.determineStrokes(points);
        return (strokes == null) ? null : Collections.unmodifiableList(strokes);
    }

    private void detectPoints() {
        convolver.filter(image);
    }

    private class StrokeWidthCorrectionConvolver extends Convolver {

        int threshold = 220;

        @Override
        public Image filter(Image in) {
            in.getSource().startProduction(this);

            try {
                convolve();
            } catch (Exception e) {
                System.out.println("Fallo Convolver: " + e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void convolve() {
            // Se recorre la imagen en cuadros de 5x5
            for(int y = 0; y < height/5; y++)
                for(int x = 0; x < width/5; x++) {

                    // Si hay puntos por debajo y por encima del threshold, es contorno
                    boolean belowThreshold = false, aboveThreshold = false;
                    for (int k = 0; k < 5 && !(belowThreshold && aboveThreshold) && (y*5+k < height); k++)
                        for (int j = 0; j < 5 && !(belowThreshold && aboveThreshold) && (x*5+j < width); j++)
                            if (grayValue(getImgPixel(x*5+j, y*5+k)) < threshold)
                                belowThreshold = true;
                            else
                                aboveThreshold = true;

                    if (belowThreshold && aboveThreshold)
                        points.add(new IdentifiablePoint(x*5, y*5));
                }
        }

        private int grayValue(int rgb) {
            int r = (rgb >> 16) & 0xff;
            int g = (rgb >> 8) & 0xff;
            int b = rgb & 0xff;
            return (int) (.56 *g + .33 * r+ .11 * b);
        }


    }
}
