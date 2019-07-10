package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 24/10/2017.
 */
public class EditDealActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();

    @Before//gets the database
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }



    @Test
    public void testExecute() throws Exception {
        ActionProxy proxy = getActionProxy("/editDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        EditDealAction action = (EditDealAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);
    }

    @Test
    public void testGetTargetDealID() throws Exception {

        request.setParameter("targetDealID", "1");

        ActionProxy proxy = getActionProxy("/editDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        EditDealAction action = (EditDealAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("checks if targetDealID is retrieved and set", 1, action.getTargetDealID());
    }

    @Test
    public void testSetTargetDealID() throws Exception {



        ActionProxy proxy = getActionProxy("/editDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        EditDealAction action = (EditDealAction) proxy.getAction();
        String result = proxy.execute();

        action.setTargetDealID(1);

        assertEquals("checks if targetDealID is retrieved and set", 1, action.getTargetDealID());
    }

    @Test
    public void testGetModel() throws Exception { //dono how to create a model inside of the action

        request.setParameter("targetDealID", "1");

        ActionProxy proxy = getActionProxy("/editDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        EditDealAction action = (EditDealAction) proxy.getAction();
        String result = proxy.execute();
        //action.getModel().get(0).setDealid(1);
        assertEquals(1 ,(int) action.getModel().get(0).getDealid());

    }

}