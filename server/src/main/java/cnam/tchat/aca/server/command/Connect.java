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
	public JSONObject takeDecision() {
		// TODO Auto-generated method stub
		return null;
	}

}
