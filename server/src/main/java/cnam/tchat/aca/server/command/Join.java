/**
 * 
 */
package cnam.tchat.aca.server.command;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author arnold
 *
 */
public class Join extends Command {

	private String channel;
	/**
	 * 
	 */
	public Join(JSONArray parameters) {
		// TODO Auto-generated constructor stub
		this.channel = parameters.getString(0);
	}

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.command.Command#takeDecision()
	 */
	@Override
	public String takeDecision() {
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You have joined channel " + channel);
		return jsonMessage.toString();
	}

}
