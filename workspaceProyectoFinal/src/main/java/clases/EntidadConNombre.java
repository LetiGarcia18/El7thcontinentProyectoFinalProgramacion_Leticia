package clases;

public class EntidadConNombre {
	
	private String nombre;

	public EntidadConNombre(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "EntidadConNombre [nombre=" + nombre + "]";
	}
	
	
	
	

}
