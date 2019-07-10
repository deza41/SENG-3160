package NewcastleConnectionsPrototype.Group4.actions.events;

import NewcastleConnectionsPrototype.Group4.DBManager;
import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.event.ViewEventInformationAction;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rhys on 28/10/2017.
 */
public class ViewEventInformationActionTest extends StrutsTestCase{
    Map<String, Object> sessionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {
        String eventID = "1";

        request.setParameter("eventID", eventID);

        ActionProxy proxy = getActionProxy("/viewEventInformation.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        ViewEventInformationAction mockAction = (ViewEventInformationAction) proxy.getAction();

        String result = proxy.execute();

        Result<EventsRecord> eventsRecords = mockAction.getEventsRecordResult();
        Result<BusinessuserRecord> businessuserRecords = mockAction.getBusinessuserRecordResult();

        mockAction.setEventID(2);
        mockAction.setSavings(20);
        mockAction.setSavingsPercentage(30);

        assertEquals("success", result);
        assertEquals(2, mockAction.getEventID());
        assertEquals(20.0, mockAction.getSavings());
        assertEquals(30.0, mockAction.getSavingsPercentage());
    }
}
