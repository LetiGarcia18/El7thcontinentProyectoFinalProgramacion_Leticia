package excepciones;

/**
 * Excepción que se lanza cuando no se encuentra un personaje en la base de datos
 * @author Leticia
 *
 */
public class CharacterDoesNotExistException extends Exception{
	public CharacterDoesNotExistException(String mensaje) {
		super("That character does not exist");
	}
}
