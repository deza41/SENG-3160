package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import org.jooq.DSLContext;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class DiscontinueDealAction implements RequiresDBConnection {

    private DSLContext db;
    private int targetDealID;

    public String execute(){
        //make the deal matching targetDealID now discontinued
        db.update(DEALS).set(DEALS.ACTIVEDEAL, (byte)0)
                .where(DEALS.DEALID.eq(targetDealID))
                .execute();

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

}
