package clases;

public class CartaEnMapa extends Carta{
	
	private int posicionX;
	private int posicionY;

	public CartaEnMapa(int id, String numeroCarta, String rutaImagen, int posicionX, int posicionY) {
		super(id, numeroCarta, rutaImagen);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	
	
	
	

}
