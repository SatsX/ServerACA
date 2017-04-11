package cnam.tchat.aca.server.io;

import cnam.tchat.aca.server.receive.ReceiveProcess;

public class Command {

	/*public static void Commande(String post, String arguments){
		if(post =="#channel"){
			CHANNEL(arguments);
		}else if(post =="#exit"){
			EXIT(arguments);
		}else if(post =="#kik"){
			KIK(arguments);
		}else if(post =="#connect"){
			System.out.println("Test");
			LOGIN(arguments);
		}else if(post =="#quit"){	
			QUIT(arguments);
		}
	}*/
	
	public static void HOW(int nbr) {
		System.out.println(nbr);
		
	}

	
	private static void KIK(String arguments) {
		// TODO Auto-generated method stub
		
	}

	private static void CHANNEL(String arguments) {
		System.out.println("Command 'channel' detected");
		
	}
	
	private static void EXIT(String arguments) {
		System.out.println("Command 'exit' detected");
		
	}
	
	public static void LOGIN(String arguments) {
		System.out.println("Command 'connect' detected");
		
	}
	
	private static void QUIT(String arguments) {
		System.out.println("Command 'quit' detected");
		
	}

}
