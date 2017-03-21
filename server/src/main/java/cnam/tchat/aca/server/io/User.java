/**
 * 
 */
package cnam.tchat.aca.server.io;
import java.sql.Date;

import cnam.tchat.aca.server.dao.DAOUser;

/**
 * @author Arnold
 *
 */
public class User {

	private int userId;
	private String userName;
	private Date connectionDate;
	
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

	/**
	 * @return the connectionDate
	 */
	public Date getConnectionDate() {
		return connectionDate;
	}

	/**
	 * @param hour the connectionDate to set
	 */
	public void setConnectionDate(Date connectionDate) {
		this.connectionDate = connectionDate;
	}

	public User() {
		//Useless
	}

}
