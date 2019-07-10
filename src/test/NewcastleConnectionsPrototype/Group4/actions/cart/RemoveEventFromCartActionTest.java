package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon janmaat on 22/10/2017.
 */
public class RemoveEventFromCartActionTest extends StrutsTestCase{

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        //SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
    }

    @Test //tests the setter and getter methods.
    public void testGetMethods() throws Exception {
        request.setParameter("eventID", "0");
        request.setParameter("removeQ", "1");

        ActionProxy proxy = getActionProxy("/removeEventFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveEventFromCartAction test = (RemoveEventFromCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(0, test.getEventID());
        assertEquals(1, test.getRemoveQ());
    }

    @Test //list with 1 element and removing in 1 event
    public void testExecute() throws Exception {
        BookingEventModel newEvent = new BookingEventModel(1, 1, null, null, null, null, "event Image", true, 50, 25, "title", "description");
        testBean.addEventToPackage(newEvent);
        request.setParameter("eventID", "1");
        request.setParameter("removeQ", "1");

        ActionProxy proxy = getActionProxy("/removeEventFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveEventFromCartAction test = (RemoveEventFromCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, test.getEventID());
        assertEquals(1, test.getRemoveQ());
        assertEquals(0,testBean.getEventsCart().size());
    }

    @Test //list containing 1 element with a quanitity of 2 and removing 1 quantity
    public void testExecute2() throws Exception {

        BookingEventModel newEvent = new BookingEventModel(1, 1, null, null, null, null, "event Image", true, 50, 25, "title", "description");
        newEvent.setQuantity(1);
        assertEquals(2, newEvent.getQuantity());

        testBean.addEventToPackage(newEvent);
        request.setParameter("eventID", "1");
        request.setParameter("removeQ", "1");

        ActionProxy proxy = getActionProxy("/removeEventFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveEventFromCartAction test = (RemoveEventFromCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, test.getEventID());
        assertEquals(1, test.getRemoveQ());
        assertEquals(1,testBean.getEventsCart().size());
        assertEquals(1, testBean.getEventsCart().get(0).getQuantity());

    }

    @Test //list containing 2 different elements and removing 1 element.
    public void testExecute3() throws Exception {
        BookingEventModel newEvent = new BookingEventModel(1, 1, null, null, null, null, "event Image", true, 50, 25, "title", "description");
        BookingEventModel newEvent2 = new BookingEventModel(1, 2, null, null, null, null, "event Image", true, 50, 25, "title", "description");

        newEvent.setQuantity(1);
        assertEquals(2, newEvent.getQuantity());

        testBean.addEventToPackage(newEvent);
        testBean.addEventToPackage(newEvent2);

        request.setParameter("eventID", "1");
        request.setParameter("removeQ", "2");

        ActionProxy proxy = getActionProxy("/removeEventFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveEventFromCartAction test = (RemoveEventFromCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, test.getEventID());
        assertEquals(2, test.getRemoveQ());
        assertEquals(1, testBean.getEventsCart().size());
        assertEquals(2, testBean.getEventsCart().get(0).getEventID());
    }

    @Test //list containing 2 different elements and attemping to remove an element that doeesnt exist.
    public void testExecute4() throws Exception {
        BookingEventModel newEvent = new BookingEventModel(1, 1, null, null, null, null, "event Image", true, 50, 25, "title", "description");
        BookingEventModel newEvent2 = new BookingEventModel(1, 2, null, null, null, null, "event Image", true, 50, 25, "title", "description");

        newEvent.setQuantity(1);
        assertEquals(2, newEvent.getQuantity());

        testBean.addEventToPackage(newEvent);
        testBean.addEventToPackage(newEvent2);

        request.setParameter("eventID", "0");
        request.setParameter("removeQ", "2");

        ActionProxy proxy = getActionProxy("/removeEventFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveEventFromCartAction test = (RemoveEventFromCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(0, test.getEventID());
        assertEquals(2, test.getRemoveQ());
        assertEquals(2, testBean.getEventsCart().size());
        assertEquals(1, testBean.getEventsCart().get(0).getEventID());
    }





}