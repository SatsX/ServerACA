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
import cnam.tchat.aca.server.io.Channel;
import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.User;

public class MessageProcess implements Runnable{
	// Read
	private InputStream in = null;
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	
	//Write 
	private OutputStream out = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;
	
	private Socket so;
	private boolean isCommand = false;

	public MessageProcess(Socket cs) {	
		so = cs;		
	}

	public String read() throws IOException{
		String originalMessage;
		String msg = null;
		
		while (msg == null) {
			try {
				// We recover the message send to us by the server
				originalMessage = br.readLine();
				if(originalMessage == null){
					break;
				}
				System.out.println("Reading : " + originalMessage);
				JSONObject jsonMessage = new JSONObject(originalMessage);
				if(jsonMessage.getString("post").startsWith("#"))
				{
					Command command;
					isCommand = true;
					switch(jsonMessage.getString("post")){
						case "#CONNECT" :
							command = new Connect(jsonMessage.getJSONArray("args"), jsonMessage.getString("nickname"), so);
							break;
						case "#JOIN" :
							command = new Join(jsonMessage.getJSONArray("args"), jsonMessage.getString("nickname"));
							break;
						case "#EXIT" :
							command = new Exit(jsonMessage.getString("nickname"));
							break;
						case "#QUIT" :
							command = new Quit(jsonMessage.getString("nickname"));
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
		
		if(so.isConnected()){
	   		try {
				in = so.getInputStream();
				isr = new InputStreamReader(in, "UTF-8");
		   		br = new BufferedReader(isr);
		   		while(true){
			   		String msgToClient = this.read();
					if(msgToClient == null){
						break;
					}
			   		out = so.getOutputStream();
					osw = new OutputStreamWriter(out, "UTF-8");
					bw = new BufferedWriter(osw);
					System.out.println("New user : " + so);
					System.out.println("Writing : " + msgToClient);
					if(isCommand){
						bw.write(msgToClient);
						bw.newLine();
						bw.flush();
						isCommand = false;
					} else {
						JSONObject msgToRoom= new JSONObject(msgToClient);
						String senderNickName = msgToRoom.getString("nickname");					
						User u = MainServer.getUserConnected().get(senderNickName);
						
						Channel ch = u.getChannelUser();
						
						for(User tmp : ch.getlUser()){
							System.out.println(tmp);
							System.out.println(ch.getlUser());
							out = tmp.getSocketUser().getOutputStream();
							osw = new OutputStreamWriter(out, "UTF-8");
							bw = new BufferedWriter(osw);
							
							bw.write(msgToClient);
							bw.newLine();
							bw.flush();
						}
												
					}
					
		   		}
			} catch (IOException e) {
				System.err.println("Error during socket server init.");
				e.printStackTrace();
			}finally {
				
					try {
						if(in == null){
						in.close();
						}
						if(isr == null){
							isr.close();
						}
						if(br == null){
							br.close();
						}
					} catch (IOException e) {
						System.err.println("Error during streams closing.");
						e.printStackTrace();
					}
				
			}
	   		  		
	   		}
	}
}
