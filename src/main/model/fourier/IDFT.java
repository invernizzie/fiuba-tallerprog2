package main.model.fourier;


import main.model.fourier.exceptions.OutOfBoundsException;
import main.model.fourier.impl.SimpleDiscreteFunction;

import java.util.List;

public class IDFT {

    private DiscreteFunction<Complex> transformedFunction;

	private int numberOfPoints;
	private List<Complex> data;

    public IDFT(DiscreteFunction<Complex> function) {
        transformedFunction = function;
    }

    public DiscreteFunction<Complex> invert() {
        SimpleDiscreteFunction<Complex> result = new SimpleDiscreteFunction<Complex>();
        for (int i = 0; i < transformedFunction.getDomainSize(); i++) {
            try {
                result.addValue(invertPoint(i));
            } catch (OutOfBoundsException e) {
                System.err.println("Se capturo una excepcion que nunca deberia arrojarse, error irrecuperable");
                e.printStackTrace();
            }
        }
        return result;
    }

    private Complex invertPoint(int pointNumber) throws OutOfBoundsException {
        Complex result = new Complex(0.0f,0.0f);

        if ((pointNumber < 0) || (pointNumber >= transformedFunction.getDomainSize()))
            throw new OutOfBoundsException(pointNumber);

        if (pointNumber == 0) {
            for (int n = 0; n < transformedFunction.getDomainSize(); n++) {
                result = result.add(transformedFunction.getValue(n));
            }
        }
        else {
            for (int n = 0; n < transformedFunction.getDomainSize(); n++) {
                double scale = (2 * Math.PI * n * pointNumber) / transformedFunction.getDomainSize();
                Complex newCx = new Complex((float) Math.cos(scale), (float) Math.sin(scale));
                result = result.add(transformedFunction.getValue(n).mult(newCx));
            }
        }
		result = result.div(transformedFunction.getDomainSize());
		return result;
    }

    @Deprecated
	public IDFT(List<Complex> data, int numberOfPoints){
		this.data = data;
		this.numberOfPoints = numberOfPoints;
	}

    @Deprecated
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
