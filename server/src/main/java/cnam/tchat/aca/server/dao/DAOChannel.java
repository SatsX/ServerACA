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

import cnam.tchat.aca.server.io.Channel;


/**
* @authors Adrien / Cihat / Arnold
 *
 */
public class DAOChannel implements DAO<Channel> {

	private static final Logger LOG = Logger.getLogger(DAOChannel.class.getName());
	private static String URL;
	private static String LOGIN;
	private static String PASSWORD;
	
	public DAOChannel(String url, String login, String password) throws DAOException{
		DAOChannel.URL = url;
		DAOChannel.LOGIN = login;
		DAOChannel.PASSWORD = password;
	}
	//Find the channel
	public Channel find(Object channelName) throws DAOException {
		if(!(channelName instanceof String))
			throw new DAOException("channelName not take in charge.");
		
		final String sql = "SELECT * FROM `channel` WHERE `channel_name` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		Channel ch = new Channel();
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setString(1, (String) channelName);
			r = st.executeQuery();
			
			if(r.next()){
				ch.setChannelId(r.getInt("channel_id"));
				ch.setChannelName(r.getString("channel_name"));
				
				return ch;
				
			}else{
				throw new DAOException("Error no channel for this id.");
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
	//Create a channel
	public void create(Channel obj) throws DAOException {
		final String sql = "INSERT INTO `channel` (`channel_id`, `channel_name`)"
				+ " VALUES ( ? , ? ) ;";
		
		Connection c = null;
		PreparedStatement st = null;	
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getChannelId());
			st.setString(2, obj.getChannelName());
			st.executeUpdate();
				
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during channel creation.", e);
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
	//Update a channel
	public void update(Channel obj) throws DAOException {
				final String sql = "UPDATE `channel` SET `channel_name` = ? "
						+ "WHERE `channel_id` = ? ;";
				
				Connection c = null;
				PreparedStatement st = null;
				int r = 0;
				
				
				try{
					// initialize connection
					c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
					st = c.prepareStatement(sql);
					st.setString(1, obj.getChannelName());
					st.setInt(2, obj.getChannelId());
					r = st.executeUpdate();
					
					// check modification		
					if(r < 1){
						throw new DAOException("No channel was updated.");
					}
				} catch (SQLException e){
					throw new DAOException("Error in SQL engines during channel updating.", e);
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
	//Delete a channel
	public void delete(Channel obj) throws DAOException {
		final String sql = "DELETE FROM `channel` WHERE `channel_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getChannelId());
			// Execute the request 
			r = st.executeUpdate();
			
			if(r < 1){
				throw new DAOException("No channel was deleted.");
			}
				
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during channel deleting.", e);
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
	//List all the channel
	public ArrayList<Channel> listAllChannel() throws DAOException {
		final String sql = "SELECT * FROM `channel`;";
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			r = st.executeQuery();
			// Create a list of channel
			ArrayList<Channel> l = new ArrayList<Channel>();
			
			while(r.next()){
				// Create a channel
				Channel ch = new Channel();
				// Recover all fields
				ch.setChannelId(r.getInt("channel_id"));
				ch.setChannelName(r.getString("channel_name"));
				// Add the channel to the list
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
