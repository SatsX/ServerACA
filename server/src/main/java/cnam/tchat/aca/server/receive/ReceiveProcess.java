package cnam.tchat.aca.server.receive;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;


public class ReceiveProcess extends JSONObject{

	private String arguments; 
	private String post;
	private ArrayList<String> channels;
	private ArrayList<String> users;


	public ReceiveProcess(String json) {
		super(json);

		try {
			String arguments = this.getString("arguments");
			String post = this.getString("post");
			
			this.setArguments(arguments);
			this.setPost(post);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}


	/**
	 * @return the nickname
	 */
	public String getArguments() {
		return this.arguments;
	}


	/**
	 * @return the post
	 */
	public String getPost() {
		return this.post;
	}


	/**
	 * @return the channels
	 */
	public ArrayList<String> getChannels() {
		return this.channels;
	}


	/**
	 * @return the users
	 */
	public ArrayList<String> getUsers() {
		return this.users;
	}


	/**
	 * @param nickname the nickname to set
	 */
	private void setArguments(String arguments) {
		this.arguments = arguments;
	}


	/**
	 * @param post the post to set
	 */
	private void setPost(String post) {
		this.post = post;
	}


	/**
	 * @param channels the channels to set
	 */
	private void setChannels(ArrayList<String> channels) {
		this.channels = channels;
	}


	/**
	 * @param users the users to set
	 */
	private void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}
}
