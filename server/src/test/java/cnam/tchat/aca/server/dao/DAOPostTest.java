/**
 * 
 */
package cnam.tchat.aca.server.dao;

import java.sql.Date;

import cnam.tchat.aca.server.AppTest;
import cnam.tchat.aca.server.io.Post;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author arnold / adrien / cihat 
 *
 */
public class DAOPostTest extends TestCase {

	/**
	 * @param name
	 */
	
	public DAOPostTest(String name) {
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
	public void testDAOPost()
    {
        try {
			DAOPost d = new DAOPost();
			Post p = new Post();
			p.setPostId(1);
			p.setContent("Coucou enchanté");
			p.setPostDate("1993");
			p.setUserId(1");
			p.setChannelId(1);
			
			//Test creation
			d.create(p);
			Post p2;
			p2 = d.find(1);
			assertTrue(p.compareTo(p2));
			
						
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    }

}
