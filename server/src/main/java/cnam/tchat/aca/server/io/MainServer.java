package cnam.tchat.aca.server.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import cnam.tchat.aca.server.receive.ReceiveProcess;


public class MainServer extends Thread{
	private Integer port;
	private ServerSocket socketPrincipal;
	
	public MainServer(Integer port) {
		this.port = port;
	}
		

	public void run() {
	
		int usersAccepted=0;
		
		System.out.println("You are start SERVER ACA");
		System.out.println("[INFO] : Start server on the port : "+this.port);
		
		try {
			socketPrincipal = new ServerSocket(this.port);
		} catch (IOException e) {
			System.err.println("[ERROR] : connection to socket is impossible ");
			e.printStackTrace();
		}
		
	
		while(true) {
			try {
				
				processSocket(socketPrincipal.accept());
				usersAccepted++;
				
				Command.HOW(usersAccepted);
				
				
				//User user = new User();
				//user.addUser(user);
								
			} catch (IOException e) {
				System.err.println("[ERROR] : Failed to accept requests on main socket.");
				e.printStackTrace();
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void processSocket(Socket clientSocket) throws ServerException, IOException{
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		
		in = clientSocket.getInputStream();
		isr = new InputStreamReader(in, "UTF-8");
		br = new BufferedReader(isr);
		
        String line = br.readLine();
       // System.out.println(line);
	}

}
