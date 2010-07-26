package control;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public enum MasksEnum {

	SHARPEN("Sharpen"),
    BLUR("Blur"),
    LOW_PASS("Filtro pasa bajos"),
    SMOOTH("Smooth"),
    MID_PASS("Filtro pasa medios"),
    GAUSS_LOW_PASS("Pasa bajos Gaussiano"),
    LAPLACIAN("Laplaciano"),
    PREWITT_1("Operador de Prewitt 1"),
    PREWITT_2("Operador de Prewitt 2");
    
    private String nombre;
    
    private MasksEnum(String nombre) {
    	this.nombre = nombre;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    

}
