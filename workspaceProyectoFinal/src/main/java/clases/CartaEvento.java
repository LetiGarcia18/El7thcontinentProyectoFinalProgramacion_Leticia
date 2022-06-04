package clases;

public class CartaEvento extends CartaEnMapa{
	
	private boolean visible;
	private int id_cartaAsociada;
	private int id_accionDesactivada;

	public CartaEvento(int id, String numeroCarta, String rutaImagen, short posicionX, short posicionY, int id_cartaAsociada, int id_accionDesactivada) {
		super(id, numeroCarta, rutaImagen, posicionX, posicionY);
		this.visible = false;
		this.id_cartaAsociada = id_cartaAsociada;
		this.id_accionDesactivada = id_accionDesactivada;
	}
	
	

}
