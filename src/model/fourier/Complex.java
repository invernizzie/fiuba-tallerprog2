package model.fourier;

public class Complex {
  
	private float real;
	private float imaginary;

	public Complex(){
		this.real = 0f;
		this.imaginary = 0f;
	}

	public Complex(float real, float imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}

	public float getReal() {
		return real;
	}

	public void setReal(float real) {
		this.real = real;
	}

	public float getImaginary() {
		return imaginary;
	}

	public void setImaginary(float imaginary) {
		this.imaginary = imaginary;
	}
	
	public float getMagnitude(){
	    return ( (float)Math.sqrt((real*real)+(imaginary*imaginary)) );
	}

	public float getAngle(){
	    float angle = (float)Math.atan( imaginary / real );
	    return ( (angle * 180.0f)/(float)Math.PI );
	}
	  
    public Complex add(Complex cx){
	    return new Complex(real + cx.getReal(), imaginary + cx.getImaginary());
	}

    public Complex mult(Complex cx){
	    float r = (real * cx.getReal()) - (imaginary * cx.getImaginary());
	    float i = (real * cx.getImaginary()) + (cx.getReal() * imaginary);
	    return new Complex(r, i);
	}

	public Complex div(float divisor){
	    float R2 = divisor;
	    float div;
	    div = R2 * R2;
	    float R = (real * R2) / div;
	    float I = (R2 * imaginary) / div;
	    return new Complex(R, I);
	  }

}
