package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.orders.ItineraryAction;
import NewcastleConnectionsPrototype.Group4.models.beans.ItineraryItemBean;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Will on 26/09/2017.
 */
public class ItineraryActionTest extends StrutsTestCase {

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {

        ActionProxy proxy = getActionProxy("/itinerary.action");

        ItineraryAction test = (ItineraryAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

    }

    @Test
    public void testGetSetItineraryItems() throws Exception {

        ActionProxy proxy = getActionProxy("/itinerary.action");

        ItineraryAction test = (ItineraryAction) proxy.getAction();

        ArrayList<ItineraryItemBean> testList = new ArrayList<ItineraryItemBean>();

        testList.add(new ItineraryItemBean(0, "day", 0, 0, 0, 0, 0));
        testList.add(new ItineraryItemBean(0, "day", 0, 0, 0, 0, 0));
        testList.add(new ItineraryItemBean(0, "day", 0, 0, 0, 0, 0));

        test.setSearchResults(testList);

        List<ItineraryItemBean> returned = test.getItineraryItems();

        assertEquals(3, returned.size());
    }

    @Test
    public void testSetSearchResultsTest() throws Exception {

        ActionProxy proxy = getActionProxy("/itinerary.action");

        ItineraryAction test = (ItineraryAction) proxy.getAction();

        ArrayList<ItineraryItemBean> itineraryItemBeans = new ArrayList<ItineraryItemBean>();

        test.setSearchResults(itineraryItemBeans);
    }
}