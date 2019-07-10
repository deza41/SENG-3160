package NewcastleConnectionsPrototype.Group4.actions.search;

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

/**
 * Created by simon janmaat on 28/10/2017.
 */
public class LikeBusinessActionTest extends StrutsTestCase{

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean user = new UserBean();
    DBManager manager = new DBManager();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", user);
        user.setID(1);
    }


    //tests the initial like.
    @Test
    public void testExecute() throws Exception {

        request.setParameter("liked", "1");
        request.setParameter("businessID", "2");

        ActionProxy proxy = getActionProxy("/likeBusiness.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LikeBusinessAction test = (LikeBusinessAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Error", "success", result);
        assertEquals(2, test.getBusinessID());
        assertEquals(1, test.getLiked());
    }

    //tests an unlike.
    @Test
    public void testExecute2() throws Exception {

        request.setParameter("liked", "1");
        request.setParameter("businessID", "2");

        ActionProxy proxy = getActionProxy("/likeBusiness.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LikeBusinessAction test = (LikeBusinessAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Error", "success", result);
        assertEquals(2, test.getBusinessID());
        assertEquals(1, test.getLiked());

        //hard coded the delete.
        manager.delete("liked", "N/A");
    }

}