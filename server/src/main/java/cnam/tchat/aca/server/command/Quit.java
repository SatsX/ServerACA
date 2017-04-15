package cnam.tchat.aca.server.command;

import java.util.ArrayList;

import org.json.JSONObject;

import cnam.tchat.aca.server.io.Channel;
import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.User;

/**
 * @authors Adrien / Cihat / Arnold
 *
 */

// Quit class extends Command
public class Quit extends Command {

	// Implements constructor
	public Quit(String nicknameUser) {
		this.nicknameUser = nicknameUser;
	}

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.command.Command#takeDecision()
	 */
	@Override
	public String takeDecision() {
		
		User u = MainServer.getUserConnected().get(nicknameUser);
		Channel ch = u.getChannelUser();
		
		
		//remove user from channel
		
		if(u.getChannelUser() != null){
			int indexUser = 0;
			ArrayList<Channel> listCh = MainServer.getListChannel();
			for(Channel tmp : listCh) {
				if (ch.getChannelName().equals(tmp.getChannelName())){
					for(User tmpUser : tmp.getlUser()){
						if(tmpUser.getUserName().equals(nicknameUser)){
							tmp.getlUser().remove(indexUser);
							u.setChannelUser(null);
							break;
						}
						indexUser++;
					}
					break;
				}
			}
		}
		
		//The JSON traitement
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You have left channel");
		return jsonMessage.toString();
	}

}
