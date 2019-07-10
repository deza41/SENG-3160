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

public class DisplayOneBusinessAccountTest extends StrutsTestCase{
    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testExecute() throws Exception {

        ActionProxy proxy = getActionProxy("/DisplayEditBusiness.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);
    }

    @Test
    public void testSetDSLContext() throws Exception {
        ActionProxy proxy = getActionProxy("/DisplayEditBusiness.action");

        DisplayOneBusinessAccount test = (DisplayOneBusinessAccount) proxy.getAction();

        test.setDSLContext(null);

    }

    @Test
    public void testGetModel() throws Exception {

        testBean.setID(1);
        sessionMap.put("userBean", testBean);

        ActionProxy proxy = getActionProxy("/DisplayEditBusiness.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        DisplayOneBusinessAccount test = (DisplayOneBusinessAccount) proxy.getAction();

        proxy.execute();

        //unable to get result value to match, returns a strange format similar but not identical to "[1]"
        assertEquals(1,(int) test.getModel().get(0).getBusinessid());

    }

    @Test
    public void testSetSession() throws Exception {
        ActionProxy proxy = getActionProxy("/DisplayEditBusiness.action");

        DisplayOneBusinessAccount test = (DisplayOneBusinessAccount) proxy.getAction();

        test.setSession(null);
    }

}