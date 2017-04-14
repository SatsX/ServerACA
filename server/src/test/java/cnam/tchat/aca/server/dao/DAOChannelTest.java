/**
 * 
 */
package cnam.tchat.aca.server.dao;

import java.sql.Date;

import cnam.tchat.aca.server.AppTest;
import cnam.tchat.aca.server.factory.DAOFactory;
import cnam.tchat.aca.server.io.Channel;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author arnold / adrien / cihat 
 *
 */
public class DAOChannelTest extends TestCase {

	/**
	 * @param name
	 */
	
	private static final String URL = "jdbc:mysql://localhost:3306/chatirc?useSSL=false";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";
	
	public DAOChannelTest(String name) {
		super(name);
		
	}
	
	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    /**
     * Rigourous Test :-)
     */
    
    @SuppressWarnings("deprecation")
	public void testDAOChannel()
    {
        try {
        	DAOChannel d = (DAOChannel) DAOFactory.getDAOChannel();
			Channel ch = new Channel();
			ch.setChannelId(2);
			ch.setChannelName("Channel7");
			//Test creation
			d.create(ch);
			Channel ch2;
			ch2 = d.find(2);
			assertTrue(ch.compareTo(ch2));
			
			//Test update
			Channel ch3 = new Channel();
			ch3.setChannelId(2);
			ch3.setChannelName("Channel2.0");
			
			d.update(ch3);
			
			Channel ch4;
			ch4 = d.find(2);
			assertTrue(ch3.compareTo(ch4));

			//Test delete
			d.delete(ch3);
			
			
			Channel ch5 = new Channel();
			ch5.setUser_id(1);
			ch5.setChannelId(4);
			d.userJoinChannel(ch5);
			

			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    }

}
