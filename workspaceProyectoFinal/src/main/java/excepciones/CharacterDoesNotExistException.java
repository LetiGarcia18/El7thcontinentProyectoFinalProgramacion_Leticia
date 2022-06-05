package excepciones;

public class CharacterDoesNotExistException extends Exception{
	public CharacterDoesNotExistException(String mensaje) {
		super("That character does not exist");
	}
}
