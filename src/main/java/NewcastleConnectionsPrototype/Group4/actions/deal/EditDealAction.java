package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import com.opensymphony.xwork2.ModelDriven;
import org.jooq.DSLContext;
import org.jooq.Result;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class EditDealAction implements RequiresDBConnection, ModelDriven<Result<DealsRecord>> {

    private Result<DealsRecord> model;
    private DSLContext db;
    private int targetDealID;

    public String execute(){

        //store deal matching deal id in model for use in jsp
        this.model = db.select()
                .from(DEALS)
                .where(DEALS.DEALID.eq(targetDealID))
                .fetchInto(DEALS);

        return SUCCESS;
    }

    public int getTargetDealID() {
        return targetDealID;
    }

    public void setTargetDealID(int targetDealID) {
        this.targetDealID = targetDealID;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Result<DealsRecord> getModel() {
        return model;
    }

}
