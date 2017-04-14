/**
 * 
 */
package cnam.tchat.aca.server.io;
import java.sql.Date;

/**
 * @author Arnold
 *
 */
public class LogInfo {

	private int idLog;
	private String ipAdress;
	private Date hour;
	
	/**
	 * @return the idLog
	 */
	public int getIdLog() {
		return idLog;
	}

	/**
	 * @param idLog the idLog to set
	 */
	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	/**
	 * @return the ipAdress
	 */
	public String getIpAdress() {
		return ipAdress;
	}

	/**
	 * @param ipAdress the ipAdress to set
	 */
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	/**
	 * @return the hour
	 */
	public Date getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(Date hour) {
		this.hour = hour;
	}

	public LogInfo() {
		//Useless
	}

}
