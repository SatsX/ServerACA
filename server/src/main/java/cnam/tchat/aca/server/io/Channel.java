/**
 * 
 */
package cnam.tchat.aca.server.io;

import cnam.tchat.aca.server.dao.DAOChannel;
import cnam.tchat.aca.server.dao.DAOException;


/**
 * @authors Adrien / Cihat / Arnold
 *
 */
public class Channel {
	private static final String URL = "jdbc:mysql://localhost:3306/chatirc?useSSL=false";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";
	private int channelId;
	private String channelName;
	private String user_name;
	private int user_id;
	
	
	
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
	
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	
	public void existenceChannel(Object id) throws DAOException {
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		DAOChannel d = new DAOChannel(URL, LOGIN, PASSWORD);
		Channel ch = new Channel();
		
		ch.setChannelId(channelId);
		Channel ch2;
		//Listing channel
		ch2 = d.listAllChannel(channelId);
		//Check the existence of the channel and if their don't exist it will create it.
		if(ch.compareTo(ch2)== false){
			d.create(ch);
		}
	}
	
	public void joinChannel(Object id) throws DAOException {
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		DAOChannel d = new DAOChannel(URL, LOGIN, PASSWORD);
		Channel ch = new Channel();
		
		ch.setChannelId(channelId);
		Channel ch2;
		//Listing channel
		ch2 = d.listChannel(ch);
		//Check the existence of the channel and if their don't exist it will be create it and the user too.
		if(ch.compareTo(ch2)== false){
			d.create(ch2);
			d.userJoinChannel(ch2);
		}else {
			d.userJoinChannel(ch2);
		}
		
	}

	public void userInChannel(Object id) throws DAOException {
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		DAOChannel d = new DAOChannel(URL, LOGIN, PASSWORD);
		
		d.listAllUserInChannel(channelId);
		
	}
	
	public Channel() throws DAOException {
		//Useless
		
	}




}
