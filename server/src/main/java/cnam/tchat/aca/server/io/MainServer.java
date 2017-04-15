package cnam.tchat.aca.server.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import cnam.tchat.aca.server.dao.DAOChannel;
import cnam.tchat.aca.server.dao.DAOException;
import cnam.tchat.aca.server.factory.DAOFactory;
import cnam.tchat.aca.server.messageProcess.MessageProcess;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
/**
 * @authors Adrien / Cihat / Arnold
 *
 */
// Class MainServer
public class MainServer implements Runnable{
	
	static final Logger logger = Logger.getLogger(MainServer.class);
	
	private Integer port;
	private ServerSocket socketPrincipal;
	private static HashMap<String, User> userConnected;
	private static ArrayList<Channel> listChannel;
	// Implement MainServer
	public MainServer(Integer port) {
		this.port = port;
		userConnected = new HashMap<>();
		listChannel = new ArrayList<>();
	}
		
	// Implemement run
	public void run() {		
		BasicConfigurator.configure();
		logger.info("Server ACA started !!");
		logger.info("[INFO] : Start server on the port : "+this.port);
		
		try {
			socketPrincipal = new ServerSocket(this.port);
		// Check connection to socket
		} catch (IOException e) {
			logger.error("[ERROR] : connection to socket is impossible ");
			e.printStackTrace();
		}
		
		// list channel from database
		try {
			DAOChannel dc = (DAOChannel) DAOFactory.getDAOChannel();
			listChannel = dc.listAllChannel();
		} catch (DAOException e1) {
			logger.error("Error during listing channel");
			e1.printStackTrace();
		}
		
		while(true) {
			try {
				// Accept socket principal
				Socket s = socketPrincipal.accept();
				logger.info("New user connected " + s );
				// Instance message process
				MessageProcess p = new MessageProcess(s);
				// Start thread to message procces
				Thread t = new Thread(p);            
				t.start();
			// Check accept requests on main socket 	
			} catch (IOException e) {
				logger.error("[ERROR] : Failed to accept requests on main socket.");
				e.printStackTrace();
			
			} finally {
				try {
					if(socketPrincipal == null){
						socketPrincipal.close();
					}
				// Check server socket initialization
				} catch (IOException e) {
					logger.error("Error during Server Socket init.");
					e.printStackTrace();
				}
				
			}
		}
	}


	/**
	 * @return the userConnected
	 */
	public static HashMap<String,User> getUserConnected() {
		return userConnected;
	}


	/**
	 * @param userConnected the userConnected to set
	 */
	public static void setUserConnected(HashMap<String, User> userConnected) {
		MainServer.userConnected = userConnected;
	}


	/**
	 * @return the listChannel
	 */
	public static ArrayList<Channel> getListChannel() {
		return listChannel;
	}


	/**
	 * @param listChannel the listChannel to set
	 */
	public static void setListChannel(ArrayList<Channel> listChannel) {
		MainServer.listChannel = listChannel;
	}
	

}
