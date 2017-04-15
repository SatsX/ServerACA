/**
 * 
 */
package cnam.tchat.aca.server.factory;

import cnam.tchat.aca.server.dao.DAO;
import cnam.tchat.aca.server.dao.DAOChannel;
import cnam.tchat.aca.server.dao.DAOException;
import cnam.tchat.aca.server.dao.DAOLogInfo;
import cnam.tchat.aca.server.dao.DAOPost;
import cnam.tchat.aca.server.dao.DAOUser;
import cnam.tchat.aca.server.io.Channel;
import cnam.tchat.aca.server.io.LogInfo;
import cnam.tchat.aca.server.io.Post;
import cnam.tchat.aca.server.io.User;

/**
 * @authors Adrien / Cihat / Arnold
 *
 */
public class DAOFactory {
	//connection bdd
	private static final String URL = "jdbc:mysql://localhost:3306/chatirc?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";
	// Instance DAOUser
	public static DAO<User> getDAOUser() throws DAOException{
		return new DAOUser(URL, LOGIN, PASSWORD);
	}
	// Instance DAOChannel
	public static DAO<Channel> getDAOChannel() throws DAOException{
		return new DAOChannel(URL, LOGIN, PASSWORD);
	}
	// Instance DAOLOgInfo
	public static DAO<LogInfo> getDAOLogInfo() throws DAOException{
		return new DAOLogInfo(URL, LOGIN, PASSWORD);
	}
	// Instance DAOPost
	public static DAO<Post> getDAOPost() throws DAOException{
		return new DAOPost(URL, LOGIN, PASSWORD);
	}

}
