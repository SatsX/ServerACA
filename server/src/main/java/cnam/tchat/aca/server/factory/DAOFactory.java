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
	
	public static DAO getDAOUser() throws DAOException{
		return new DAOUser();
	}
	
	public static DAO getDAOChannel() throws DAOException{
		return new DAOChannel();
	}
	
	public static DAO getDAOLogInfo() throws DAOException{
		return new DAOLogInfo();
	}
	
	public static DAO getDAOPost() throws DAOException{
		return new DAOPost();
	}

}
