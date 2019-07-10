package NewcastleConnectionsPrototype.Group4.actions.event;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.CategoriesRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import com.opensymphony.xwork2.ModelDriven;
import org.jooq.DSLContext;
import org.jooq.Result;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.CATEGORIES;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTS;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class EditEventAction implements RequiresDBConnection, ModelDriven<Result<EventsRecord>> {

    private Result<EventsRecord> model;
    private Result<CategoriesRecord> categoryResult;
    private DSLContext db;
    private int targetEventID;
    private String oldCategory;

    public String execute(){
        //store event matching event id in model for use in jsp
        this.model = db.select()
                .from(EVENTS)
                .where(EVENTS.EVENTID.eq(targetEventID))
                .fetchInto(EVENTS);

        this.categoryResult = db.select()
                .from(CATEGORIES)
                .where(CATEGORIES.CATEGORIESID.eq(model.get(0).getCategoriesid()))
                .fetchInto(CATEGORIES);

        oldCategory = categoryResult.get(0).getCategory();

        return SUCCESS;
    }

    public int getTargetEventID() {
        return targetEventID;
    }

    public void setTargetEventID(int targetEventID) {
        this.targetEventID = targetEventID;
    }

    public String getOldCategory() {
        return oldCategory;
    }

    public void setOldCategory(String oldCategory) {
        this.oldCategory = oldCategory;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Result<EventsRecord> getModel() {
        return model;
    }
}
