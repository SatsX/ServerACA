
package cnam.tchat.aca.server.command;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import cnam.tchat.aca.server.io.Channel;
import cnam.tchat.aca.server.io.MainServer;
import cnam.tchat.aca.server.io.User;

/**
 * @author arnold
 *
 */
public class Join extends Command {

	private String channel;
	/**
	 * 
	 */
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
		
		// recover user
		/*for (User tmpUser : MainServer.getUserConnected()) {
			if(tmpUser.getUserName().equals(nicknameUser)) {
				u = tmpUser;
			}
		}*/
		
		u = MainServer.getUserConnected().get(nicknameUser);
		Channel ch = existenceChannel();
		// check if channel exists, if exists, add user to existing channel, else create new channel
		if(ch == null) {
			System.out.println("if");
			ch = new Channel();
			ch.setChannelName(channel);
			ArrayList<User> listUser = new ArrayList<User>();
			listUser.add(u);
			ch.setlUser(listUser);
			MainServer.getListChannel().add(ch);
		} else {
			ch.getlUser().add(u);
			System.out.println("else");
		}
		
		u.setChannelUser(ch);
				
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You have joined channel " + channel);
		return jsonMessage.toString();
	}
	
	
	// check if channel exists, if exists return channel, else return null
	private Channel existenceChannel() {
		for (Channel tmp : MainServer.getListChannel()) {
			System.out.println("cc" + tmp.getChannelName());
			System.out.println("hey" + channel);
			if(tmp.getChannelName().compareTo(channel) == 0) {
				return tmp;
			}
		}
		return null;
	}

}
