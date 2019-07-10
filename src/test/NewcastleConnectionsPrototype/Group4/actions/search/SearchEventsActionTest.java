package NewcastleConnectionsPrototype.Group4.actions.search;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SearchEventsActionTest extends StrutsTestCase{
    Map<String, Object> sessionMap = new HashMap<String, Object>();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {
        ActionProxy proxy = getActionProxy("/searchEvent.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        SearchEventsAction action = (SearchEventsAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);
    }

}