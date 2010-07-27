package main.model.filters.impl;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import main.model.filters.ParametricFilter;
import main.control.FilterEnum;

/**
 * @author Esteban I. Invernizzi
 * @created 16/04/2010
 */
public class Binarize extends RGBImageFilter implements ParametricFilter {

    private int umbral = 220;

    public Image filter(Image image) {
        Canvas c = new Canvas();
		return c.createImage(new FilteredImageSource(image.getSource(),this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
        int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		int grayValue = (int) (.56 *g + .33 * r+ .11 * b);
		return (grayValue < umbral) ? 0xff000000 : 0xffffffff;
    }

    private int getUmbral() {
        return umbral;
    }

    private void setUmbral(int umbral) {
        if ((umbral > 255) || (umbral < 0))
            throw new IllegalArgumentException();
        this.umbral = umbral;
    }

	@Override
	public double getValue() {
		return getUmbral();
	}


	@Override
	public void setValue(double value) {
		setUmbral((int)value);
	}

	@Override
	public String getFilterName() {
		return FilterEnum.BINARIZE.getNombre();
	}

	@Override
	public double getDefaultValue() {
		return 220;
	}

	@Override
	public double getBottomLimit() {
		return 1;
	}

	@Override
	public double getTopLimit() {
		return 255;
	}
	
	
	


}
