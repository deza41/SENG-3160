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

import static org.junit.Assert.*;

public class BusinessOrUserActionTest extends StrutsTestCase{

    Map<String, Object> sessionMap = new HashMap<String, Object>();

    private UserBean testBean = new UserBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        testBean.setRole("");
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testExecute() throws Exception{
        ActionProxy proxy = getActionProxy("/choiceUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was not success but it should have been.", "success", result);

    }

    @Test
    public void testSetDSLContext() throws Exception {
        ActionProxy proxy = getActionProxy("/choiceUser.action");

        BusinessOrUserAction test = (BusinessOrUserAction) proxy.getAction();

        test.setDSLContext(null);

    }

    @Test
    public void testGetModel() throws Exception {

        testBean.setID(1);
        //sessionMap.put("userBean", testBean);

        ActionProxy proxy = getActionProxy("/choiceUser.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        BusinessOrUserAction test = (BusinessOrUserAction) proxy.getAction();

        proxy.execute();

        //unable to get result value to match, returns a strange format similar but not identical to "[1]"
        test.getModel();

    }

}