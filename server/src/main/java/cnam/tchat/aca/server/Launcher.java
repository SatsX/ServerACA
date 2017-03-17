/**
 * 
 */
package cnam.tchat.aca.server;

import org.apache.log4j.Logger;

import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.ServerException;

/**
 * @author arnold
 *
 */
public class Launcher {

	private static final Logger LOG = Logger.getLogger(Launcher.class.getName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainServer server = new MainServer();
		
		
		LOG.info("Start server on the port : " + server.SERVER_PORT + ".");
		try {
			server.run();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			LOG.error("ERROR on the server");
		}
	}

}
