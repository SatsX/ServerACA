package cnam.tchat.aca.server.command;

import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import cnam.tchat.aca.server.dao.DAOException;
import cnam.tchat.aca.server.dao.DAOUser;
import cnam.tchat.aca.server.factory.DAOFactory;
import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.User;

/**
 * @authors Adrien / Cihat / Arnold
 *
 */

// Connect class extends Command
public class Connect extends Command {

	//Implements Connect
	private JSONArray parameters;
	private Socket socketUser;

	//Implements constructor
	public Connect(JSONArray parameters, String nicknameUser, Socket socketUser) {
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
		
		// insert user into database
		try {
			DAOUser d = (DAOUser) DAOFactory.getDAOUser();
			d.create(u);
		} catch (DAOException e) {
			System.err.println("Error during insert user");
			e.printStackTrace();
		}
		
		
		// return message to client
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You are connected to server : " + parameters.getString(0) + " as " + parameters.getString(1));
		return jsonMessage.toString();
	}

}
