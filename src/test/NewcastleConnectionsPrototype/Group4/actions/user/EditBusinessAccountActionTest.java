package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.DBManager;
import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

public class EditBusinessAccountActionTest extends StrutsTestCase{

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();

    DBManager dbm = new DBManager();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        testBean.setUsername("Test userName");
        testBean.setFirstName("Test firstName");
        testBean.setLastName("Test lastName");
        testBean.setEmail("Test@email");
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testExecute() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was not success but it should have been.", "success", result);

        dbm.deleteTestBusinessUser();
    }

    //test targeting email address validation
    @Test
    public void testExecute2() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Invalid email address was not detected", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //tests targeting server-side input validation

    //testing validation of null firstName
    @Test
    public void testExecute3() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null firstName should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null lastName
    @Test
    public void testExecute4() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null lastName should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null phoneNumber
    @Test
    public void testExecute5() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null phoneNumber should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null email
    @Test
    public void testExecute6() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null email should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null businessName
    @Test
    public void testExecute7() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null businessName should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null street
    @Test
    public void testExecute8() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null street should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null suburb
    @Test
    public void testExecute9() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null suburb should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null businessDescription
    @Test
    public void testExecute10() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null businessDescription should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null categoryName
    @Test
    public void testExecute11() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null categoryName should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null number
    @Test
    public void testExecute12() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null number should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null businessOpen
    @Test
    public void testExecute13() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null businessOpen should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //testing validation of null businessClose
    @Test
    public void testExecute14() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null businessClose should have been detected.", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //test targeting taken email address validation
    @Test
    public void testExecute15() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "newcastleConnect@gmail.com",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Taken email address was not detected", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //test targeting null email address validation
    @Test
    public void testExecute16() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Null email address was not detected", "error", result);

        dbm.deleteTestBusinessUser();
    }

    //test targeting unmatched categoryName address validation
    @Test
    public void testExecute17() throws Exception {
        dbm.deleteTestBusinessUser();
        dbm.createTestBusinessUser();

        String  userName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                password = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "invalid",
                number = "1",
                businessOpen = "2017-10-19",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        request.setParameter("userName", userName);
        request.setParameter("firstName",firstName);
        request.setParameter("lastName",lastName);
        request.setParameter("phoneNumber",phoneNumber);
        request.setParameter("email",email);
        request.setParameter("postCode",postCode);
        request.setParameter("password",password);
        request.setParameter("profileImageURL",profileImageURL);
        request.setParameter("businessName",businessName);
        request.setParameter("street",street);
        request.setParameter("unit",unit);
        request.setParameter("suburb",suburb);
        request.setParameter("businessDescription",businessDescription);
        request.setParameter("latitude",latitude);
        request.setParameter("longitude",longitude);
        request.setParameter("categoryName",categoryName);
        request.setParameter("number",number);
        request.setParameter("rating",Integer.toString(rating));
        request.setParameter("businessViews", Integer.toString(businessViews));
        request.setParameter("businessOpen", businessOpen);
        request.setParameter("businessClose", businessClose);

        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned from executing the action was success but it should not have been. Unmatched categoryName was not detected", "error", result);

        dbm.deleteTestBusinessUser();
    }

    @Test
    public void testGettersSetters() throws Exception{
        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        EditBusinessAccountAction test = (EditBusinessAccountAction) proxy.getAction();

        test.setUserImageContentType(null);
        test.setUserImageFileName(null);
        test.setUserImage(null);
        test.setFilesContentType(null);
        test.setFilesFileName(null);
        test.setFiles(null);

        test.getUserImageContentType();
        test.getUserImageFileName();
        test.getUserImage();
        test.getFilesContentType();
        test.getFilesFileName();
        test.getFiles();

        test.display();

    }

    @Test
    public void testSetDSLContext() throws Exception {
        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");

        EditBusinessAccountAction test = (EditBusinessAccountAction) proxy.getAction();

        test.setDSLContext(null);

    }

    @Test
    public void testSetSession() throws Exception {
        ActionProxy proxy = getActionProxy("/editBusinessAccount.action");

        EditBusinessAccountAction test = (EditBusinessAccountAction) proxy.getAction();

        test.setSession(null);
    }

}