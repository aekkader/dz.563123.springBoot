package dz.springBoot.exception;

/**
 * Exception for Registration New User
 * 
 * @author AEK
 *
 */
public class UserRegistrationException extends RuntimeException {

	
	public UserRegistrationException(String message) {
		super(message);
	}
	
	public UserRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}