package main.model.fourier;

public class Complex {
  
	private double real;
	private double imaginary;

	public Complex(){
		this.real = 0f;
		this.imaginary = 0f;
	}

	public Complex(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}
	
	public double getMagnitude(){
	    return Math.sqrt((real * real) + (imaginary * imaginary));
	}

	public double getAngle(){
	    double angle = Math.atan(imaginary / real);
	    return ( (angle * 180.0f)/(float)Math.PI );
	}
	  
    public Complex add(Complex cx){
	    return new Complex(real + cx.getReal(), imaginary + cx.getImaginary());
	}

    public Complex mult(Complex cx){
	    double r = (real * cx.getReal()) - (imaginary * cx.getImaginary());
	    double i = (real * cx.getImaginary()) + (cx.getReal() * imaginary);
	    return new Complex(r, i);
	}

	public Complex div(float divisor){
	    double R2 = divisor;
	    double div;
	    div = R2 * R2;
	    double R = (real * R2) / div;
	    double I = (R2 * imaginary) / div;
	    return new Complex(R, I);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complex)) return false;

        Complex complex = (Complex) o;

        if (Double.compare(complex.imaginary, imaginary) != 0) return false;
        if (Double.compare(complex.real, real) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = real != +0.0d ? Double.doubleToLongBits(real) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = imaginary != +0.0d ? Double.doubleToLongBits(imaginary) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
