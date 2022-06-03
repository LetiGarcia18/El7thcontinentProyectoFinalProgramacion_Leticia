package clases;

public class CartaEnMapa extends Carta{
	
	private short posicionX;
	private short posicionY;

	public CartaEnMapa(int id, short numeroCarta, String rutaImagen, short posicionX, short posicionY) {
		super(id, numeroCarta, rutaImagen);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(short posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(short posicionY) {
		this.posicionY = posicionY;
	}
	
	
	
	

}
