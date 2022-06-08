package excepciones;

/**
 * Excepción que se lanza cuando no se encuentra un personaje en la base de datos
 * @author Leticia
 *
 */
public class CharacterDoesNotExistException extends Exception{
	/**
	 * Constructor de la excepción. Se le pasa por parámetros el mensaje que queramos que se vea al lanzar la excepción
	 * @param mensaje
	 */
	public CharacterDoesNotExistException(String mensaje) {
		super(mensaje);
	}
}
