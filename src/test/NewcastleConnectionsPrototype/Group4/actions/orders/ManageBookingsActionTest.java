package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import static org.junit.Assert.*;

/**
 * Created by Will on 26/09/2017.
 */
public class ManageBookingsActionTest extends StrutsTestCase {

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }


    @Test
    public void testExecute() throws Exception {
        ActionProxy proxy = getActionProxy("/declineBooking.action");

        ManageBookingsAction test = (ManageBookingsAction) proxy.getAction();

        test.setBookingID(-500);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);
    }

    @Test
    public void testDecline() throws Exception {

        ActionProxy proxy = getActionProxy("/declineBooking.action");

        ManageBookingsAction test = (ManageBookingsAction) proxy.getAction();

        test.setBookingID(1);

        String result = proxy.execute();

    }

    @Test
    public void testDecline2() throws Exception {

        ActionProxy proxy = getActionProxy("/declineBooking.action");

        ManageBookingsAction test = (ManageBookingsAction) proxy.getAction();

        test.setBookingID(-2);

        String result = proxy.execute();

    }

    @Test
    public void testAccept() throws Exception {

        ActionProxy proxy = getActionProxy("/acceptBooking.action");

        ManageBookingsAction test = (ManageBookingsAction) proxy.getAction();

        test.setBookingID(1);

        String result = proxy.execute();
    }

    @Test
    public void testAccept2() throws Exception {

        ActionProxy proxy = getActionProxy("/acceptBooking.action");

        ManageBookingsAction test = (ManageBookingsAction) proxy.getAction();

        test.setBookingID(12);

        String result = proxy.execute();
    }

    @Test
    public void testAccept3() throws Exception {

        ActionProxy proxy = getActionProxy("/acceptBooking.action");

        ManageBookingsAction test = (ManageBookingsAction) proxy.getAction();

        test.setBookingID(-2);

        String result = proxy.execute();
    }


    @Test
    public void testSetBookingID() throws Exception {
        ActionProxy proxy = getActionProxy("/declineBooking.action");

        ManageBookingsAction test = (ManageBookingsAction) proxy.getAction();

        test.setBookingID(0);

        assertEquals(0,test.getBookingID());
    }

}