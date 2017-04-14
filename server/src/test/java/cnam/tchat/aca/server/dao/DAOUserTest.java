/**
 * 
 */
package cnam.tchat.aca.server.dao;

import java.sql.Date;

import cnam.tchat.aca.server.AppTest;
import cnam.tchat.aca.server.factory.DAOFactory;
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
	
	private static final String URL = "jdbc:mysql://localhost:3306/chatirc?useSSL=false";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";
	
	public DAOUserTest(String name) {
		super(name);
		
	}
	
    
    /**
     * Rigourous Test :-)
     */
    
    @SuppressWarnings("deprecation")
	public void testDAOUser()
    {
        try {
			DAOUser d = (DAOUser) DAOFactory.getDAOUser();
			User u = new User();
			u.setUserId(2);
			u.setUserName("toto");
			//Test creation
			d.create(u);
			User u2;
			u2 = d.find(2);
			assertTrue(u.compareTo(u2));
			
			//Test update
			User u3 = new User();
			u3.setUserId(2);
			u3.setUserName("Toutou");
			
			d.update(u3);
			
			User u4;
			u4 = d.find(2);
			assertTrue(u3.compareTo(u4));

			//Test delete
			d.delete(u4);
			
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    }

}
