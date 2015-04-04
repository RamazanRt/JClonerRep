package jcloner;

/**
 * Cloner exception.
 * 
 * @author Faruk BOZAN
 * @since 15.4.3
 * @version 15.4.3
 *
 */
public class ClonerException extends Exception {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Single parameter constructor.
	 * 
	 * @param e
	 *            Throwable instance.
	 */
	public ClonerException(Throwable e) {
		super("An exception occurs during clone operation", e);
	}

}
