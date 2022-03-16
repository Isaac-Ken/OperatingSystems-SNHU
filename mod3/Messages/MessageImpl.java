/**
 * Implementation of the Message interface.
 *
 * Reports the number of characters a .. z A .. Z
 * and the number of digits 0 .. 9
 * 
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */
 
import java.io.Serializable;

public class MessageImpl implements Message, Serializable
{
	private String message;
	private int characterCount;
	private int digitCount;

	public MessageImpl(String message) {
		this.message = message;
	}

	public void setCounts() {
		for (int i = 0; i < message.length(); i++) {
			if (Character.isDigit(message.charAt(i)) )
				++digitCount;
			else if (Character.isLetter(message.charAt(i)) )
				++characterCount;
		}
	}

	public int getCharacterCount() {
		return characterCount;
	}

	public int getDigitCount() {
		return digitCount;
	}

	public String toString() {
		return message;
	}
}
