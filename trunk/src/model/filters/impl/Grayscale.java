package model.filters.impl;

import model.filters.Filter;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.*;

public class Grayscale extends RGBImageFilter implements Filter {

	@Override
	public int filterRGB(int x, int y, int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		int k = (int) (.56 *g + .33 * r+ .11 * b);
		return (0xff000000 | k << 16 | k << 8 | k);
	}

	public Image filter(Image in) {
		Canvas c = new Canvas();
		return c.createImage(new FilteredImageSource(in.getSource(),this));
	}

}
