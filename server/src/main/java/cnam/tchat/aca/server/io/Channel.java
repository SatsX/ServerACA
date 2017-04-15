/**
 * 
 */
package cnam.tchat.aca.server.io;

import java.util.ArrayList;



/**
 * @authors Adrien / Cihat / Arnold
 *
 */
public class Channel {
	
	private int channelId;
	private String channelName;
	// List user
	private ArrayList<User> lUser;
	
	
	/**
	 * @return the channelId
	 */
	public int getChannelId() {
		return channelId;
	}

	
	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * @return the lUser
	 */
	public ArrayList<User> getlUser() {
		return lUser;
	}


	/**
	 * @param lUser the lUser to set
	 */
	public void setlUser(ArrayList<User> lUser) {
		this.lUser = lUser;
	}

	
	public boolean compareTo(Channel ch){
		if(this.channelId == ch.channelId && this.channelName.compareTo(ch.channelName) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Channel() {
		lUser = new ArrayList<>();
	}
}
