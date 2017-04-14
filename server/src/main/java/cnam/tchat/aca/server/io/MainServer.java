package cnam.tchat.aca.server.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import cnam.tchat.aca.server.messageProcess.ReceiveProcess;


public class MainServer implements Runnable{
	private Integer port;
	private ServerSocket socketPrincipal;
	HashMap<String, ReceiveProcess> hmp = new HashMap<String, ReceiveProcess>();
	
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
				
				ReceiveProcess p = new ReceiveProcess(s);
				Thread t = new Thread(p);            
				t.start();
								
			} catch (IOException e) {
				System.err.println("[ERROR] : Failed to accept requests on main socket.");
				e.printStackTrace();
			
			}
		}
	}
	

}
