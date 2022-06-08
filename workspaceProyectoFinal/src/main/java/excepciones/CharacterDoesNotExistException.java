package excepciones;

/**
 * Excepci�n que se lanza cuando no se encuentra un personaje en la base de datos
 * @author Leticia
 *
 */
public class CharacterDoesNotExistException extends Exception{
	/**
	 * Constructor de la excepci�n. Se le pasa por par�metros el mensaje que queramos que se vea al lanzar la excepci�n
	 * @param mensaje
	 */
	public CharacterDoesNotExistException(String mensaje) {
		super(mensaje);
	}
}
