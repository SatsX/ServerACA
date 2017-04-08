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
 * @authors Arnold / Adrien / Cihat
 *
 */
public class DAOPost implements DAO<Post> {

	private static final Logger LOG = Logger.getLogger(DAOPost.class.getName());
	private static String URL;
	private static String LOGIN;
	private static String PASSWORD;
	
	public DAOPost(String url, String login, String password) throws DAOException{
		DAOPost.URL = url;
		DAOPost.LOGIN = login;
		DAOPost.PASSWORD = password;
	}

	public Post find(Object id) throws DAOException {
		if(!(id instanceof Integer))
			throw new DAOException("ID not take in charge.");
		
		final String sql = "SELECT * FROM `post` WHERE `post_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet r = null;
		Post p = new Post();
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, (Integer) id);
			r = st.executeQuery();
			
			if(r.next()){
				p.setPostId(r.getInt("post_id"));
				p.setContent(r.getString("content"));
				p.setPostDate(r.getString("post_date"));
				p.setUserId(r.getInt("user_id"));
				p.setChannelId(r.getInt("channel_id"));
				return p;
				
			}else{
				throw new DAOException("Error no post for this id.");
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

	public void create(Post obj) throws DAOException {
		final String sql = "INSERT INTO `post` (`post_id`, `content`, `post_date`, `user_id`, `channel_id`)"
				+ " VALUES ( ? , ? , ? , ? , ? ) ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getUserId());
			st.setString(2, obj.getContent());
			st.setString(3, obj.getPostDate());
			st.setInt(4, obj.getUserId());
			st.setInt(5, obj.getChannelId());
			r = st.executeUpdate();
				
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

	public void update(Post obj) throws DAOException {
		// TODO Auto-generated method stub
				final String sql = "UPDATE `post` SET `content` = ? "
						+ "WHERE `post_id` = ? ;";
				
				Connection c = null;
				PreparedStatement st = null;
				int r = 0;
				
				
				try{
					// init connection
					c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
					st = c.prepareStatement(sql);
					st.setString(1, obj.getContent());
					st.setInt(2, obj.getPostId());
					r = st.executeUpdate();
					
					// check modification		
					if(r < 1){
						throw new DAOException("No post was updated.");
					}
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

	public void delete(Post obj) throws DAOException {
		final String sql = "DELETE FROM `post` WHERE `post_id` = ? ;";
		
		Connection c = null;
		PreparedStatement st = null;
		int r = 0;
		
		try{
			c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = c.prepareStatement(sql);
			st.setInt(1, obj.getPostId());
			r = st.executeUpdate();
			
			if(r < 1){
				throw new DAOException("No post was deleted.");
			}
				
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
