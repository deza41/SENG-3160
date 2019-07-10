package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.DBManager;
import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon janmaat on 22/10/2017.
 */
public class ShoppingCartActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();
    UserBean user = new UserBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
        sessionMap.put("userBean", user);
    }

    //testing the setters in packagebean
    //testing the total cost
    //with only 1 item in the package
    @Test
    public void testExecute() throws Exception {
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);

        testBean.addDealToPackage(newDeal);


        ActionProxy proxy = getActionProxy("/shoppingCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(String.format("%5.2f", 10.00), testBean.getTotalCost());
        assertEquals(String.format("%5.2f", 15.00), testBean.getFullCost());
        assertEquals(String.format("%5.2f", 5.00), testBean.getDiscountAmount());

    }
    //testing the setters in packagebean
    //testing the total cost
    //with 2 same items in the package
    @Test
    public void testExecute2() throws Exception {
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        newDeal.setQuantity(1);
        testBean.addDealToPackage(newDeal);


        ActionProxy proxy = getActionProxy("/shoppingCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(String.format("%5.2f", 20.00), testBean.getTotalCost());
        assertEquals(String.format("%5.2f", 30.00), testBean.getFullCost());
        assertEquals(String.format("%5.2f", 10.00), testBean.getDiscountAmount());
    }

    //testing the setters in packagebean
    //testing the total cost
    //with 2 different items in the package
    @Test
    public void testExecute3() throws Exception {
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        newDeal.setQuantity(1);
        BookingDealModel newDeal2 = new BookingDealModel(0, tempBusiness, "testing", "dealImage",  20.00, 40.00, "dealDescription", 1, 1);

        testBean.addDealToPackage(newDeal);
        testBean.addDealToPackage(newDeal2);


        ActionProxy proxy = getActionProxy("/shoppingCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(String.format("%5.2f", 40.00), testBean.getTotalCost());
        assertEquals(String.format("%5.2f", 70.00), testBean.getFullCost());
        assertEquals(String.format("%5.2f", 30.00), testBean.getDiscountAmount());
    }

    //testing the setters in packagebean
    //testing the total cost
    //with 2 different items in the package
    //with 1 event as well.
    @Test
    public void testExecute4() throws Exception {
        BusinessModel tempBusiness = new BusinessModel();
        BookingDealModel newDeal = new BookingDealModel(1, tempBusiness, "testing", "dealImage",  10.00, 15.00, "dealDescription", 1, 1);
        newDeal.setQuantity(1);
        BookingDealModel newDeal2 = new BookingDealModel(0, tempBusiness, "testing", "dealImage",  20.00, 40.00, "dealDescription", 1, 1);
        BookingEventModel newEvent = new BookingEventModel(1, 2, null, null, null, null, "testing", false, 10.00, 5.00, "tittle", "description");

        testBean.addDealToPackage(newDeal);
        testBean.addDealToPackage(newDeal2);
        testBean.addEventToPackage(newEvent);

        ActionProxy proxy = getActionProxy("/shoppingCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals(String.format("%5.2f", 45.00), testBean.getTotalCost());
        assertEquals(String.format("%5.2f", 80.00), testBean.getFullCost());
        assertEquals(String.format("%5.2f", 35.00), testBean.getDiscountAmount());
    }


    //checking logged in test.
    //if user is logged in mapping to isloggedin
    @Test
    public void testCheckOutNav1() throws Exception {
        user.setLoggedIn(true);
        user.setFirstName("Tom");

        ActionProxy proxy = getActionProxy("/checkOut.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("loggedin", result);

    }

    //test user has inputed data.
    //and is valid
    @Test
    public void testCheckOutNav2() throws Exception {
        DBManager manager = new DBManager();
        user.setLoggedIn(false);

        request.setParameter("firstName", "testFirst");
        request.setParameter("lastName", "testLast");
        request.setParameter("phoneNumber", "testPhone");
        request.setParameter("email", "testEmail");
        request.setParameter("postCode", "testPostCode");
        request.setParameter("dateOfBirth", "testDOB");

        ActionProxy proxy = getActionProxy("/checkOut.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();



        manager.delete("shoppingcart", "testFirst");
        assertEquals("guestformvalid", result);

    }

    //tests error in user input fields.
    @Test
    public void testCheckOutNav3() throws Exception {
        DBManager manager = new DBManager();
        user.setLoggedIn(false);

        request.setParameter("lastName", "testLast");
        request.setParameter("phoneNumber", "testPhone");
        request.setParameter("email", "testEmail");
        request.setParameter("postCode", "testPostCode");
        request.setParameter("dateOfBirth", "testDOB");

        ActionProxy proxy = getActionProxy("/checkOut.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("success", result);

    }
    @Test
    public void testSettersAndGetters() throws Exception{
        request.setParameter("firstName", "testFirst");
        request.setParameter("lastName", "testLast");
        request.setParameter("phoneNumber", "testPhone");
        request.setParameter("email", "testEmail");
        request.setParameter("postCode", "testPostCode");
        request.setParameter("dateOfBirth", "testDOB");

        ActionProxy proxy = getActionProxy("/shoppingCart.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        ShoppingCartAction test = (ShoppingCartAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("testFirst",test.getFirstName());
        assertEquals("testLast", test.getLastName());
        assertEquals("testPhone", test.getPhoneNumber());
        assertEquals("testEmail", test.getEmail());
        assertEquals("testPostCode", test.getPostCode());
        assertEquals("testDOB", test.getDateOfBirth());
    }


}