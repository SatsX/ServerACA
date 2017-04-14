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
public class Connect extends Command {

	private JSONArray parameters;
	
	/**
	 * 
	 */
	public Connect(JSONArray parameters) {
		// TODO Auto-generated constructor stub
		this.parameters = parameters;
	}

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.command.Command#takeDecision()
	 */
	@Override
	public String takeDecision() {
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You are connected to server : " + parameters.getString(0) + " as " + parameters.getString(1));
		return jsonMessage.toString();
	}

}
