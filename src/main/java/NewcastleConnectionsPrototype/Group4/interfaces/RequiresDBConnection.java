package NewcastleConnectionsPrototype.Group4.interfaces;

import org.jooq.DSLContext;

/**
 * Created by simon janmaat on 18/05/2017.
 */
public interface RequiresDBConnection
{
    public void setDSLContext(DSLContext ctx);
}
