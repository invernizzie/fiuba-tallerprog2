package test.model.fourier;

import main.model.fourier.*;
import main.model.fourier.exceptions.OutOfBoundsException;
import main.model.fourier.impl.SimpleDiscreteFunction;

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

        SimpleDiscreteFunction<Complex> originalFunction = new SimpleDiscreteFunction<Complex>();
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
        DiscreteFunction<Complex> transformedFunction = new DFT(originalFunction).transform();
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
			System.out.println(Math.round(p.getReal()));
            assertEquals(new Integer(originalData.get(i).y).longValue(), Math.round(p.getReal()));
            assertEquals(0L, Math.round(p.getImaginary())); // La parte imaginaria en la inversion debe ser siempre 0
            invertedData.add(p);
		}
        /************************/

        /***** New inverse ******/
        DiscreteFunction<Complex> invertedFunction = new IDFT(transformedFunction).invert();
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
