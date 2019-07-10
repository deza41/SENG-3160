package NewcastleConnectionsPrototype.Group4.actions.notifications;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.NotificationBean;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon janmaat on 27/10/2017.
 */
public class NotificationsActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean testBean = new PackageBean();
    UserBean user = new UserBean();
    NotificationBean notify = new NotificationBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", testBean);
        sessionMap.put("userBean", user);
        sessionMap.put("notificationBean", notify);
        user.setLoggedIn(true);
        user.setID(1);
    }

    @Test
    public void testExecuteUser() throws Exception {
        user.setRole("user");

        ActionProxy proxy = getActionProxy("/generateUserNotifications.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        NotificationsAction test = (NotificationsAction) proxy.getAction();
        String result = proxy.execute();


        assertEquals(2, test.getModel().size());
    }

    @Test
    public void testExecuteBusinessUser() throws Exception {
        user.setRole("business");

        ActionProxy proxy = getActionProxy("/generateUserNotifications.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        NotificationsAction test = (NotificationsAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(1, test.getModel().size());
    }

    //tests business users navigation to the notifications
    @Test
    public void testNavNotifications1() throws Exception {
        user.setRole("business");

        ActionProxy proxy = getActionProxy("/navToListOfNotifications.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        NotificationsAction test = (NotificationsAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(1, test.getModel().size());
    }

    //test user navigation to the notifications
    @Test
    public void testNavNotifications2() throws Exception {
        user.setRole("user");

        ActionProxy proxy = getActionProxy("/navToListOfNotifications.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        NotificationsAction test = (NotificationsAction) proxy.getAction();
        String result = proxy.execute();


        assertEquals(2, test.getModel().size());
    }

}