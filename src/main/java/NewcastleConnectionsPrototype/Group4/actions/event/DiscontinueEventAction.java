package NewcastleConnectionsPrototype.Group4.actions.event;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import org.jooq.DSLContext;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTS;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class DiscontinueEventAction implements RequiresDBConnection {

    private DSLContext db;
    private int targetEventID;

    public String execute(){
        //make the event matching targetEventID now discontinued
        db.update(EVENTS).set(EVENTS.ACTIVEEVENT, (byte)0)
                .where(EVENTS.EVENTID.eq(targetEventID))
                .execute();

        return SUCCESS;
    }

    public int getTargetEventID() {
        return targetEventID;
    }

    public void setTargetEventID(int targetEventID) {
        this.targetEventID = targetEventID;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

}
