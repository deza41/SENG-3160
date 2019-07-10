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
 * Created by simon janmaat on 19/10/2017.
 */
public class RemoveDealFromCartActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
    }


    @Test
    public void testParameters() throws Exception {
        request.setParameter("dealID", "0");
        request.setParameter("removeQ", "1");

        ActionProxy proxy = getActionProxy("/removeDealFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveDealFromCartAction test = (RemoveDealFromCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(0, test.getDealID());
        assertEquals(1, test.getRemoveQ());

    }

    @Test
    public void testExecute() throws Exception {
        request.setParameter("dealID", "0");
        request.setParameter("removeQ", "1");

        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(0, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        BookingDealModel newDeal2 = new BookingDealModel(0, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);

        testBean.addDealToPackage(newDeal);
        testBean.addDealToPackage(newDeal2);

        ActionProxy proxy = getActionProxy("/removeDealFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveDealFromCartAction test = (RemoveDealFromCartAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(0, test.getDealID());
        assertEquals(1, test.getRemoveQ());
        assertEquals(1,testBean.getDealCart().size());
    }

    @Test
    public void testExecute2() throws Exception {
        request.setParameter("dealID", "0");
        request.setParameter("removeQ", "2");

        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(0, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        testBean.addDealToPackage(newDeal);

        testBean.getDealCart().get(0).setQuantity(1);

        ActionProxy proxy = getActionProxy("/removeDealFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveDealFromCartAction test = (RemoveDealFromCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(0, test.getDealID());
        assertEquals(2, test.getRemoveQ());
        assertEquals(0, testBean.getDealCart().size());
    }

    //removes 2 of the same deal with multiple different deals in the cart
    @Test
    public void testExecute3() throws Exception {
        request.setParameter("dealID", "0");
        request.setParameter("removeQ", "2");

        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(0, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        BookingDealModel newDeal2 = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        testBean.addDealToPackage(newDeal);

        testBean.addDealToPackage(newDeal2);

        testBean.getDealCart().get(0).setQuantity(1);

        ActionProxy proxy = getActionProxy("/removeDealFromCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RemoveDealFromCartAction test = (RemoveDealFromCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(0, test.getDealID());
        assertEquals(2, test.getRemoveQ());
        assertEquals(1, testBean.getDealCart().size());
        assertEquals(1, testBean.getDealCart().get(0).getDealID());
    }

}