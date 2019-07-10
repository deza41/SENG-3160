package NewcastleConnectionsPrototype.Group4.actions.review;

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

public class ViewReviewsActionTest extends StrutsTestCase {
    private Map<String, Object> sessionMap = new HashMap<>();

    private UserBean testBean = new UserBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void testExecute() throws Exception {
        testBean.setID(1);

        ActionProxy proxy = getActionProxy("/manageReview.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        ViewReviewsAction test = (ViewReviewsAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);

        test.setReviews(null);
        test.setListOfUsers(null);

        test.getReviews();
        test.getListOfUsers();
    }

}