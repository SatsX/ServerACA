/**
 * 
 */
package cnam.tchat.aca.server.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cnam.tchat.aca.server.io.LogInfo;

/**
* @authors Adrien / Cihat / Arnold
 *
 */
// Class DAOLogInfo
public class DAOLogInfo implements DAO<LogInfo> {
	// Connection instance
	private static final Logger LOG = Logger.getLogger(DAOLogInfo.class.getName());
	private static String URL;
	private static String LOGIN;
	private static String PASSWORD;
	// Implement DAOLogInfo
	public DAOLogInfo(String url, String login, String password) throws DAOException{
		DAOLogInfo.URL = url;
		DAOLogInfo.LOGIN = login;
		DAOLogInfo.PASSWORD = password;
	}
	//Implement find
	public LogInfo find(Object id) throws DAOException {
		// Check format id
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		final String sql = "SELECT * FROM `logInfo` WHERE `id_log` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		// LogInfo instance
		LogInfo a = new LogInfo();
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, (Integer) id);
			// Execute request
			r = st.executeQuery();
			
			if(r.next()){
				// Take data
				a.setIdLog(r.getInt("id_log"));
				a.setIpAdress(r.getString("ip_adress"));
				a.setHour(r.getDate("hour"));
				
				return a;
			// Check existence log information for id
			}else{
				throw new DAOException("Error no log information for this id.");
			}
		// Check the request	
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during log information loading.", e);
		}finally{
			try{
				if(r != null)
					r.close();
				if(st != null)
					st.close();
				if(c != null)
					c.close();
			}catch (SQLException e){
				LOG.error("Error during closing open connections", e);
				
			}
		}
	}
	// Implement create
	public void create(LogInfo obj) throws DAOException {
		final String sql = "INSERT INTO `logInfo` (`ip_adress`, `hour`)"
				+ " VALUES ( ? , ? ) ;";
		
		Connection c = null;
		PreparedStatement st = null;
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setString(1, obj.getIpAdress());
			st.setDate(2, obj.getHour());
			// Execute request
			st.executeUpdate();
		// Check the request
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during log information creation.", e);
		}finally{
			try{
				if(st != null)
					st.close();
				if(c != null)
					c.close();
			}catch (SQLException e){
				LOG.error("Error during closing open connections");
				
			}
		}
	}
	// Implements update
	public void update(LogInfo obj) throws DAOException {
				final String sql = "UPDATE `logInfo` SET `ip_adress` = ? ,"
						+ " `hour` = ? ,"
						+ "WHERE `id_log` = ? ;";
				
				Connection c = null;
				PreparedStatement st = null;
				int r = 0;
				
				try{
					// initialize connection
					c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
					st = c.prepareStatement(sql);
					st.setString(1, obj.getIpAdress());
					st.setDate(2, obj.getHour());
					//Execute request
					r = st.executeUpdate();
					
					// check modification
					if(r < 1){
						throw new DAOException("No log info updated");
						
					}
				// Check the request
				} catch (SQLException e){
					throw new DAOException("Error in SQL engines during log information updating.", e);
				}finally{
					try{
						if(st != null)
							st.close();
						if(c != null)
							c.close();
					}catch (SQLException e){
						LOG.error("Error during closing open connections", e);
						
					}
				}
	}
	// Implement delete
	public void delete(LogInfo obj) throws DAOException {
		final String sql = "DELETE FROM `logInfo` WHERE `id_log` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getIdLog());
			// Execute request
			r = st.executeUpdate();
			// Check the delete
			if(r < 1){
				throw new DAOException("Delete didn't work.");
			}
		// Check the request
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during log information deleting.", e);
		}finally{
			try{
				if(st != null)
					st.close();
				if(c != null)
					c.close();
			}catch (SQLException e){
				LOG.error("Error during closing open connections", e);
				
			}
		}
		
	}

}
