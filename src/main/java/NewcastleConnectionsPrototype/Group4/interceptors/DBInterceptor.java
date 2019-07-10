package NewcastleConnectionsPrototype.Group4.interceptors;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by simon janmaat on 18/05/2017.
 */
public class DBInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // Get a reference to the executing action
        Object action = invocation.getAction();

        if(action instanceof RequiresDBConnection)
        {
            Connection conn = null;
            try
            {
                RequiresDBConnection actn = (RequiresDBConnection) action;

                Context ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/db");

                conn = ds.getConnection();
                DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

                actn.setDSLContext(db);
                String result = invocation.invoke();

                return result;
            }
            catch (Exception e)
            {
                throw e;
            }
            finally {
                if (conn != null)
                {
                    conn.close();
                }
            }
        }
        return invocation.invoke();
    }
}
