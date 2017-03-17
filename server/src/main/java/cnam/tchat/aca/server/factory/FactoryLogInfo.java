/**
 * 
 */
package cnam.tchat.aca.server.factory;

import cnam.tchat.aca.server.io.LogInfo;

/**
 * @author arnold
 *
 */
public class FactoryLogInfo extends Factory {

	/* (non-Javadoc)
	 * @see cnam.tchat.aca.server.factory.Factory#createLogInfo()
	 */
	@Override
	public LogInfo createLogInfo() {
		// TODO Auto-generated method stub
		return new LogInfo();
	}

}
