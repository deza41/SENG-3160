package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tyrone on 17/10/2017.
 */
public class ViewDealsActionTest extends StrutsTestCase {



    Result<DealsRecord> dealsRecordResult;
    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();

    @Before//gets the database
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }


    @Test
    public void testExecute() throws Exception {
        //request.setParameter("dealID", "1");

        testBean.setID(1);

        ActionProxy proxy = getActionProxy("/viewDeals.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ViewDealsAction action = (ViewDealsAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);
    }

    @Test
    public void testGetModel() throws Exception {

        testBean.setID(1);

        ActionProxy proxy = getActionProxy("/viewDeals.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ViewDealsAction action = (ViewDealsAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals(1, (int)  action.getModel().get(0).getDealid());

    }

}