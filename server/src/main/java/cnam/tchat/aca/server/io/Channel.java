/**
 * 
 */
package cnam.tchat.aca.server.io;
import java.sql.Date;

import cnam.tchat.aca.server.dao.DAOChannel;
import cnam.tchat.aca.server.dao.DAOException;


/**
 * @author Arnold / Adrien / Cihat
 *
 */
public class Channel {
	private static final String URL = "jdbc:mysql://localhost:3306/chatirc?useSSL=false";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
	private int channelId;
	private String channelName;
	private String user_name;
	
	
	
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
	 * @return the user_name
	 */
	public String getUserName() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}


	
	public boolean compareTo(Channel ch){
		if(this.channelId == ch.channelId && this.channelName.compareTo(ch.channelName) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkExistence(Channel ch){
		if(this.channelName == ch.channelName) {
			return true;
		} else {
			return false;
		}
	}
	
	public void ExistenceChannel() throws DAOException {
		DAOChannel d = new DAOChannel(URL, LOGIN, PASSWORD);
		Channel ch = new Channel();
		
		ch.setChannelName(channelName);
		Channel ch2;
		//Listing channel
		ch2 = d.listAllChannel(channelId);
		//Check the existence of the channel and if their don't exist it will create it.
		if(ch.compareTo(ch2)== false){
			ch.setChannelId(channelId);
			d.create(ch);
		}
	}
	
	public Channel() throws DAOException {
		//Useless
		
	}

}
