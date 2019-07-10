package NewcastleConnectionsPrototype.Group4.actions.events;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.event.CreateEventAction;
import NewcastleConnectionsPrototype.Group4.models.beans.EventTimeBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rhys on 24/10/2017.
 */
public class CreateEventActionTest extends StrutsTestCase {
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

        ActionProxy proxy = getActionProxy("/createEvent.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        CreateEventAction mockAction = (CreateEventAction) proxy.getAction();

        //String result = proxy.execute();

        EventTimeBean eventTimes = new EventTimeBean("4", "60");

        List<EventTimeBean> eventTimesList = new LinkedList<>();
        eventTimesList.add(eventTimes);

        //assertEquals("success", result);
        mockAction.setEventImageFileFileName("Beach");
        mockAction.setMyFileContentType("Event");
        mockAction.setEventTimes(eventTimesList);

        // assertEquals("success", result);
        assertEquals("Beach", mockAction.getEventImageFileFileName());
        assertEquals("Event", mockAction.getMyFileContentType());
        assertEquals(eventTimesList, mockAction.getEventTimes());
    }

}
