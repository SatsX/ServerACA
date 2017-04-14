package cnam.tchat.aca.server.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import cnam.tchat.aca.server.messageProcess.MessageProcess;


public class MainServer implements Runnable{
	private Integer port;
	private ServerSocket socketPrincipal;
	
	public MainServer(Integer port) {
		this.port = port;
		
	}
		

	public void run() {		
		System.out.println("SERVER ACA is start");
		System.out.println("[INFO] : Start server on the port : "+this.port);
		
		try {
			socketPrincipal = new ServerSocket(this.port);
			
		} catch (IOException e) {
			System.err.println("[ERROR] : connection to socket is impossible ");
			e.printStackTrace();
		}
		
		while(true) {
			try {
				Socket s = socketPrincipal.accept();
				System.out.println("New user connected " + s );
				MessageProcess p = new MessageProcess(s);
				Thread t = new Thread(p);            
				t.start();
								
			} catch (IOException e) {
				System.err.println("[ERROR] : Failed to accept requests on main socket.");
				e.printStackTrace();
			
			}
		}
	}
	

}
