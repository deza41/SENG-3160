package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.jooq.DSLContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;
import static org.junit.Assert.*;

/**
 * Created by Tyrone on 17/10/2017.
 */
public class DiscontinueDealActionTest extends StrutsTestCase {

    DiscontinueDealAction DiscontinueDealAction = new DiscontinueDealAction();
    Map<String, Object> sessionMap = new HashMap<String, Object>();

    private DSLContext db;
    @Before//gets the database
    public void setUp() throws Exception {
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {

        request.setParameter("startDate", "2017-06-10");
        ActionProxy proxy = getActionProxy("/discontinueDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        DiscontinueDealAction action = (DiscontinueDealAction) proxy.getAction();
        String result = proxy.execute();
        action.setTargetDealID(1);
        assertEquals("Result returned shows the form was created successfully ", "success", result);
    }

    @Test
    public void testGetTargetDealID() throws Exception {
        DiscontinueDealAction.setTargetDealID(1);
        assertEquals(1,DiscontinueDealAction.getTargetDealID());
    }

}