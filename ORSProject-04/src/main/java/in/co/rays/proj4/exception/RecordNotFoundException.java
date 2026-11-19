package in.co.rays.proj4.exception;

/**
 * RecordNotFoundException class. Represents an exception that occurs when the
 * requested record is not found in the database.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class RecordNotFoundException extends RuntimeException {

	/**
	 * Constructs a RecordNotFoundException with the specified message.
	 *
	 * @param msg the detail message describing the record not found exception
	 */
	public RecordNotFoundException(String msg) {

		super(msg);

	}

}