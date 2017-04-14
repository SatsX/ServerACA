/**
 * 
 */
package cnam.tchat.aca.server.io;

/**
 * @author Arnold / Adrien / Cihat
 *
 */
public class User {

	private int userId;
	private String userName;
	
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
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
