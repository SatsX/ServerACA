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

import cnam.tchat.aca.server.io.User;

/**
 * @authors Arnold / Adrien / Cihat
 *
 */
public class DAOUser implements DAO<User> {

	private static final Logger LOG = Logger.getLogger(DAOUser.class.getName());
	private static final String URL = "jdbc:mysql://localhost:3306/chatirc?useSSL=false";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
	
	public DAOUser() throws DAOException{
		//useless
	}

	public User find(Object id) throws DAOException {
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		final String sql = "SELECT * FROM `user` WHERE `User_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		User a = new User();
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, (int) id);
			r = st.executeQuery();
			
			if(r.next()){
				a.setUserId(r.getInt("User_id"));
				a.setUserName(r.getString("User_name"));
				
				return a;
				
			}else{
				throw new DAOException("Error no user for this id.");
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

	public void create(User obj) throws DAOException {
		final String sql = "INSERT INTO `user` (`User_id`, `User_name`)"
				+ " VALUES ( ? , ? ) ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getUserId());
			st.setString(2, obj.getUserName());
			r = st.executeUpdate();
				
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during user creation.", e);
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

	public void update(User obj) throws DAOException {
		// TODO Auto-generated method stub
				final String sql = "UPDATE `user` SET `User_name` = ? ,"
						+ "WHERE `User_id` = ? ;";
				
				Connection c = null;
				PreparedStatement st = null;
				int r = 0;
				
				
				try{
					// init connection
					c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
					st = c.prepareStatement(sql);
					st.setString(1, obj.getUserName());
					r = st.executeUpdate();
					
					// check modification		
					if(r < 1){
						throw new DAOException("No user was updated.");
					}
				} catch (SQLException e){
					throw new DAOException("Error in SQL engines during user updating.", e);
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

	public void delete(User obj) throws DAOException {
		final String sql = "DELETE FROM `user` WHERE `User_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getUserId());
			r = st.executeUpdate();
			
			if(r < 1){
				throw new DAOException("No user was deleted.");
			}
				
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during user deleting.", e);
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
