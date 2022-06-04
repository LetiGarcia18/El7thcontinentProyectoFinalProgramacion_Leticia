package clases;

public class CartaEnMapa extends Carta{
	
	private byte posicionX;
	private byte posicionY;

	public CartaEnMapa(int id, String numeroCarta, String rutaImagen, byte posicionX, byte posicionY, boolean estaInicialmenteEnMesa) {
		super(id, numeroCarta, rutaImagen, estaInicialmenteEnMesa);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	public byte getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(byte posicionX) {
		this.posicionX = posicionX;
	}

	public byte getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(byte posicionY) {
		this.posicionY = posicionY;
	}
	
	
	
	

}
