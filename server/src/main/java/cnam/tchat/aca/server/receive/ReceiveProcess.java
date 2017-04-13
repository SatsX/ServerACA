package cnam.tchat.aca.server.receive;

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


public class ReceiveProcess extends JSONObject implements Runnable{

	private HashMap<String, String> arguments = new HashMap<String, String>();
	private String post;
	private Socket so;
	private ArrayList<String> channels;
	private ArrayList<String> users;

	public ReceiveProcess(Socket cs) {		
		so = cs;		

	}

	public String getArguments() {
		return this.arguments.get("USERS");
	}

	public String getPost() {
		return this.post;
	}
	
	public ArrayList<String> getChannels() {
		return this.channels;
	}


	public ArrayList<String> getUsers() {
		return this.users;
	}



	public void Cancel() throws IOException{
		so.close();
	}

	public void run() {
		
		while(so.isConnected()){
			
	       try {
	    	   	InputStream in = null;
				InputStreamReader isr = null;
				BufferedReader br = null;
				OutputStream out = null;
				OutputStreamWriter osw = null;
				BufferedWriter bw = null;
				
				in = so.getInputStream();
				isr = new InputStreamReader(in, "UTF-8");
				br = new BufferedReader(isr);
				
				out = so.getOutputStream();
				osw = new OutputStreamWriter(out, "UTF-8");
				bw = new BufferedWriter(osw);
			
				String line = br.readLine();
				System.out.println(line);
				
				bw.write(line);
				bw.newLine();
				bw.flush();
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
		
		
	}
}
