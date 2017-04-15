/**
 * 
 */
package cnam.tchat.aca.server.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cnam.tchat.aca.server.io.User;

/**
 * @authors Adrien / Cihat / Arnold
 *
 */
public class DAOUser implements DAO<User> {

	private static final Logger LOG = Logger.getLogger(DAOUser.class.getName());
	private static String URL;
	private static String LOGIN;
	private static String PASSWORD;
	
	public DAOUser(String url, String login, String password) throws DAOException{
		DAOUser.URL = url;
		DAOUser.LOGIN = login;
		DAOUser.PASSWORD = password;
	}
	// Find the user.
	public User find(Object id) throws DAOException {
		if(!(id instanceof String))
			throw new DAOException("ID not take in charge.");
		
		final String sql = "SELECT * FROM `user` WHERE `User_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		User a = new User();
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setString(1, (String) id);
			r = st.executeQuery();
			
			if(r.next()){
				a.setUserId(r.getString("User_id"));
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
	//Create user
	public void create(User obj) throws DAOException {
		final String sql = "INSERT INTO `user` (`User_id`, `User_name`)"
				+ " VALUES ( ? , ? ) ;";
		
		Connection c = null;
		PreparedStatement st = null;
				
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setString(1, obj.getUserId());
			st.setString(2, obj.getUserName());
			st.executeUpdate();
				
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
	//Update user
	public void update(User obj) throws DAOException {
				final String sql = "UPDATE `user` SET `User_name` = ? "
						+ "WHERE `User_id` = ? ;";
				
				Connection c = null;
				PreparedStatement st = null;
				int r = 0;
				
				
				try{
					// initialize connection
					c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
					st = c.prepareStatement(sql);
					st.setString(1, obj.getUserName());
					st.setString(2, obj.getUserId());
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
	//Delete user.
	public void delete(User obj) throws DAOException {
		final String sql = "DELETE FROM `user` WHERE `User_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setString(1, obj.getUserId());
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
	//List all users 
	public ArrayList<User> listAllUser() throws DAOException {
		final String sql = "SELECT * FROM `user`;";
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			r = st.executeQuery();
			// Create a list of user
			ArrayList<User> l = new ArrayList<User>();
			
			while(r.next()){
				// Create a user
				User ch = new User();
				// recover all fields
				ch.setUserId(r.getString("User_id"));
				ch.setUserName(r.getString("User_name"));
				// Add the user to the list
				l.add(ch);
			}
			// Return the list
			return l;	
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
}
