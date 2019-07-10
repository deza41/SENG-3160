package NewcastleConnectionsPrototype.Group4.actions.review;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AddReviewActionTest extends StrutsTestCase{
    private Map<String, Object> sessionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {
        String     userID = "1",
                businessID = "1",
                userRating = "3",
                reviewContent = "Test reviewContent",
                reviewTitle = "Test reviewTitle",
                title = "Test title";

        request.setParameter("userID", userID);
        request.setParameter("businessID", businessID);
        request.setParameter("userRating", userRating);
        request.setParameter("reviewContent", reviewContent);
        request.setParameter("reviewTitle", reviewTitle);
        request.setParameter("title", title);

        ActionProxy proxy = getActionProxy("/addReview.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);

        AddReviewAction test = (AddReviewAction) proxy.getAction();

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);
    }

}