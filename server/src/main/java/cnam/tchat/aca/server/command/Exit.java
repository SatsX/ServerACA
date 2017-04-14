/**
 * 
 */
package cnam.tchat.aca.server.command;

import org.json.JSONObject;

/**
 * @author arnold
 *
 */
public class Exit extends Command {

	/**
	 * 
	 */
	public Exit() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.command.Command#takeDecision()
	 */
	@Override
	public String takeDecision() {
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("nickname", "server");
		jsonMessage.put("post", "You have left server, bye...");
		return jsonMessage.toString();
	}

}
