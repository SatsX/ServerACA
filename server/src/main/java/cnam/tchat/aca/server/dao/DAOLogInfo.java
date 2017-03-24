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
 * @authors Arnold / Adrien / Cihat
 *
 */
public class DAOLogInfo implements DAO<LogInfo> {

	private static final Logger LOG = Logger.getLogger(DAOLogInfo.class.getName());
	private static final String URL = "jdbc:mysql://localhost:3306/cnam?useSSL=false";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
	
	protected DAOLogInfo() throws DAOException{
		//useless
	}

	public LogInfo find(Object id) throws DAOException {
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		final String sql = "SELECT * FROM `logInfo` WHERE `id_log` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		LogInfo a = new LogInfo();
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, (int) id);
			r = st.executeQuery();
			
			if(r.next()){
				a.setIdLog(r.getInt("id_log"));
				a.setIpAdress(r.getString("ip_adress"));
				a.setHour(r.getDate("hour"));
				
				return a;
				
			}else{
				throw new DAOException("Error no log information for this id.");
			}
				
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

	public void create(LogInfo obj) throws DAOException {
		final String sql = "INSERT INTO `logInfo` (`ip_adress`, `hour`)"
				+ " VALUES ( ? , ? ) ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setString(1, obj.getIpAdress());
			st.setDate(2, obj.getHour());
			r = st.executeUpdate();
				
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

	public void update(LogInfo obj) throws DAOException {
		// TODO Auto-generated method stub
				final String sql = "UPDATE `logInfo` SET `ip_adress` = ? ,"
						+ " `hour` = ? ,"
						+ "WHERE `id_log` = ? ;";
				
				Connection c = null;
				PreparedStatement st = null;
				int r = 0;
				
				try{
					// init connection
					c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
					st = c.prepareStatement(sql);
					st.setString(1, obj.getIpAdress());
					st.setDate(2, obj.getHour());
					r = st.executeUpdate();
					
					// check modification
					if(r < 1){
						throw new DAOException("No log info updated");
						
					}
						
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

	public void delete(LogInfo obj) throws DAOException {
		final String sql = "DELETE FROM `logInfo` WHERE `id_log` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getIdLog());
			r = st.executeUpdate();
			
			if(r < 1){
				throw new DAOException("Delete didn't work.");
			}
				
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
