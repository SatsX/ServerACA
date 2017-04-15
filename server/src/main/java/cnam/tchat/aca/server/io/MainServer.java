package cnam.tchat.aca.server.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import cnam.tchat.aca.server.messageProcess.MessageProcess;


public class MainServer implements Runnable{
	private Integer port;
	private ServerSocket socketPrincipal;
	private static ArrayList<User> userConnected;
	private static ArrayList<Channel> listChannel;
	
	public MainServer(Integer port) {
		this.port = port;
		
	}
		

	public void run() {		
		System.out.println("Server ACA started !!");
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
			
			} finally {
				try {
					if(socketPrincipal == null){
						socketPrincipal.close();
					}
				} catch (IOException e) {
					System.err.println("Error during Server Socket init.");
					e.printStackTrace();
				}
				
			}
		}
	}


	/**
	 * @return the userConnected
	 */
	public static ArrayList<User> getUserConnected() {
		return userConnected;
	}


	/**
	 * @param userConnected the userConnected to set
	 */
	public static void setUserConnected(ArrayList<User> userConnected) {
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
