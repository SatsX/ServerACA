package cnam.tchat.aca.server.messageProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONObject;

import cnam.tchat.aca.server.command.Command;
import cnam.tchat.aca.server.command.Connect;
import cnam.tchat.aca.server.command.Exit;
import cnam.tchat.aca.server.command.Join;
import cnam.tchat.aca.server.command.Quit;

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
		String originalMessage;
		String msg = null;
		
		while (msg == null) {
			try {
				// We recover the message send to us by the server
				originalMessage = br.readLine();
				System.out.println("Reading : " + originalMessage);
				JSONObject jsonMessage = new JSONObject(originalMessage);
				if(jsonMessage.getString("post").startsWith("#"))
				{
					Command command;
					switch(jsonMessage.getString("post")){
						case "#CONNECT" :
							command = new Connect(jsonMessage.getJSONArray("args"));
							break;
						case "#JOIN" :
							command = new Join(jsonMessage.getJSONArray("args"));
							//String User = jsonMessage.getString("nickname");
							//List listChannelUser = new LinkedList();
							//listChannelUser.add(User);
							//listChannelUser.add("Gabardin");
							//System.out.println(listChannelUser);
							break;
						case "#EXIT" :
							command = new Exit();
							break;
						case "#QUIT" :
							command = new Quit();
							break;
						default :
							JSONObject messageToSend = new JSONObject();
							jsonMessage.put("nickname", "server");
							jsonMessage.put("post", "Command not found");
							return messageToSend.toString();
					}
					msg = command.takeDecision();
				} else {
					jsonMessage.put("nickname", jsonMessage.getString("nickname"));
					jsonMessage.put("post", jsonMessage.getString("post"));
					originalMessage = jsonMessage.toString();
					return originalMessage;
				}
			} catch (IOException e){
				System.err.println("Error during receive process");
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
