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

/**
 * @author arnold
 *
 */
public class DAOFactory {
	
	private static final String URL = "jdbc:mysql://localhost:3306/chatirc";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";
	
	public static DAO getDAOUser() throws DAOException{
		return new DAOUser(URL, LOGIN, PASSWORD);
	}
	
	public static DAO getDAOChannel() throws DAOException{
		return new DAOChannel(URL, LOGIN, PASSWORD);
	}
	
	public static DAO getDAOLogInfo() throws DAOException{
		return new DAOLogInfo(URL, LOGIN, PASSWORD);
	}
	
	public static DAO getDAOPost() throws DAOException{
		return new DAOPost(URL, LOGIN, PASSWORD);
	}

}
