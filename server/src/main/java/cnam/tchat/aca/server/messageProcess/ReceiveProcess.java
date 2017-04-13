package cnam.tchat.aca.server.messageProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;


public class ReceiveProcess implements Runnable{
	// Read
	private InputStream in = null;
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private OutputStream out = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;
	private Socket so;

	public ReceiveProcess(Socket cs) {		
		so = cs;		
	}

	public String read() throws IOException{
		String msg = null;
		
		while (msg == null) {
			try {
				// We recover the message send to us by the server
				msg = br.readLine();
				System.out.println("Reading : " + msg);
			} catch (IOException e){
				System.err.println("Error during reading line");
				e.printStackTrace();
			}
		}
		return msg;
	}
		
	public void Cancel() throws IOException{
		so.close();
	}

	public void run() {
		
		while(so.isConnected()){
	   		try {
				in = so.getInputStream();
				isr = new InputStreamReader(in, "UTF-8");
		   		br = new BufferedReader(isr);
		   		while(true){
			   		String msgToClient = this.read();
			   		out = so.getOutputStream();
					osw = new OutputStreamWriter(out, "UTF-8");
					bw = new BufferedWriter(osw);
					
					System.out.println("Writing : " + msgToClient);
					bw.write(msgToClient);
					bw.newLine();
					bw.flush();
		   		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		  		
	   		}
	}
}
