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
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MainServer server = new MainServer();
		Thread t = new Thread();
		System.out.println("Start server on the port : " + server.SERVER_PORT + ".");
		server.run(t);


	}

}