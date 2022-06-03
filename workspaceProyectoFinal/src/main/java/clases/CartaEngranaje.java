package clases;

public class CartaEngranaje extends CartaInventario{
	
	private int numeroEngranajes;

	public CartaEngranaje(int id, short numeroCarta, String rutaImagen, int numeroEngranajes) {
		super(id, numeroCarta, rutaImagen);
		this.numeroEngranajes = numeroEngranajes;
	}

}
