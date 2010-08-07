package test.model.fourier;

import main.model.edgedetection.Stroke;
import main.model.fourier.*;
import main.model.fourier.exceptions.OutOfBoundsException;
import main.model.fourier.impl.SimpleDiscreteComplexFunction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TestDFT{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 11;

		List<Point> originalData = new ArrayList<Point>();
		originalData.add(new Point(12, 34)); //34
		originalData.add(new Point(43, 23)); //23
		originalData.add(new Point(76, 66)); //66
		originalData.add(new Point(12, 1)); //1
		originalData.add(new Point(4, 78)); //78
		originalData.add(new Point(44, 75)); //75
		originalData.add(new Point(84, 44)); //44
		originalData.add(new Point(12, 23)); //23
		originalData.add(new Point(88, 49)); //49
		originalData.add(new Point(18, 29)); //29
		originalData.add(new Point(18, 198)); //198

        SimpleDiscreteComplexFunction originalFunction = new SimpleDiscreteComplexFunction();
        for (Point point: originalData) {
            originalFunction.addValue(new Complex(point.x, point.y));
        }
		
		List<Complex> transformedData = new ArrayList<Complex>();

        /*** Legacy transform ***/
		DFT dft = new DFT(originalData, N);
		for(int i = 0; i < N; i++){
			Complex p = dft.getDFTPoint(i);
			transformedData.add(p);
		}
        /************************/

        /***** New transform ****/
        // TODO
        DiscreteComplexFunction transformedFunction = new DFT(originalFunction).transform();
        for (int i = 0; i < transformedFunction.getDomainSize(); i++) {
            Complex p = transformedData.get(i);
            try {
                if (!p.equals(transformedFunction.getValue(i)))
                    throw new RuntimeException("Las transformadas no coinciden");
            } catch (OutOfBoundsException e) {
                throw new RuntimeException("Las transformadas no coinciden");
            }
        }
        /************************/

        List<Complex> invertedData = new ArrayList<Complex>();

        /**** Legacy inverse ****/
		IDFT idft = new IDFT(transformedData, N);
		for(int i = 0; i < N; i++){
			Complex p = idft.getIDFTPoint(i);
			System.out.println(Math.round(p.getReal())); //la parte imaginaria en la inversion, es siempre 0
            assertEquals(originalData.get(i).y, Math.round(p.getReal()));
            assertEquals(0, Math.round(p.getImaginary()));
            invertedData.add(p);
		}
        /************************/

        /***** New inverse ******/
        DiscreteComplexFunction invertedFunction = new IDFT(transformedFunction).invert();
        for (int i = 0; i < invertedFunction.getDomainSize(); i++) {
            Complex p = invertedData.get(i);
            try {
                if (!p.equals(invertedFunction.getValue(i)))
                    throw new RuntimeException("Las inversiones no coinciden");
            } catch (OutOfBoundsException e) {
                throw new RuntimeException("Las inversiones no coinciden");
            }
        }
        /************************/
	}

    public static void  assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual))
            throw new RuntimeException(
                    "Se esperaba " + expected.toString() + " y se obtuvo " + actual.toString());
    }

}
