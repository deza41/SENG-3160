package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.jooq.DSLContext;
import org.jooq.Result;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;

/**
 * Created by Tyrone on 26/08/2017.
 */

/**
 * Created by Tyrone on 22/08/2017.
 */
public class DisplayBusinessAccountAction extends ActionSupport implements RequiresDBConnection, ModelDriven<Result<BusinessuserRecord>>, CheckLogin {

    private DSLContext db;
    private Result<BusinessuserRecord> model;

    @Override
    public String execute(){

        this.model = db.select()
                .from(BUSINESSUSER)
                .fetchInto(BUSINESSUSER);

        return "success";
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Result<BusinessuserRecord> getModel() {
        return model;
    }

}
