package cnam.tchat.aca.server.command;

public abstract class Command {

	protected String nicknameUser;
	
	public Command(){
	}
	
	public abstract String takeDecision();

}
