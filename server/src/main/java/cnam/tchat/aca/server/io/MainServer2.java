/**
 * 
 */
package cnam.tchat.aca.server.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer2 {
	public static final int SERVER_PORT = 12345;
	
	public static void main(String[] args) throws IOException, ServerException {
		System.out.println("Start server on the port : " + SERVER_PORT + ".");
		while(true){
			run();
		}
		
	}
	
	public static void run() throws ServerException, IOException{
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		processSocket(serverSocket.accept());
	}
		
	private static void processSocket(Socket clientSocket) throws ServerException, IOException{
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
			
		in = clientSocket.getInputStream();
		isr = new InputStreamReader(in, "UTF-8");
		br = new BufferedReader(isr);
		
		System.out.println(br.readLine());
	}

}
