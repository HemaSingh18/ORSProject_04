package in.co.rays.proj4.exception;

/**
 * ApplicationException class. Represents an application-level exception that
 * occurs during the execution of the application.
 * 
 * @author Rays Technologies
 * @version 1.0
 */
public class ApplicationException extends RuntimeException {

	/**
	 * Constructs an ApplicationException with the specified message.
	 * 
	 * @param msg the detail message describing the exception
	 */
	public ApplicationException(String msg) {

		super(msg);

	}

}