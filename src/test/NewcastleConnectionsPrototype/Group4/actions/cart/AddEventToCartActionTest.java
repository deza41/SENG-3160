package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon janmaat on 26/09/2017.
 */
public class AddEventToCartActionTest extends StrutsTestCase{

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
    }


    //testing the struts feature of setting the events id.
    @Test
    public void testSetEventId() throws Exception {

        request.setParameter("eventID", "1");
        ActionProxy proxy = getActionProxy("/addEventToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddEventToCartAction test = (AddEventToCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(1, test.getEventID());
    }

    //tests if 1 event, 1 quantity, and correct event getting added.
    @Test
    public void testExecuteInitialEvent() throws Exception {

        request.setParameter("eventID", "2");

        ActionProxy proxy = getActionProxy("/addEventToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddEventToCartAction test = (AddEventToCartAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals(2, testBean.getEventsCart().get(0).getEventID());
        assertEquals(1, testBean.getEventsCart().get(0).getQuantity());
        assertEquals(1, testBean.getEventsCart().size());
    }

    //testing if adding the same "event" again increases the quantity of that instance not the size of the events cart.
    @Test
    public void testExecuteAddIn2ndEvent() throws Exception {
        request.setParameter("eventID", "2");
        BookingEventModel newEvent = new BookingEventModel(1, 2, null, null, null, null, "testing", false, 10.00, 5.00, "tittle", "description");

        testBean.addEventToPackage(newEvent);
        ActionProxy proxy = getActionProxy("/addEventToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddEventToCartAction test = (AddEventToCartAction) proxy.getAction();

        String result = proxy.execute();
        assertEquals(2, test.getEventID());
        assertEquals(2, testBean.getEventsCart().get(0).getQuantity());
        assertEquals(1, testBean.getEventsCart().size());
    }

    //testing if adding a dfierent "event" increases the size of the list.
    @Test
    public void testExecuteAddIn3rdEvent() throws Exception {
        request.setParameter("eventID", "1");
        BookingEventModel newEvent = new BookingEventModel(1, 2, null, null, null, null, "testing", false, 10.00, 5.00, "tittle", "description");

        testBean.addEventToPackage(newEvent);
        ActionProxy proxy = getActionProxy("/addEventToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddEventToCartAction test = (AddEventToCartAction) proxy.getAction();

        String result = proxy.execute();
        assertEquals(1, test.getEventID());
        assertEquals(1, testBean.getEventsCart().get(0).getQuantity());
        assertEquals(2, testBean.getEventsCart().size());
    }

    //testing if an id that doesnt exist in the database returns an error.
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExecuteAddInEvent() throws Exception {
        request.setParameter("eventID", "4");

        ActionProxy proxy = getActionProxy("/addEventToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddEventToCartAction test = (AddEventToCartAction) proxy.getAction();

        String result = proxy.execute();
    }


}