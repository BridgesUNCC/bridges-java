package bridges.validation;

/**
 * Exception indicating invalid CSS values.
 * Examples of uses for this include sizes with invalid units, and invalid
 * colors.
 * @author Sean Gallagher
 *
 */
public class InvalidValueException extends RuntimeException {
	public InvalidValueException(String message) {
		super(message);
	}

	/**
	 * Auto-generated ID
	 */
	private static final long serialVersionUID = 8664177180092591999L;
	
}
