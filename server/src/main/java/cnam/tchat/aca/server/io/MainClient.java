/**
 * 
 */
package cnam.tchat.aca.server.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class MainClient {
	
	private static final String NICKNAME = "Erf...";
	private static final int SERVER_PORT = 12345;
	//private static final String SERVER_HOST = "";


	
	public static void runClient() throws ClientException, IOException{
		// TODO Auto-generated constructor stub
		
				
				Scanner sc = null;
				Socket s = null;
				
				OutputStream out = null;
				OutputStreamWriter osw = null;
				BufferedWriter bw = null;
						
				PrintWriter pw = null;
				sc = new Scanner(System.in);
				
				final String msg = sc.nextLine();
						
				s = new Socket("localhost", 12345);
				out = s.getOutputStream();
				osw = new OutputStreamWriter(out, "UTF-8");
				bw = new BufferedWriter(osw);
						
				bw.write(NICKNAME + " : " + msg);
				bw.flush();
	}
	
	public MainClient() throws ClientException {
		// Useless
	}
	
	public static void main(String[] args) throws ClientException, IOException {
		System.out.println("Start client");
			runClient();
	}
}

