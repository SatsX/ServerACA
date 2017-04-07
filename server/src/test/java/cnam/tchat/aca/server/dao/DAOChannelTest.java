/**
 * 
 */
package cnam.tchat.aca.server.dao;

import java.sql.Date;

import cnam.tchat.aca.server.AppTest;
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
			DAOChannel d = new DAOChannel(URL, LOGIN, PASSWORD);
			Channel ch = new Channel();
			ch.setChannelId(1);
			ch.setChannelName("Channel1");
			//Test creation
			d.create(ch);
			Channel ch2;
			ch2 = d.find(1);
			assertTrue(ch.compareTo(ch2));
			
			//Test update
			Channel ch3 = new Channel();
			ch3.setChannelId(1);
			ch3.setChannelName("Channel2.0");
			
			d.update(ch3);
			
			Channel ch4;
			ch4 = d.find(1);
			assertTrue(ch3.compareTo(ch4));

			//Test delete
			d.delete(ch3);
			
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    }

}
