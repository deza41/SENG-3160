package NewcastleConnectionsPrototype.Group4.actions.search;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BuildPackageActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    PackageBean packageBean = new PackageBean();

    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("PackageBean", packageBean);
    }

    @Test
    public void testExecute() throws Exception {
        request.setParameter("numberOfAdults", "1");
        request.setParameter("numberOfChildren", "1");
        request.setParameter("startDate", "19-10-2017");
        request.setParameter("endDate", "19-10-2017");

        ActionProxy proxy = getActionProxy("/buildPackage.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        BuildPackageAction action = (BuildPackageAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);

        action.getStartDate();
        action.getEndDate();
        action.getNumberOfAdults();
        action.getNumberOfChildren();

    }

    @Test
    public void testExecute2() throws Exception {
        request.setParameter("numberOfAdults", "1");
        request.setParameter("numberOfChildren", "1");

        ActionProxy proxy = getActionProxy("/buildPackage.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        BuildPackageAction action = (BuildPackageAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);

        action.getStartDate();
        action.getEndDate();
        action.getNumberOfAdults();
        action.getNumberOfChildren();

    }

}