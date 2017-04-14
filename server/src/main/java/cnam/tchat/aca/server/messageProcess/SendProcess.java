package cnam.tchat.aca.server.messageProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class SendProcess implements Runnable{
	// Write
	private OutputStream out = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;
	private Socket so;

	public SendProcess() {		
		//
	}

	public String write(String msgToClient) throws IOException{
		out = so.getOutputStream();
		osw = new OutputStreamWriter(out, "UTF-8");
		bw = new BufferedWriter(osw);
		
		System.out.println("Writing : " + msgToClient);
		bw.write(msgToClient);
		bw.newLine();
		bw.flush();
		
		return msgToClient;
		
	}
		
	public void Cancel() throws IOException{
		so.close();
	}


	public void run() {
			
			try {
				MessageFromServer mfc;
				mfc = new MessageFromServer(new ReceiveProcess(so).read());
				String modif = mfc.toString();
		   		MessageToServer mtc = new MessageToServer(modif);
		   		String line = mtc.toString();
		   		write(line);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}