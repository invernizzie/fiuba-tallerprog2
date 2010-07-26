package model.filters.impl;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import control.FilterEnum;

import model.filters.ParametricFilter;

public class Contrast extends RGBImageFilter implements ParametricFilter {
	
	private double gain = 1.2;

	
	//Evita que los valores multiplicados superen 255
	private int multclamp(int in, double factor){
		in = (int) (in * factor);
		return in > 255 ? 255 : in;
	}
	
	
	private int cont(int in){
		return (in < 128) ? (int) (in/gain) : multclamp(in, gain);
	}
	
	@Override
	public int filterRGB(int x, int y, int rgb) {
		int r = cont((rgb >> 16) & 0xff);
		int g = cont((rgb >> 8) & 0xff);
		int b = cont(rgb & 0xff);
		return (0xff000000 | r << 16 | g << 8 | b);
	}

	public Image filter(Image in) {
		Canvas c = new Canvas();
		return c.createImage(new FilteredImageSource(in.getSource(),this));
	}

	private double getGain() {
		return gain;
	}

	private void setGain(double gain) {
		this.gain = gain;
	}


	@Override
	public double getValue() {
		return getGain();
	}


	@Override
	public void setValue(double value) {
		setGain(value);
	}
	
	@Override
	public String getFilterName() {
		return FilterEnum.CONTRAST.getNombre();
	}


	@Override
	public double getDefaultValue() {
		return 1.2;
	}
	
	@Override
	public double getBottomLimit() {
		return 0.001;
	}

	@Override
	public double getTopLimit() {
		return 40;
	}	
	

}
