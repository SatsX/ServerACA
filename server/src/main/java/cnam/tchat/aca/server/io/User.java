/**
 * 
 */
package cnam.tchat.aca.server.io;

import java.net.Socket;

/**
 * @author Arnold / Adrien / Cihat
 *
 */
public class User {

	private String userId;
	private String userName;
	private Socket socketUser;
	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}



	
	/**
	 * @return the socketUser
	 */
	public Socket getSocketUser() {
		return socketUser;
	}

	/**
	 * @param socketUser the socketUser to set
	 */
	public void setSocketUser(Socket socketUser) {
		this.socketUser = socketUser;
	}

	public boolean compareTo(User u){
		if(this.userId == u.userId && this.userName.compareTo(u.userName) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public User() {
		//Useless
	}

}
