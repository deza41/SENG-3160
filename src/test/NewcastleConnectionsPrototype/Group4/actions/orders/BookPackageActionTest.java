package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.DBManager;
import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.cart.AddDealToCartAction;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingModel;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Will on 26/09/2017.
 */
public class BookPackageActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean userBean;
    PackageBean testBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        userBean = new UserBean("swagmasta", "alex", "perry", "swagGanga@hotmail.com", "0496858467", "business", 1, false);

        sessionMap.put("userBean", userBean);
        sessionMap.put("PackageBean", testBean);
    }

    // no notifications
    @Test
    public void testExecute() throws Exception {

        DBManager dbManager = new DBManager();
        java.util.Date today = new java.util.Date();

        int highestId = dbManager.getHighestId("notifications");

        dbManager.delete("relationUserNotification", "" + highestId);
        dbManager.delete("notifications", "You have an unconfirmed booking by alex perry.");

        BusinessModel tempBusiness = new BusinessModel(1,"asdf",today,today,"000","urlimage","testbusiness");
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        BookingModel newBooking = new BookingModel(0, new Time(today.getTime()),new Time(today.getTime()), today, 1,0,newDeal);
        BookingEventModel newEvent = new BookingEventModel(1,1,today,today,new ArrayList<Time>(), new ArrayList<Time>(), "img", false, 0,0, "title", "description");

        testBean.addDealToPackage(newDeal);
        testBean.addEventToPackage(newEvent);
        testBean.addBookingToPackage(newBooking);

        ActionProxy proxy = getActionProxy("/payment.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        BookPackageAction test = (BookPackageAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, testBean.getDealCart().get(0).getDealID());
        //dbManager.delete("shoppingcart", "testFirst");
    }

    // where there are notifications already
    @Test
    public void testExecute2() throws Exception {

        DBManager dbManager = new DBManager();
        java.util.Date today = new java.util.Date();

        int highestId = dbManager.getHighestId("notifications");


        BusinessModel tempBusiness = new BusinessModel(1,"asdf",today,today,"000","urlimage","testbusiness");
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        BookingModel newBooking = new BookingModel(0, new Time(today.getTime()),new Time(today.getTime()), today, 1,0,newDeal);
        BookingEventModel newEvent = new BookingEventModel(1,1,today,today,new ArrayList<Time>(), new ArrayList<Time>(), "img", false, 0,0, "title", "description");

        testBean.addDealToPackage(newDeal);
        testBean.addEventToPackage(newEvent);
        testBean.addBookingToPackage(newBooking);

        ActionProxy proxy = getActionProxy("/payment.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        BookPackageAction test = (BookPackageAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, testBean.getDealCart().get(0).getDealID());
        //dbManager.delete("shoppingcart", "testFirst");

        dbManager.delete("relationUserNotification", "" + highestId);
        dbManager.delete("notifications", "You have an unconfirmed booking by alex perry.");
    }

    // an exception
    @Test
    public void testExecute3() throws Exception {
        DBManager dbManager = new DBManager();
        java.util.Date today = new java.util.Date();

        int highestId = dbManager.getHighestId("notifications");


        BusinessModel tempBusiness = new BusinessModel(0,"asdf",today,today,"000","urlimage","testbusiness");
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        BookingModel newBooking = new BookingModel(0, new Time(today.getTime()),new Time(today.getTime()), today, 1,0,newDeal);
        BookingEventModel newEvent = new BookingEventModel(1,1,today,today,new ArrayList<Time>(), new ArrayList<Time>(), "img", false, 0,0, "title", "description");

        testBean.addDealToPackage(newDeal);
        testBean.addEventToPackage(newEvent);
        testBean.addBookingToPackage(newBooking);

        ActionProxy proxy = getActionProxy("/payment.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        BookPackageAction test = (BookPackageAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, testBean.getDealCart().get(0).getDealID());
        //dbManager.delete("shoppingcart", "testFirst");

        dbManager.delete("relationUserNotification", "" + highestId);
        dbManager.delete("notifications", "You have an unconfirmed booking by alex perry.");
    }

    @Test
    public void testSetPackageID() throws Exception {
        BookPackageAction bookPackageAction = new BookPackageAction();
        bookPackageAction.setPackageID(5);
        assertEquals(5, bookPackageAction.getPackageID());
    }

    @Test
    public void testGetPackageID() throws Exception {
        BookPackageAction bookPackageAction = new BookPackageAction();
        bookPackageAction.setPackageID(5);
        assertEquals(5, bookPackageAction.getPackageID());
    }

}