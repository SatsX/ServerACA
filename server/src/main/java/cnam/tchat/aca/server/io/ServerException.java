/**
 * 
 */
package cnam.tchat.aca.server.io;

/**
* @authors Adrien / Cihat / Arnold
 *
 */
// Class server exception
public class ServerException extends Exception {

	private static final long serialVersionUID = 1L;

	// Implement ServerException 
	public ServerException() {

	}

	/**
	 * @param arg0
	 */
	public ServerException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ServerException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ServerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public ServerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
