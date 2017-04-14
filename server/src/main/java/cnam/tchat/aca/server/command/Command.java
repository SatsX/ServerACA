package cnam.tchat.aca.server.command;

import org.json.JSONObject;

import cnam.tchat.aca.server.messageProcess.ReceiveProcess;

public abstract class Command {

	public Command(){
		// Useless
	}
	
	public abstract String takeDecision();

}
