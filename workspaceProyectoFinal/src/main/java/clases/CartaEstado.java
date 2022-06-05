package clases;

public class CartaEstado extends Carta{
		
	String textoEstado;

	public CartaEstado(int id, String numeroCarta, String rutaImagen, String textoEstado) {
		super(id, numeroCarta, rutaImagen, false);
		this.textoEstado = textoEstado;
		
	}

	public String getTextoEstado() {
		return textoEstado;
	}

	
	
	

}
