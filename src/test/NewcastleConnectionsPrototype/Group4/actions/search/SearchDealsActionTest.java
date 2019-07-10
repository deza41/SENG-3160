package NewcastleConnectionsPrototype.Group4.actions.search;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SearchDealsActionTest extends StrutsTestCase{

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean user = new UserBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", user);
    }

    @Test
    public void testExecute() throws Exception {
        request.setParameter("category", "food");

        ActionProxy proxy = getActionProxy("/search.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        SearchDealsAction action = (SearchDealsAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);

        action.getPageHeading();
        action.getCategory();

        action.setCategory("experience");
        action.getPageHeading();

        action.setCategory("accommodation");
        action.getPageHeading();

        action.setCategory("");
        action.getPageHeading();
    }

}