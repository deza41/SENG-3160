package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
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
public class AddDealToCartActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
    }

    //tests if the setter for deal id is working properly.
    @Test
    public void testGetDealID() throws Exception {
        request.setParameter("dealID", "1");
        ActionProxy proxy = getActionProxy("/addDealToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddDealToCartAction test = (AddDealToCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(1, test.getDealID());
    }


    //tests if only 1 deal has been added, 1 quantity, and the correct deal ID.
    @Test
    public void testExecute() throws Exception {
        request.setParameter("dealID", "1");
        ActionProxy proxy = getActionProxy("/addDealToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddDealToCartAction test = (AddDealToCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, testBean.getDealCart().get(0).getDealID());
        assertEquals(1, testBean.getDealCart().size());
        assertEquals(1, testBean.getDealCart().get(0).getQuantity());

    }

    //testing if adding in the same deal increases the quantity not the size of list.
    @Test
    public void testExecute2() throws Exception{

        request.setParameter("dealID", "1");
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);

        testBean.addDealToPackage(newDeal);
        ActionProxy proxy = getActionProxy("/addDealToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddDealToCartAction test = (AddDealToCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, testBean.getDealCart().get(0).getDealID());
        assertEquals(1, testBean.getDealCart().size());
        assertEquals(2, testBean.getDealCart().get(0).getQuantity());

    }

    //test increase of deal cart size not quantity.
    @Test
    public void testExecute3() throws Exception{
        request.setParameter("dealID", "1");
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(2, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        testBean.addDealToPackage(newDeal);
        ActionProxy proxy = getActionProxy("/addDealToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddDealToCartAction test = (AddDealToCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(1, testBean.getDealCart().get(1).getDealID());
        assertEquals(2, testBean.getDealCart().size());
        assertEquals(1, testBean.getDealCart().get(0).getQuantity());
        assertEquals(1, testBean.getDealCart().get(1).getQuantity());
    }

    //testing if an id that doesnt exist in the database returns an error.
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExecuteAddInEvent() throws Exception {
        request.setParameter("dealID", "20");

        ActionProxy proxy = getActionProxy("/addDealToCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AddDealToCartAction test = (AddDealToCartAction) proxy.getAction();

        String result = proxy.execute();

    }
}