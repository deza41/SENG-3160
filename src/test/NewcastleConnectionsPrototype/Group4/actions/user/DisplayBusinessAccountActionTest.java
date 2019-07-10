package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public class DisplayBusinessAccountActionTest extends StrutsTestCase {


    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {
        ActionProxy proxy = getActionProxy("/DisplayBusinessAccount.action");

        String result = proxy.execute();

        assertEquals("Result returned form executing the action was not success but it should have been.", "success", result);
    }

    @Test
    public void testSetDSLContext() throws Exception {
        ActionProxy proxy = getActionProxy("/DisplayBusinessAccount.action");

        DisplayBusinessAccountAction test = (DisplayBusinessAccountAction) proxy.getAction();

        test.setDSLContext(null);

    }

    @Test
    public void testGetModel() throws Exception {

        ActionProxy proxy = getActionProxy("/DisplayBusinessAccount.action");

        proxy.execute();

    }

}