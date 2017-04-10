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


public class MainServer implements Runnable {
	public static final int SERVER_PORT = 12345;
	public void run(Thread t){
		try {
			ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
			processSocket(serverSocket.accept());
			t.start();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
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

	public void run() {
		// TODO Auto-generated method stub
		
	}

}
