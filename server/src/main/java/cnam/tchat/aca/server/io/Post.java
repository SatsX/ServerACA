/**
 * 
 */
package cnam.tchat.aca.server.io;
import java.sql.Date;

import cnam.tchat.aca.server.dao.DAOPost;

/**
 * @author Arnold / Adrien / Cihat
 *
 */
public class Post {

	private int postId;
	private String content;
	private String postDate;
	private int userId;
	private int channelId;
	
	/**
	 * @return the postId
	 */
	public int getPostId() {
		return postId;
	}

	/**
	 * @param userId the postId to set
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the postDate
	 */
	public String getPostDate() {
		return postDate;
	}

	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the channelId
	 */
	public int getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the ChannelId to set
	 */
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	public boolean compareTo(Post p){
		if(this.postId == p.postId && this.content.compareTo(p.content) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public Post() {
		//Useless
	}

}
