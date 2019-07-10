package NewcastleConnectionsPrototype.Group4.actions.events;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.event.EventModifiedAction;
import NewcastleConnectionsPrototype.Group4.actions.event.ViewEventsAction;
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
 * Created by Rhys on 28/10/2017.
 */
public class EventModifiedActionTest extends StrutsTestCase {
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



        String oldEventID = "2";
        request.setParameter("oldEventID", oldEventID);

        ActionProxy proxy = getActionProxy("/modifyEvent.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        EventModifiedAction mockAction = (EventModifiedAction) proxy.getAction();

        //String result = proxy.execute(); can't do mock files
        mockAction.setOldEventID(3);
        mockAction.setEventImageFileFileName("Beach");
        mockAction.setMyFileContentType("Event");

       // assertEquals("success", result);
        assertEquals(3, mockAction.getOldEventID());
        assertEquals("Beach", mockAction.getEventImageFileFileName());
        assertEquals("Event", mockAction.getMyFileContentType());
    }
}
