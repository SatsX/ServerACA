/**
 * 
 */
package cnam.tchat.aca.server.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient3 {
	public static void main(String[] args) throws UnknownHostException, IOException{
		OutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		
		Socket sockets = new Socket("127.0.0.1", 12345);
		
		out = sockets.getOutputStream();
		osw = new OutputStreamWriter(out, "UTF-8");
		bw = new BufferedWriter(osw);
		
		bw.write("Connexion établi message envoyé !");
		bw.flush();
		
	}
}
