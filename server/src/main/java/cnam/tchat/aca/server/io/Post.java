/**
 * 
 */
package cnam.tchat.aca.server.io;

/**
* @authors Adrien / Cihat / Arnold
 *
 */
public class Post {

	private int postId;
	private String content;
	private String postDate;
	private String userId;
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
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
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
		
	}

}
