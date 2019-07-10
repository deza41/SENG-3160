package NewcastleConnectionsPrototype.Group4.actions.events;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.event.ViewEventsAction;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rhys on 28/10/2017.
 */
public class ViewEventActionTest extends StrutsTestCase{

    private Map<String, Object> sessionMap = new HashMap<>();

    private UserBean testBean = new UserBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testExecute() throws Exception {
        testBean.setID(1);

        ActionProxy proxy = getActionProxy("/viewEvents.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        ViewEventsAction mockAction = (ViewEventsAction) proxy.getAction();

        String result = proxy.execute();


        assertEquals("success", result);

    }
}
