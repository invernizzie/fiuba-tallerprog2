package model.filters.impl;

import model.filters.Filter;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.*;

public class Invert extends RGBImageFilter implements Filter {
	
	@Override
	public int filterRGB(int x, int y, int rgb) {
		int r = 0xff - (rgb >> 16) & 0xff;
		int g = 0xff - (rgb >> 8) & 0xff;
		int b = 0xff - rgb & 0xff;
		return (0xff000000 | r << 16 | g << 8 | b);
	}

	public Image filter(Image in) {
		Canvas c = new Canvas();
		return c.createImage(new FilteredImageSource(in.getSource(),this));
	}

}
