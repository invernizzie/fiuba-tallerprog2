package main.model.edgedetection;

import java.util.List;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 09/05/2010
 */
public interface StrokeDetector {

    /**
	 * Devuelve una lista de trazos detectados en la imagen.
	 */
	public List<Stroke> generateStrokes();
}
