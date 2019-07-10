package NewcastleConnectionsPrototype.Group4.actions;

        import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
        import com.opensymphony.xwork2.ActionSupport;
        import org.jooq.DSLContext;

/**
 * Created by simon janmaat on 27/09/2017.
 */
public class RequiresDbConnectionMockAction extends ActionSupport implements RequiresDBConnection{
    private DSLContext db;

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    public DSLContext getDSL(){
        return this.db;
    }
}
