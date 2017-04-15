package cnam.tchat.aca.server;

import cnam.tchat.aca.server.io.MainServer;

/**
* @authors Adrien / Cihat / Arnold
 *
 */
// Implement the launcher
public class Launcher {


	public static void main(String[] args) {
		Integer port;
		MainServer mainServer;
		// Port used
		port = new Integer(12345);
		// Instance mainServer with the port
		mainServer = new MainServer(port);
		// Execute the mainServer
		mainServer.run();
	}


}
