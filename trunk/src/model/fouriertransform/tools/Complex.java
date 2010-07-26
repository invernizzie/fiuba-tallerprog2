package model.fouriertransform.tools;

/**
 * 
 * @author Cristian
 * Representación de un número complejo
 *
 */

public class Complex {

	/** Parte real de un número. */
	public float re;

	/** Parte imaginaria de un número. */
	public float im;


	public Complex() {}


	public Complex(float real, float imaginary) {
		re = real;
		im = imaginary;
	}


	public float getMagnitude() {
		return (float) Math.sqrt(re*re + im*im);
	}


	public float getPhase() {
		return (float) Math.atan2(im, re);
	}


	public void setPolar(double r, double theta) {
		re = (float)(r*Math.cos(theta));
		im = (float)(r*Math.sin(theta));
	}


	public String toString() {
		return new String(re + " + " + im + "i");
	}


	public void swapWith(Complex value) {
		float temp = re;
		re = value.re;
		value.re = temp;
		temp = im;
		im = value.im;
		value.im = temp;
	}

}
