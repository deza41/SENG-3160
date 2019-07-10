package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionProxyFactory;
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
public class TrackYourOrderActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();


    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    // business user
    @Test
    public void testExecute() throws Exception {

        UserBean userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", 1, false);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/trackYourOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        TrackYourOrderAction test = (TrackYourOrderAction) proxy.getAction();

        test.setTrackingID("0");

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }

    // user
    @Test
    public void testExecute2() throws Exception {

        UserBean userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "user", 1, false);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/trackYourOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        TrackYourOrderAction test = (TrackYourOrderAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }

    // crash user
    @Test
    public void testExecute3() throws Exception {

        UserBean userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "user", -2, false);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/trackYourOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        TrackYourOrderAction test = (TrackYourOrderAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }
    // crash business user
    @Test
    public void testExecute4() throws Exception {

        UserBean userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", -2, false);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/trackYourOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        TrackYourOrderAction test = (TrackYourOrderAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }

    // business user
    @Test
    public void testExecute5() throws Exception {

        UserBean userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", 1, true);

        sessionMap.put("userBean", userBean);

        ActionProxy proxy = getActionProxy("/trackYourOrder.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        TrackYourOrderAction test = (TrackYourOrderAction) proxy.getAction();


        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }

    @Test
    public void testGetModel() throws Exception {

        ActionProxy proxy = getActionProxy("/trackYourOrder.action");

        TrackYourOrderAction test = (TrackYourOrderAction) proxy.getAction();

        test.getModel();
    }

    @Test
    public void testgetTrackingID() throws Exception {

        ActionProxy proxy = getActionProxy("/trackYourOrder.action");

        TrackYourOrderAction test = (TrackYourOrderAction) proxy.getAction();

        test.setTrackingID("0");
        assertEquals("0", test.getTrackingID());

    }

}