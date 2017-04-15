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
//Exit class extends Command
public class Exit extends Command {

	//Implements constructor
	public Exit(String nicknameUser) {
		this.nicknameUser = nicknameUser;
	}

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.command.Command#takeDecision()
	 */
	@Override
	public String takeDecision() {
		
		User u = MainServer.getUserConnected().get(nicknameUser);
		Channel ch = u.getChannelUser();
		
		// remove user from server
		MainServer.getUserConnected().remove(nicknameUser);
		
		
		// remove user from channel
		if(u.getChannelUser() != null){
			int indexUser = 0;
			ArrayList<Channel> listCh = MainServer.getListChannel();
			for(Channel tmp : listCh) {
				if (ch.getChannelName().equals(tmp.getChannelName())){
					for(User tmpUser : tmp.getlUser()){
						if(tmpUser.getUserName().equals(nicknameUser)){
							System.out.println("avant : " + u.getChannelUser().getlUser());
							tmp.getlUser().remove(indexUser);
							System.out.println("apres : " + u.getChannelUser().getlUser());
							u.setChannelUser(null);
							break;
						}
						indexUser++;
					}
					break;
				}
			}
		}
		
		
		
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You have left server, bye...");
		return jsonMessage.toString();
	}

}
