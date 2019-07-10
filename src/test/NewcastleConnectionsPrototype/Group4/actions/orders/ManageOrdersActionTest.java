package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Will on 26/09/2017.
 */
public class ManageOrdersActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean userBean;


    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", 1, false);

        sessionMap.put("userBean", userBean);
    }

    // unconfirmed bookings
    @Test
    public void testExecute() throws Exception {


        ActionProxy proxy = getActionProxy("/manageOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ManageOrdersAction test = (ManageOrdersAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }

    // confirmed bookings
    @Test
    public void testExecute2() throws Exception {

        userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", 3, false);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/manageOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ManageOrdersAction test = (ManageOrdersAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);
    }
    // no id
    @Test
    public void testExecute3() throws Exception {

        userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", -1, false);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/manageOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ManageOrdersAction test = (ManageOrdersAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }
    // no id
    @Test
    public void testExecute4() throws Exception {

        userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", -2, false);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/manageOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ManageOrdersAction test = (ManageOrdersAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }

    @Test
    public void getModel() throws Exception {
    }

}