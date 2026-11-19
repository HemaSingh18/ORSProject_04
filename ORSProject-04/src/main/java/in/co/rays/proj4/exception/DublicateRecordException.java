package in.co.rays.proj4.exception;

/**
 * DublicateRecordException class. Represents an exception that occurs when an
 * attempt is made to insert a duplicate record into the database.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class DublicateRecordException extends RuntimeException {

	/**
	 * Constructs a DublicateRecordException with the specified message.
	 *
	 * @param msg the detail message describing the duplicate record exception
	 */
	public DublicateRecordException(String msg) {

		super(msg);

	}

}