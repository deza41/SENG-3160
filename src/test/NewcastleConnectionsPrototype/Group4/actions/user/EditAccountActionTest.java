package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.DBManager;
import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EditAccountActionTest extends StrutsTestCase{

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();

    DBManager dbm = new DBManager();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        testBean.setUsername("Test userName");
        testBean.setFirstName("Test firstName");
        testBean.setLastName("Test lastName");
        testBean.setEmail("Test@email");
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testExecute() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String phoneNumber = "Test phoneNumber";
        String email = "Test@email";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

        assertEquals(userImageContentType, test.getUserImageContentType());
        assertEquals(userImageFileName, test.getUserImageFileName());
        assertEquals(password, test.getPassword());
        assertEquals(firstName, test.getFirstName());
        assertEquals(lastName, test.getLastName());
        assertEquals(phoneNumber, test.getPhoneNumber());
        assertEquals(email, test.getEmail());
        assertEquals(postCode, test.getPostCode());
        assertEquals(dateOfBirth, test.getDateOfBirth());

        dbm.delete("user", "Test userName");

    }

    //test targeting invalid email address format
    @Test
    public void testExecute2() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String phoneNumber = "Test phoneNumber";
        String email = "Test email";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was successful but it should have been.\n Invalid email address was not detected", "error", result);

        dbm.delete("user", "Test userName");

    }

    //testing server-side validation of required user variables

    //testing validation for null firstName
    @Test
    public void testExecute5() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "";
        String lastName = "Test lastName";
        String phoneNumber = "Test phoneNumber";
        String email = "Test@email";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expecting error due to null firstName", "error", result);

        dbm.delete("user", "Test userName");
    }

    //testing validation for null lastName
    @Test
    public void testExecute6() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "Test firstName";
        String lastName = "";
        String phoneNumber = "Test phoneNumber";
        String email = "Test@email";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expecting error due to null lastName", "error", result);

        dbm.delete("user", "Test userName");
    }

    //testing validation for null phoneNumber
    @Test
    public void testExecute7() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String phoneNumber = "";
        String email = "Test@email";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expecting error due to null phoneNumber", "error", result);

        dbm.delete("user", "Test userName");
    }

    //testing validation for null dateOfBirth
    @Test
    public void testExecute8() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String phoneNumber = "Test phoneNumber";
        String email = "Test@email";
        String postCode = "Test postCode";
        Date dateOfBirth = null;

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expecting error due to null dateOfBirth", "error", result);

        dbm.delete("user", "Test userName");
    }

    //Change password
    @Test
    public void testExecute9() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "newPass";
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String phoneNumber = "Test phoneNumber";
        String email = "Test@email";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

        assertEquals(userImageContentType, test.getUserImageContentType());
        assertEquals(userImageFileName, test.getUserImageFileName());
        assertEquals(password, test.getPassword());
        assertEquals(firstName, test.getFirstName());
        assertEquals(lastName, test.getLastName());
        assertEquals(phoneNumber, test.getPhoneNumber());
        assertEquals(email, test.getEmail());
        assertEquals(postCode, test.getPostCode());
        assertEquals(dateOfBirth, test.getDateOfBirth());

        dbm.delete("user", "Test userName");

    }

    //taken email address
    @Test
    public void testExecute10() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String phoneNumber = "Test phoneNumber";
        String email = "newcastleConnect@gmail.com";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was successful but it should have been. Taken email should have been detected.", "error", result);

        assertEquals(userImageContentType, test.getUserImageContentType());
        assertEquals(userImageFileName, test.getUserImageFileName());
        assertEquals(password, test.getPassword());
        assertEquals(firstName, test.getFirstName());
        assertEquals(lastName, test.getLastName());
        assertEquals(phoneNumber, test.getPhoneNumber());
        assertEquals(email, test.getEmail());
        assertEquals(postCode, test.getPostCode());
        assertEquals(dateOfBirth, test.getDateOfBirth());

        dbm.delete("user", "Test userName");

    }

    //test targeting invalid email address format
    @Test
    public void testExecute11() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        String userImageContentType = "Test userImageContentType";
        String userImageFileName = "Test userImageFileName";
        String password = "";
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String phoneNumber = "Test phoneNumber";
        String email = "TestEmail";
        String postCode = "Test postCode";
        Date dateOfBirth = new Date();

        test.setUserImageContentType(userImageContentType);
        test.setUserImageFileName(userImageFileName);
        test.setPassword(password);
        test.setFirstName(firstName);
        test.setLastName(lastName);
        test.setPhoneNumber(phoneNumber);
        test.setEmail(email);
        test.setPostCode(postCode);
        test.setDateOfBirth(dateOfBirth);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was successful but it should have been.\n Invalid email address was not detected", "error", result);

        dbm.delete("user", "Test userName");

    }

    @Test
    public void testGettersSetters() throws Exception{
        ActionProxy proxy = getActionProxy("/editUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        EditAccountAction test = (EditAccountAction) proxy.getAction();

        test.setUserImage(null);
        test.getUserImage();
    }

    @Test
    public void testSetDSLContext() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        test.setDSLContext(null);

    }

    @Test
    public void testSetSession() throws Exception {
        ActionProxy proxy = getActionProxy("/editUser.action");

        EditAccountAction test = (EditAccountAction) proxy.getAction();

        test.setSession(null);
    }

}