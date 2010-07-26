package model.fourier;


import java.util.List;

public class IDFT {

	private int numberOfPoints;
	private List<Complex> data;
	
	public IDFT(List<Complex> data, int numberOfPoints){
		this.data = data;
		this.numberOfPoints = numberOfPoints;
	}

	public Complex getIDFTPoint(int pointNumber){
		Complex ret = new Complex(0.0f,0.0f);
		if (pointNumber >= 0 && pointNumber < numberOfPoints) {
			if (pointNumber == 0) {
				Complex cx;
				for (int n = 0; n < numberOfPoints; n++) {
					cx = (Complex)data.get(n);
					ret = ret.add( cx );
				}
			}
			else{
				for (int n = 0; n < numberOfPoints; n++) {
					Complex cx = (Complex)data.get(n);
					double scale = (2 * Math.PI * n * pointNumber)/numberOfPoints;
					Complex newCx = new Complex((float)Math.cos( scale ), (float)Math.sin( scale ));
					ret = ret.add(cx.mult(newCx));
				}
			}
		}
		ret = ret.div(numberOfPoints);
		return ret;
	}

}
