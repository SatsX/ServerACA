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

import org.apache.log4j.Logger;

/**
 * @author Arnold
 *
 */
public class MainServer {
	
	private static final Logger LOG = Logger.getLogger(MainServer.class.getName());
	public static final int SERVER_PORT = 12345;

	
	public static void run() throws ServerException{
		// TODO Auto-generated constructor stub
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(SERVER_PORT);
			while(true){
				
					processSocket(server.accept());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ServerException("Error during Socket connection.", e);
		}finally{
			try{
				if(server != null)
					server.close();
			} catch (IOException e){
				LOG.error("Error during SocketServer INIT.", e);
			}
		}
	}
	
	private static void processSocket(Socket clientSocket) throws ServerException{
		ServerSocket server = null;
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			in = clientSocket.getInputStream();
			isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);
			
			System.out.println(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ServerException("Error during writing data on System.out.println()", e);
		} finally {
			try {
				if( in != null)
					in.close();
				if( isr != null)
					isr.close();
				if( br != null)
					br.close();
			} catch (IOException e) {
				LOG.error("Error during streams closing", e);
			}
		}
	}

}
