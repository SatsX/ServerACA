/**
 * 
 */
package cnam.tchat.aca.server.command;

import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.User;

/**
 * @author arnold
 *
 */
public class Connect extends Command {

	private JSONArray parameters;
	private String nicknameUser;
	private Socket socketUser;
	
	/**
	 * 
	 */
	public Connect(JSONArray parameters, String nicknameUser, Socket socketUser) {
		// TODO Auto-generated constructor stub
		this.parameters = parameters;
		this.nicknameUser = nicknameUser;
		this.socketUser = socketUser;
	}

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.command.Command#takeDecision()
	 */
	@Override
	public String takeDecision() {
		
		// Create new user
		User u = new User();
		u.setUserId(socketUser.toString());
		u.setUserName(nicknameUser);
		u.setSocketUser(socketUser);
		
		// add user to the server
		MainServer.getUserConnected().put(nicknameUser, u);
		System.out.println(MainServer.getUserConnected());
		
		// insert user into database
		
		// return message to client
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You are connected to server : " + parameters.getString(0) + " as " + parameters.getString(1));
		return jsonMessage.toString();
	}

}
