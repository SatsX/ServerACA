/**
 * 
 */
package cnam.tchat.aca.server.dao;

import java.sql.Date;

import cnam.tchat.aca.server.AppTest;
import cnam.tchat.aca.server.io.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author arnold / adrien / cihat 
 *
 */
public class DAOUserTest extends TestCase {

	/**
	 * @param name
	 */
	
	public DAOUserTest(String name) {
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
	public void testDAOUser()
    {
        try {
			DAOUser d = new DAOUser();
			User u = new User();
			u.setUserId(1);
			u.setUserName("toto");
			
			d.create(u);
			User u2;
			u2 = d.find(1);
			assertTrue(u.compareTo(u2));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    }

}
