package NewcastleConnectionsPrototype.Group4.actions.event;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import org.apache.struts2.ServletActionContext;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Businessuser.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Events.EVENTS;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class ViewEventInformationAction implements RequiresDBConnection {

    private Result<EventsRecord> eventsRecordResult;
    private Result<BusinessuserRecord> businessuserRecordResult;
    private DSLContext db;
    private int eventID;
    private double savings;
    private double savingsPercentage;

    public String execute(){

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        // create insert query to save event
        this.eventsRecordResult = db.select()
                .from(EVENTS)
                .where(EVENTS.EVENTID.eq(eventID))
                .fetchInto(EVENTS);

        this.businessuserRecordResult = db.select()
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.BUSINESSID.eq(eventsRecordResult.get(0).getBusinessid()))
                .fetchInto(BUSINESSUSER);

        db.update(EVENTS).set(EVENTS.TOTALVIEWS, EVENTS.TOTALVIEWS.add(1))
                .where(EVENTS.EVENTID.eq(eventsRecordResult.get(0).getEventid()))
                .execute();

        savings = eventsRecordResult.get(0).getOldprice() - eventsRecordResult.get(0).getPrice();
        savingsPercentage = 100* savings/eventsRecordResult.get(0).getOldprice();

        return SUCCESS;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    public Result<EventsRecord> getEventsRecordResult() {
        return eventsRecordResult;
    }

    public Result<BusinessuserRecord> getBusinessuserRecordResult() {
        return businessuserRecordResult;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getSavingsPercentage() {
        return savingsPercentage;
    }

    public void setSavingsPercentage(double savingsPercentage) {
        this.savingsPercentage = savingsPercentage;
    }
}
