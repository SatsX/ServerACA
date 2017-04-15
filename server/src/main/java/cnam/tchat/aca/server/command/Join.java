package cnam.tchat.aca.server.command;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import cnam.tchat.aca.server.dao.DAOChannel;
import cnam.tchat.aca.server.dao.DAOException;
import cnam.tchat.aca.server.factory.DAOFactory;
import cnam.tchat.aca.server.io.Channel;
import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.User;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * @authors Adrien / Cihat / Arnold
 *
 */

//Join Class extends Command
public class Join extends Command {
	
	static final Logger logger = Logger.getLogger(Join.class);
	
	//implements arguments
	private String channel;
	
	// implements constructor
	public Join(JSONArray parameters, String nicknameUser) {
		this.channel = parameters.getString(0);
		this.nicknameUser = nicknameUser;
	}

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.command.Command#takeDecision()
	 */
	@Override
	public String takeDecision() {
		
		
		User u = null;
		u = MainServer.getUserConnected().get(nicknameUser);
		Channel ch = existenceChannel();
		
		// check if channel exists, if exists, add user to existing channel, else create new channel
		if(ch == null) {
			ch = new Channel();
			ch.setChannelName(channel);
			ch.setChannelId(channel);
			ArrayList<User> listUser = new ArrayList<User>();
			listUser.add(u);
			ch.setlUser(listUser);
			MainServer.getListChannel().add(ch);
		} else {
			ch.getlUser().add(u);
		}
		
		// insert channel into database
		try {
			DAOChannel d = (DAOChannel) DAOFactory.getDAOChannel();
			d.create(ch);
		} catch (DAOException e) {
			logger.error("Error during insert channel");
			e.printStackTrace();
		}

		
		//add to user a channel
		u.setChannelUser(ch);
				
		//the JSONObject traitement
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You have joined channel " + channel);
		return jsonMessage.toString();
	}
	
	
	// check if channel exists, if exists return channel, else return null
	private Channel existenceChannel() {
		for (Channel tmp : MainServer.getListChannel()) {
			if(tmp.getChannelName().compareTo(channel) == 0) {
				return tmp;
			}
		}
		return null;
	}

}
