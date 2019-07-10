package NewcastleConnectionsPrototype.Group4.interceptors;

import NewcastleConnectionsPrototype.Group4.actions.CheckLoggedInInterceptorMockAction;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rhys on 23/10/2017.
 */
public class CheckLoggedInIntercepterTest  extends StrutsJUnit4TestCase<CheckLoggedInIntercepterTest>{

    @Override
    protected String getConfigPath() {
        return "struts-test.xml";
    }

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();

    public void setUp() throws Exception {
        super.setUp();
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testInterceptAllow() //allowed access
    {
        ActionProxy proxy = getActionProxy("/testCheckLoggedIn.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        CheckLoggedInInterceptorMockAction intercepter = (CheckLoggedInInterceptorMockAction) proxy.getAction();

        assertNotNull(intercepter);

        try {
            testBean.setRole("business"); //set up to only work with business role, any other role will return "error"
            String result = proxy.execute();
            assertEquals("success", result);

        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    @Test
    public void testInterceptDisallow() //not allowed access
    {
        ActionProxy proxy = getActionProxy("/testCheckLoggedIn.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        CheckLoggedInInterceptorMockAction intercepter = (CheckLoggedInInterceptorMockAction) proxy.getAction();

        assertNotNull(intercepter);

        try {
            testBean.setRole("admin");
            String result = proxy.execute();
            assertEquals("error", result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInterceptAll() //everyone allowed access
    {
        ActionProxy proxy = getActionProxy("/testCheckLoggedIn2.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        CheckLoggedInInterceptorMockAction intercepter = (CheckLoggedInInterceptorMockAction) proxy.getAction();

        assertNotNull(intercepter);

        try {
            testBean.setRole("user");
            String result = proxy.execute();
            assertEquals("success", result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIntercept() //trying for 100% line
    {
        ActionProxy proxy = getActionProxy("/testCheckLoggedIn3.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        CheckLoggedInInterceptorMockAction intercepter = (CheckLoggedInInterceptorMockAction) proxy.getAction();

        assertNotNull(intercepter);

        try {
            testBean.setRole("business");
            String result = proxy.execute();
            assertEquals("error", result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
