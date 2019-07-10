package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingModel;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon janmaat on 17/10/2017.
 */
public class RemoveBookingFromCartActionTest extends StrutsTestCase {
    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
    }

    @Test
    public void testGetLocalBookingID() throws Exception {
        request.setParameter("localBookingID", "1");
        ActionProxy proxy = getActionProxy("/removeBookingFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveBookingFromCartAction test = (RemoveBookingFromCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(1, test.getLocalBookingID());
    }

    //removes 1 "booking" from the bookings list, and adds to the deals list.
    @Test
    public void testExecute() throws Exception {
        request.setParameter("localBookingID", "0");
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(2, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);

        BookingModel temp = new BookingModel(0, null, null, null, 1, 2, newDeal);
        testBean.addBookingToPackage(temp);

        ActionProxy proxy = getActionProxy("/removeBookingFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveBookingFromCartAction test = (RemoveBookingFromCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(0, testBean.getBookings().size());
        assertEquals(0, test.getLocalBookingID());
        assertEquals(1, testBean.getDealCart().size());
    }

    //try to remove a booking that is not in the list (should not remove anything)
    @Test
    public void testExecute2() throws Exception {
        request.setParameter("localBookingID", "1");
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(2, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);

        BookingModel temp = new BookingModel(0, null, null, null, 1, 2, newDeal);
        testBean.addBookingToPackage(temp);

        ActionProxy proxy = getActionProxy("/removeBookingFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveBookingFromCartAction test = (RemoveBookingFromCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(1, testBean.getBookings().size());
        assertEquals(1, test.getLocalBookingID());
    }


}