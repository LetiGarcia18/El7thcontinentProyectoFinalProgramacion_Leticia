package clases;

public class CartaEstado extends Carta{
		
	String textoEstado;

	public CartaEstado(int id, String numeroCarta, String textoCarta, String rutaImagen, String textoEstado) {
		super(id, numeroCarta, rutaImagen, textoCarta, false);
		this.textoEstado = textoEstado;
		
	}

	public String getTextoEstado() {
		return textoEstado;
	}
}
