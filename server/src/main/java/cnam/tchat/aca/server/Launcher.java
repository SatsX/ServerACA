package cnam.tchat.aca.server;

import cnam.tchat.aca.server.io.MainServer;

/**
 * @author Cihat
 *
 */
public class Launcher {


	public static void main(String[] args) {
		Integer port;
		boolean debug = false;
		MainServer mainServer;
				
		port = new Integer(12345);
		
		mainServer = new MainServer(port, debug);
		mainServer.run();
	}


}
