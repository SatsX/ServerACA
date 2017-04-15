package cnam.tchat.aca.server.command;

/**
 * @authors Adrien / Cihat / Arnold
 *
 */

// SuperClass for available command
public abstract class Command {

	protected String nicknameUser;
	
	public Command(){
	}
	
	public abstract String takeDecision();

}
