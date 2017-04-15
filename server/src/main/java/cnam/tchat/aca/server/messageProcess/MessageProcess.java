package cnam.tchat.aca.server.messageProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import cnam.tchat.aca.server.command.Command;
import cnam.tchat.aca.server.command.Connect;
import cnam.tchat.aca.server.command.Exit;
import cnam.tchat.aca.server.command.Join;
import cnam.tchat.aca.server.command.Quit;
import cnam.tchat.aca.server.dao.DAOException;
import cnam.tchat.aca.server.dao.DAOPost;
import cnam.tchat.aca.server.factory.DAOFactory;
import cnam.tchat.aca.server.io.Channel;
import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.Post;
import cnam.tchat.aca.server.io.User;

/**
 * @authors Adrien / Cihat / Arnold
 *
 */

public class MessageProcess implements Runnable{
	//Implements MessageProcess
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

	//Implement constructor
	public MessageProcess(Socket cs) {	
		so = cs;		
	}

	//can read the receive process
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
				//Create a JSONObject
				JSONObject jsonMessage = new JSONObject(originalMessage);
				
				// Check if is a command
				if(jsonMessage.getString("post").startsWith("#"))
				{
					Command command;
					isCommand = true;
					// List of available command
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
					// It's a post
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
		
	//Close the socket
	public void Cancel() throws IOException{
		so.close();
	}

	// the run method
	public void run() {
		
		// Check the socket if it's connected
		if(so.isConnected()){
	   		try {
	   			// store the client message
				in = so.getInputStream();
				isr = new InputStreamReader(in, "UTF-8");
		   		br = new BufferedReader(isr);
		   		while(true){
		   			// read the client message
			   		String msgToClient = this.read();
			   		// check if msgToClient it's null to break the loop
					if(msgToClient == null){
						break;
					}
					// store our message
			   		out = so.getOutputStream();
					osw = new OutputStreamWriter(out, "UTF-8");
					bw = new BufferedWriter(osw);
					System.out.println("New user : " + so);
					System.out.println("Writing : " + msgToClient);
					// Check the message to send if it's a command or a post
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
						
						// insert post into database
						// current date
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						String datePost = dateFormat.format(date);
						
						// create new post
						Post p = new Post();
						p.setUserId(u.getUserId());
						p.setChannelId(ch.getChannelId());
						p.setContent(msgToRoom.getString("post"));
						p.setPostDate(datePost);
						
						//put post into database
						try {
							DAOPost dp = (DAOPost) DAOFactory.getDAOPost();
							dp.create(p);
						} catch (DAOException e) {
							System.err.println("Error during insert post");
							e.printStackTrace();
						}
						
						// send the message to all of Channel's users
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
