package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

public class LoginActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();

    UserBean user = new UserBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", user);
    }

    @Test
    public void testExecute() throws Exception{

        String testUsername = "swagmasta";
        String testPassword = "thicc";
        //set username and password
        request.setParameter("username", testUsername);
        request.setParameter("password", testPassword);

        ActionProxy proxy = getActionProxy("/login.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction test = (LoginAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was not success but it should have been.", "success", result);

        assertEquals(testUsername, test.getUsername());
        assertEquals(testPassword, test.getPassword());

    }

    @Test
    public void testExecuteAsAdmin() throws Exception{
        String testUsername = "admin";
        String testPassword = "password";
        //set username and password
        request.setParameter("username", testUsername);
        request.setParameter("password", testPassword);

        ActionProxy proxy = getActionProxy("/login.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction test = (LoginAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was not success but it should have been.", "success", result);
    }

    @Test
    public void testExecuteAsBusiness() throws Exception{
        String testUsername = "johnstan";
        String testPassword = "white4345";
        //set username and password
        request.setParameter("username", testUsername);
        request.setParameter("password", testPassword);

        ActionProxy proxy = getActionProxy("/login.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction test = (LoginAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was not success but it should have been.", "business", result);
    }

    @Test
    public void testExecuteAsNonMatch() throws Exception{
        String testUsername = "invalid";
        String testPassword = "invalid";
        //set username and password
        request.setParameter("username", testUsername);
        request.setParameter("password", testPassword);

        ActionProxy proxy = getActionProxy("/login.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction test = (LoginAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been.", "error", result);
    }

    @Test
    public void testLogout() throws Exception{

        ActionProxy proxy = getActionProxy("/login.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction test = (LoginAction) proxy.getAction();

        String result = proxy.execute();

        test.logout();

        assertEquals("Result returned form executing the action was success but it should not have been.", "error", result);
    }

    @Test
    public void testGetUsername() throws Exception {
        ActionProxy proxy = getActionProxy("/login.action");

        LoginAction test = (LoginAction) proxy.getAction();
        String userNameString = "Test Username";
        test.setUsername(userNameString);
        assertEquals(userNameString, test.getUsername());
    }

    @Test
    public void testGetPassword() throws Exception {
        ActionProxy proxy = getActionProxy("/login.action");

        LoginAction test = (LoginAction) proxy.getAction();
        String passwordString = "Test Password";
        test.setPassword(passwordString);
        assertEquals(passwordString, test.getPassword());
    }


}