package NewcastleConnectionsPrototype.Group4.actions.events;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.event.DiscontinueEventAction;
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
public class DiscontinueEventsActionTest extends StrutsTestCase{

    private Map<String, Object> sessionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {
        String targetEventID = "1";

        request.setParameter("targetEventID", targetEventID);

        ActionProxy proxy = getActionProxy("/discontinueEvent.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        DiscontinueEventAction mockAction = (DiscontinueEventAction) proxy.getAction();

        String result = proxy.execute();

        mockAction.setTargetEventID(2);

        assertEquals("success", result);
        assertEquals(2, mockAction.getTargetEventID());
    }
}
