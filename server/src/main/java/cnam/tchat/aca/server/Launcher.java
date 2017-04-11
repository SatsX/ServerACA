package cnam.tchat.aca.server;


import java.io.IOException;
import java.net.ServerSocket;

import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.ServerException;

/**
 * @author Cihat
 *
 */
public class Launcher {


	public static void main(String[] args) {
		Integer port;
		MainServer mainServer;
				
		port = new Integer(12345);
		
		mainServer = new MainServer(port);
		mainServer.start();
	}


}
