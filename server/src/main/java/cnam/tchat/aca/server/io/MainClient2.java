/**
 * 
 */
package cnam.tchat.aca.server.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;


/**
 * @author Arnold
 *
 */
public class MainClient2 {
	
	//private static final Logger LOG = Logger.getLogger(MainClient.class.getName());
	private static final String NICKNAME = "Nick";
	private static final int SERVER_PORT = 12345;
	private static final String SERVER_HOST = "127.0.0.1";
	/**
	 * @throws ClientException 
	 * 
	 */
	
	public static void runClient() throws ClientException{
		// TODO Auto-generated constructor stub
		
				//LOG.info("Init Client");
				
				Scanner sc = null;
				Socket s = null;
				
				OutputStream out = null;
				OutputStreamWriter osw = null;
				BufferedWriter bw = null;
						
				PrintWriter pw = null;
				sc = new Scanner(System.in);
				
				while(true){
					try {
						
						final String msg = sc.nextLine();
						
						s = new Socket(InetAddress.getByName(SERVER_HOST), SERVER_PORT);
						out = s.getOutputStream();
						osw = new OutputStreamWriter(out, "UTF-8");
						bw = new BufferedWriter(osw);
						
						bw.write(NICKNAME + " : " + msg);
						bw.flush();
						
					} catch (IOException e) {
						throw new ClientException("Error during message sending.", e);
					} finally {
						try{
							if(bw != null)
								bw.close();
							if(osw != null)
								osw.close();
							if(out != null)
								out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						try {
							s.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
	}
	public MainClient2() throws ClientException {
		// Useless
	}
	
	public static void Main(String[] args) {
		//LOG.info("Start client");
		
		try{
			MainClient2.runClient();
			System.exit(0);
		}catch (ClientException e){
			//LOG.error("ERROR ON CLIENT");
		}
	}

}
