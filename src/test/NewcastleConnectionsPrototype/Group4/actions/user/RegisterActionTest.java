package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.DBManager;
import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.CreateUserModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RegisterActionTest extends StrutsTestCase {

    private Map<String, Object> sessionMap = new HashMap<>();
    private UserBean testBean = new UserBean();
    private DBManager dbm = new DBManager();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testExecute() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        RegisterAction test = (RegisterAction) proxy.getAction();

        String result = proxy.execute();

        CreateUserModel userModel = test.getModel();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);
        assertEquals(username, userModel.getUsername());
        assertEquals(firstName, userModel.getFirstName());
        assertEquals(lastName, userModel.getLastName());
        assertEquals(phoneNumber, userModel.getPhoneNumber());
        assertEquals(email, userModel.getEmail());
        assertEquals(password, userModel.getPassword());
        assertEquals(postCode, userModel.getPostCode());

        userModel.getDateOfBirth();

        dbm.delete("user", username);

    }

    //test execute including profile picture
    @Test
    public void testExecute2() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19",
                myFileContentType = "Test myFileContentType",
                profileImageFileFileName = "Test profileImageFileFileName";
        File    profileImageFile;

        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        RegisterAction test = (RegisterAction) proxy.getAction();

        test.setMyFileContentType(myFileContentType);
        test.setProfileImageFileFileName(profileImageFileFileName);

        //need to set location for test image file
        //test.setProfileImageFile(profileImageFile);

        //commented out until dummy model can be set in RegisterAction
        String result = proxy.execute();

        CreateUserModel userModel = test.getModel();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);
        assertEquals(username, userModel.getUsername());
        assertEquals(firstName, userModel.getFirstName());
        assertEquals(lastName, userModel.getLastName());
        assertEquals(phoneNumber, userModel.getPhoneNumber());
        assertEquals(email, userModel.getEmail());
        assertEquals(password, userModel.getPassword());
        assertEquals(postCode, userModel.getPostCode());

        //assertEquals(profileImageFile, test.getProfileImageFile());
        assertEquals(myFileContentType, test.getMyFileContentType());
        //assertEquals(profileImageFileFileName, test.getProfileImageFileFileName()); //profileImageFile needs to be set, else action sets to "default Pic"

        //not sure how to have getDateOfBirth return regular format
        //assertEquals(dateOfBirth, userModel.getDateOfBirth());

        dbm.delete("user", username);

    }

    //test to target email addresses that do not follow regular format
    @Test
    public void testExecute3() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";

        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was successful but it should not have been.\n Invalid email address was not detected", "error", result);

        dbm.delete("user", username);

    }

    //testing server-side validation of required user variables

    //testing for null username
    @Test
    public void testExecute4() throws Exception {
        String  username = "",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to empty username.", "error", result);

        dbm.delete("user", username);

    }

    //testing for null firstName
    @Test
    public void testExecute5() throws Exception {
        String  username = "Test username",
                firstName = "",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to empty firstName.", "error", result);

        dbm.delete("user", username);

    }

    //testing for null lastName
    @Test
    public void testExecute6() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to empty lastName.", "error", result);

        dbm.delete("user", username);
    }

    //testing for null phoneNumber
    @Test
    public void testExecute7() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to empty phoneNumber.", "error", result);

        dbm.delete("user", username);
    }

    //testing for null password
    @Test
    public void testExecute8() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to empty password.", "error", result);

        dbm.delete("user", username);
    }

    //testing for null dateOfBirth
    @Test
    public void testExecute9() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to empty dateOfBirth.", "error", result);

        dbm.delete("user", username);
    }

    //taken username
    @Test
    public void testExecute10() throws Exception {
        String  username = "admin",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to taken username.", "error", result);

    }

    //taken username
    @Test
    public void testExecute11() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "newcastleConnect@gmail.com",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";


        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was success but it should not have been. Expected error due to taken email.", "error", result);

    }

    //test to target email addresses that do not follow regular format
    @Test
    public void testExecute12() throws Exception {
        String  username = "Test username",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "TestEmail",
                password = "Test password",
                postCode = "Test postCode",
                dateOfBirth = "2017-10-19";

        request.setParameter("username", username);
        request.setParameter("firstName", firstName);
        request.setParameter("lastName", lastName);
        request.setParameter("phoneNumber", phoneNumber);
        request.setParameter("email", email);
        request.setParameter("password", password);
        request.setParameter("postCode", postCode);
        request.setParameter("dateOfBirth", dateOfBirth);

        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was successful but it should not have been. Invalid email address was not detected", "error", result);

        dbm.delete("user", username);

    }

    @Test
    public void testGettersSetters() throws Exception{
        ActionProxy proxy = getActionProxy("/register.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        RegisterAction test = (RegisterAction) proxy.getAction();

        test.setProfileImageFile(null);
        test.getProfileImageFile();
        test.getProfileImageFileFileName();
    }

    @Test
    public void testDisplay() throws Exception{
        ActionProxy proxy = getActionProxy("/register.action");

        RegisterAction test = (RegisterAction) proxy.getAction();

        test.display();
    }

    @Test
    public void testSetDSLContext() throws Exception {
        ActionProxy proxy = getActionProxy("/register.action");

        RegisterAction test = (RegisterAction) proxy.getAction();

        test.setDSLContext(null);

    }

    @Test
    public void testSetSession() throws Exception {
        ActionProxy proxy = getActionProxy("/register.action");

        RegisterAction test = (RegisterAction) proxy.getAction();

        test.setSession(null);
    }

}