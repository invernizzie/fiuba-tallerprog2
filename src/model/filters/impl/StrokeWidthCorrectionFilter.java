package model.filters.impl;

import model.filters.masks.impl.Convolver;

import java.awt.*;
import java.awt.image.MemoryImageSource;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * Date: 08/05/2010
 */
@Deprecated
public class StrokeWidthCorrectionFilter extends Convolver {

    private int threshold = 220;

    public StrokeWidthCorrectionFilter() {}

    public StrokeWidthCorrectionFilter(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void convolve() {

        for(int y = 0; y < height/5; y++)
            for(int x = 0; x < width/5; x++) {

                // Si cada 5 uno cumple, es parte del contorno (esta bien??)
                boolean belowThreshold = false;
                for (int k = 0; k < 5 && !belowThreshold && (y*5+k < height); k++)
                    for (int j = 0; j < 5 && !belowThreshold && (x*5+j < width); j++)
                        if (grayValue(getImgPixel(x*5+j, y*5+k)) < threshold)
                            belowThreshold = true;

                setNewImgPixel(x, y/5, belowThreshold ? 0xff000000 : 0xffffffff);
            }
    }

    private int grayValue(int rgb) {
        int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		return (int) (.56 *g + .33 * r+ .11 * b);
    }

    public Image filter(Image in) {
		in.getSource().startProduction(this);
		newimgpixels = new int[(width/5)*(height/5)];

		try {
			convolve();
		} catch (Exception e) {
			System.out.println("Fallo Convolver: " + e);
			e.printStackTrace();
		}
		Canvas c = new Canvas();
		return c.createImage(new MemoryImageSource(width/5, height/5, newimgpixels, 0, width/5));

	}

}
