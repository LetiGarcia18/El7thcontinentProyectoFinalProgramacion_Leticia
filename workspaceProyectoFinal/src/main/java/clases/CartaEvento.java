package clases;

public class CartaEvento extends CartaEnMapa{
	
	private boolean visible;
	private int id_cartaAsociada;
	private int id_accionDesactivada;

	public CartaEvento(int id, String numeroCarta, String rutaImagen, int posicionX, int posicionY, int id_cartaAsociada, int id_accionDesactivada) {
		super(id, numeroCarta, rutaImagen, posicionX, posicionY);
		this.visible = false;
		this.id_cartaAsociada = id_cartaAsociada;
		this.id_accionDesactivada = id_accionDesactivada;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getId_cartaAsociada() {
		return id_cartaAsociada;
	}

	public void setId_cartaAsociada(int id_cartaAsociada) {
		this.id_cartaAsociada = id_cartaAsociada;
	}

	public int getId_accionDesactivada() {
		return id_accionDesactivada;
	}

	public void setId_accionDesactivada(int id_accionDesactivada) {
		this.id_accionDesactivada = id_accionDesactivada;
	}
	
	
	
	

}
