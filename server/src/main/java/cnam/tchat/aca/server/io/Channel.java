/**
 * 
 */
package cnam.tchat.aca.server.io;

import java.util.ArrayList;

import cnam.tchat.aca.server.dao.DAOChannel;
import cnam.tchat.aca.server.dao.DAOException;
import cnam.tchat.aca.server.factory.DAOFactory;


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

	
	public boolean existenceChannel(String name) throws DAOException {
		DAOChannel d = (DAOChannel) DAOFactory.getDAOChannel();
		//Create list channel
		ArrayList<Channel> lch; 
		//Listing channel
		lch = d.listAllChannel();
		//Check existence of the channel
		for (Channel tmp : lch){
			if(tmp.getChannelName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public Channel() throws DAOException {
		
	}
}
