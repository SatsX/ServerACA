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

import cnam.tchat.aca.server.io.Post;

/**
* @authors Adrien / Cihat / Arnold
 *
 */
// Class DAOPost
public class DAOPost implements DAO<Post> {

	private static final Logger LOG = Logger.getLogger(DAOPost.class.getName());
	private static String URL;
	private static String LOGIN;
	private static String PASSWORD;
	// Implement DAOPost
	public DAOPost(String url, String login, String password) throws DAOException{
		DAOPost.URL = url;
		DAOPost.LOGIN = login;
		DAOPost.PASSWORD = password;
	}
	//Find the post
	public Post find(Object id) throws DAOException {
		// Check format id
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		final String sql = "SELECT * FROM `post` WHERE `post_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		// Post instance
 		Post p = new Post();
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, (Integer) id);
			// Execute request
			r = st.executeQuery();
			
			if(r.next()){
				// Take data
				p.setPostId(r.getInt("post_id"));
				p.setContent(r.getString("content"));
				p.setPostDate(r.getString("post_date"));
				p.setUserId(r.getString("user_id"));
				p.setChannelId(r.getString("channel_id"));
				// Return data
				return p;
			// Check existence post for id
			}else{
				throw new DAOException("Error no post for this id.");
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
	//Create new Post
	public void create(Post obj) throws DAOException {
		final String sql = "INSERT INTO `post` (`post_id`, `content`, `post_date`, `user_id`, `channel_id`)"
				+ " VALUES ( ? , ? , ? , ? , ? ) ;";
		
		Connection c = null;
		PreparedStatement st = null;		
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getPostId());
			st.setString(2, obj.getContent());
			st.setString(3, obj.getPostDate());
			st.setString(4, obj.getUserId());
			st.setString(5, obj.getChannelId());
			// Execute the request
			st.executeUpdate();
		// Check the request
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during post creation.", e);
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
	//Update a post
	public void update(Post obj) throws DAOException {
				final String sql = "UPDATE `post` SET `content` = ? "
						+ "WHERE `post_id` = ? ;";
				
				Connection c = null;
				PreparedStatement st = null;
				int r = 0;
				
				
				try{
					// initialize connection
					c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
					st = c.prepareStatement(sql);
					st.setString(1, obj.getContent());
					st.setInt(2, obj.getPostId());
					// Execute request
					r = st.executeUpdate();
					
					// check modification		
					if(r < 1){
						throw new DAOException("No post was updated.");
					}
				// Check the request
				} catch (SQLException e){
					throw new DAOException("Error in SQL engines during post updating.", e);
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
	//Delete a post
	public void delete(Post obj) throws DAOException {
		final String sql = "DELETE FROM `post` WHERE `post_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			// initialize connection
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getPostId());
			// Execute the request
			r = st.executeUpdate();
			// Check delete
			if(r < 1){
				throw new DAOException("No post was deleted.");
			}
		// Check the request
		} catch (SQLException e){
			throw new DAOException("Error in SQL engines during post deleting.", e);
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
