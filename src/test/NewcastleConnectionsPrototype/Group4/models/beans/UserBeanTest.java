package NewcastleConnectionsPrototype.Group4.models.beans;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 28/10/2017.
 */
public class UserBeanTest {
    @Test
    public void testUserBean() throws Exception {
        boolean word = true;
        UserBean UserBean = new UserBean("swag","dsd","dwd","wdwdw","dec","dwcw",1,word);
    }
    @Test
    public void testGetUsername() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setUsername("232");
        assertEquals("232",  UserBean.getUsername());
    }

    @Test
    public void testGetFirstName() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setFirstName("232");
        assertEquals("232",  UserBean.getFirstName());
    }

    @Test
    public void testGetLastName() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setLastName("232");
        assertEquals("232",  UserBean.getLastName());
    }

    @Test
    public void testGetEmail() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setEmail("232");
        assertEquals("232",  UserBean.getEmail());

    }

    @Test
    public void testGetContactNo() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setContactNo("232323233");
        assertEquals("232323233",  UserBean.getContactNo());
    }

    @Test
    public void testGetRole() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setRole("232");
        assertEquals("232",  UserBean.getRole());
    }

    @Test
    public void testGetID() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setID(1);
        assertEquals(1,  UserBean.getID());
    }

    @Test
    public void testIsLoggedIn() throws Exception {
        UserBean UserBean = new UserBean();
        UserBean.setLoggedIn(true);
        assertEquals(true,  UserBean.isLoggedIn());

    }

}