package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 17/10/2017.
 */
public class ViewDealInformationActionTest extends StrutsTestCase{

    Result<DealsRecord> dealsRecordResult;
    Result<BusinessuserRecord> businessuserRecordResult;
    Map<String, Object> sessionMap = new HashMap<String, Object>();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception  {

        request.setParameter("dealID", "1");

        ActionProxy proxy = getActionProxy("/viewDealInformation.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ViewDealInformationAction action = (ViewDealInformationAction) proxy.getAction();
        String result = proxy.execute();

        // ViewDealInformationAction ViewDealInformationAction = new ViewDealInformationAction();
        // ViewDealInformationAction.setDealID(2);
        // businessuserRecordResult = ViewDealInformationAction.getBusinessuserRecordResult();
        // dealsRecordResult = ViewDealInformationAction.getDealsRecordResult();
         //action.setDealID(1);
         businessuserRecordResult = action.getBusinessuserRecordResult();
         dealsRecordResult = action.getDealsRecordResult();

        assertEquals(1,(int) dealsRecordResult.get(0).getDealid());
        assertEquals(1,(int) businessuserRecordResult.get(0).getBusinessid());
        //check the total view has increased

    }

    @Test
    public void testGetDealsRecordResult() throws Exception {
        //ViewDealInformationAction ViewDealInformationAction = new ViewDealInformationAction();
        //request.setParameter("dealID", "1");

        ActionProxy proxy = getActionProxy("/viewDealInformation.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ViewDealInformationAction action = (ViewDealInformationAction) proxy.getAction();

        action.setDealID(1);

        String result = proxy.execute();


        dealsRecordResult = action.getDealsRecordResult();
        assertEquals(1, (int) dealsRecordResult.get(0).getDealid());
    }

    @Test
    public void testGetBusinessuserRecordResult() throws Exception {
        //ViewDealInformationAction ViewDealInformationAction = new ViewDealInformationAction();
        //ViewDealInformationAction.setDealID(2);

        ActionProxy proxy = getActionProxy("/viewDealInformation.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ViewDealInformationAction action = (ViewDealInformationAction) proxy.getAction();
        action.setDealID(1);

        String result = proxy.execute();

        businessuserRecordResult = action.getBusinessuserRecordResult();
        assertEquals(1, (int) businessuserRecordResult.get(0).getBusinessid());
    }

    @Test
    public void testGetDealID() throws Exception {

        //request.setParameter("dealID", "1");

        //ActionProxy proxy = getActionProxy("/viewDealInformation.action");
       // proxy.getInvocation().getInvocationContext().setSession(sessionMap);
       // ViewDealInformationAction action = (ViewDealInformationAction) proxy.getAction();
       // String result = proxy.execute();

        //action.setDealID(1);
        ViewDealInformationAction ViewDealInformationAction = new ViewDealInformationAction();
        ViewDealInformationAction.setDealID(1);
        assertEquals(1,  ViewDealInformationAction.getDealID());
    }

    @Test
    public void testGetSavings() throws Exception {

        request.setParameter("dealID", "1");


        ActionProxy proxy = getActionProxy("/viewDealInformation.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ViewDealInformationAction action = (ViewDealInformationAction) proxy.getAction();

        String result = proxy.execute();

        action.setSavings(15);
        assertEquals(15.0,  action.getSavings());

    }

    @Test
    public void testGetSavingsPercentage() throws Exception {

        request.setParameter("dealID", "1");


        ActionProxy proxy = getActionProxy("/viewDealInformation.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ViewDealInformationAction action = (ViewDealInformationAction) proxy.getAction();

        action.setSavingsPercentage(10);

        String result = proxy.execute();

        action.setSavingsPercentage(10);

        assertEquals(10.0,  action.getSavingsPercentage());

    }

}