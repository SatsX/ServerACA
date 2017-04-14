package cnam.tchat.aca.server.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import cnam.tchat.aca.server.dao.DAOChannel;
import cnam.tchat.aca.server.messageProcess.MessageProcess;


public class MainServer implements Runnable{
	private Integer port;
	private boolean debug;
	private ServerSocket socketPrincipal;
	private static final Logger LOG = Logger.getLogger(MainServer.class.getName());
	
	public MainServer(Integer port, boolean debug) {
		this.port = port;
		this.debug = debug;
	}
		

	public void run() {		
		LOG.info("Server ACA started !");
		if (debug == true){
			System.out.println("[INFO] : Start server on the port : "+ port);
		}
		try {
			socketPrincipal = new ServerSocket(port);
			
		} catch (IOException e) {
			System.err.println("[ERROR] : connection to socket is impossible ");
			e.printStackTrace();
		}
		
		while(true) {
			try {
				Socket s = socketPrincipal.accept();
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
