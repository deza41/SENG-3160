package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.cart.AddEventToCartAction;
import NewcastleConnectionsPrototype.Group4.models.beans.ItineraryItemBean;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Will on 26/09/2017.
 */
public class ItinerarySaveActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
    }

    @Test
    public void testSetItineraryItems() throws Exception {
        
        ActionProxy proxy = getActionProxy("/itinerarySave.action");
        ItinerarySaveAction test = (ItinerarySaveAction) proxy.getAction();

        List<ItineraryItemBean> itineraryItems = new ArrayList<ItineraryItemBean>();

        itineraryItems.add(new ItineraryItemBean());
        itineraryItems.add(new ItineraryItemBean());

        test.setItineraryItems(itineraryItems);


        assertEquals(2, test.getItineraryItems().size());
    }

    @Test
    public void testExecute() throws Exception {
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(2, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);

        testBean.addDealToPackage(newDeal);

        List<ItineraryItemBean> itineraryItems = new ArrayList<ItineraryItemBean>();

        ActionProxy proxy = getActionProxy("/itinerarySave.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ItinerarySaveAction test = (ItinerarySaveAction) proxy.getAction();
        test.setItineraryItems(itineraryItems);
        String result = proxy.execute();



    }

}