package clases;

public class CartaEvento extends CartaEnMapa{
	
	
	private int idCartaAsociada;
	private int idAccionDesactivada;

	public CartaEvento(int id, String numeroCarta, String rutaImagen, String textoCarta, byte posicionX, byte posicionY, int idCartaAsociada, int idAccionDesactivada) {
		super(id, numeroCarta, rutaImagen, textoCarta, posicionX, posicionY, false);
		this.idCartaAsociada = idCartaAsociada;
		this.idAccionDesactivada = idAccionDesactivada;
	}

	public int getIdCartaAsociada() {
		return idCartaAsociada;
	}

	public void setIdCartaAsociada(int idCartaAsociada) {
		this.idCartaAsociada = idCartaAsociada;
	}

	public int getIdAccionDesactivada() {
		return idAccionDesactivada;
	}

	public void setIdAccionDesactivada(int idAccionDesactivada) {
		this.idAccionDesactivada = idAccionDesactivada;
	}
	
	
	
	

}
