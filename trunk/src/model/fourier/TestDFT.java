package model.fourier;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TestDFT{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 11;
		
		List<Point> dataInteger = new ArrayList<Point>();
		dataInteger.add(new Point(12, 34)); //34
		dataInteger.add(new Point(43, 23)); //23
		dataInteger.add(new Point(76, 66)); //66
		dataInteger.add(new Point(12, 1)); //1
		dataInteger.add(new Point(4, 78)); //78
		dataInteger.add(new Point(44, 75)); //75
		dataInteger.add(new Point(84, 44)); //44
		dataInteger.add(new Point(12, 23)); //23
		dataInteger.add(new Point(88, 49)); //49
		dataInteger.add(new Point(18, 29)); //29
		dataInteger.add(new Point(18, 198)); //198
		
		
		List<Complex> dataComplex = new ArrayList<Complex>();
		
		DFT dft = new DFT(dataInteger, N);
		for(int i = 0; i < N; i++){
			Complex p = dft.getDFTPoint(i);
			dataComplex.add(p);
		}

		IDFT idft = new IDFT(dataComplex, N);
		for(int i = 0; i < N; i++){
			Complex p = idft.getIDFTPoint(i);
			System.out.println(Math.round(p.getReal())); //la parte imaginaria en la inversion, es siempre 0
            assertEquals(dataInteger.get(i).y, Math.round(p.getReal()));
            assertEquals(0, Math.round(p.getImaginary()));
		}
	}

    public static void  assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual))
            throw new RuntimeException(
                    "Se esperaba " + expected.toString() + " y se obtuvo " + actual.toString());
    }

}
