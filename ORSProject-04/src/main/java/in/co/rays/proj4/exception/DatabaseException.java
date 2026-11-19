package in.co.rays.proj4.exception;

/**
 * DatabaseException class. Represents an exception that occurs during database
 * operations in the application.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class DatabaseException extends RuntimeException {

	/**
	 * Constructs a DatabaseException with the specified message.
	 *
	 * @param msg the detail message describing the database exception
	 */
	public DatabaseException(String msg) {

		super(msg);

	}

}