/**
 * 
 */
package cnam.tchat.aca.server.factory;

import cnam.tchat.aca.server.io.LogInfo;

/**
 * @author arnold
 *
 */
public abstract class Factory {
	
	public LogInfo storeLogInfo(){
		LogInfo logInfo = this.createLogInfo();
		return logInfo;
	}
	
	/*
	 * create log information to store it into the database
	 * @return LogInfo
	 */
	public abstract LogInfo createLogInfo();

}
