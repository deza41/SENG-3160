package NewcastleConnectionsPrototype.Group4.interceptors;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.actions.RequiresDbConnectionMockAction;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import static org.junit.Assert.assertTrue;


/**
 * Created by simon janmaat on 27/09/2017.
 */
public class DBInterceptorTest extends StrutsJUnit4TestCase<RequiresDbConnectionMockAction> {

    @Override
    protected String getConfigPath() {
        return "struts-test.xml";
    }

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testIntercept() throws Exception {
        ActionProxy proxy = getActionProxy("/testDbConn.action");
        RequiresDbConnectionMockAction test = (RequiresDbConnectionMockAction) proxy.getAction();
        String result = proxy.execute();

        assertTrue("Error", test.getDSL() != null);
    }
}