package main.control;

public enum FilterEnum{

	INVERT("Invertir"),
	CONTRAST("Contraste"),
	GREY_SCALE("Escala de grises"),
	BINARIZE("Binarizacion");
	
    private String nombre;
    
    private FilterEnum(String nombre) {
    	this.nombre = nombre;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
