package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsimageRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventstimesRecord;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

/**
 * Created by simon janmaat on 15/08/2017.
 */
public class AddEventToCartAction extends ActionSupport implements RequiresDBConnection, SessionAware {

    private DSLContext db;
    private Result<EventsRecord> addNewEvent;
    private Result<EventstimesRecord> eventTimes;
    private Result<EventsimageRecord> eventImages;
    private int eventID;
    private Map<String, Object> session, request;





    public String execute(){
        BookingEventModel newEvent;

        boolean added=false;
        PackageBean newPackage = (PackageBean) session.get("PackageBean");

        //checks to see if the event has already been placed inside the cart.
        for(int i =0; i<newPackage.getEventsCart().size(); i++) {
            //System.out.println("more quantity");
            if (newPackage.getEventsCart().get(i).getEventID() == eventID) {
                newPackage.getEventsCart().get(i).setQuantity(1);
                added = true;
                break;
            }
        }
        //if it wasn't found in the cart above. it gathers the data from db.
        if(!added){
            try {
                //gets all the information on an event.
                addNewEvent = db.select(EVENTS.fields())
                        .from(EVENTS)
                        .where(EVENTS.EVENTID.eq(this.eventID))
                        .fetchInto(EVENTS);

                //events image
                eventImages = db.select(EVENTSIMAGE.fields())
                        .from(EVENTSIMAGE)
                        .where(EVENTSIMAGE.EVENTID.eq(this.eventID))
                        .fetchInto(EVENTSIMAGE);

                //gets individual day times.
                eventTimes = db.select(EVENTSTIMES.fields())
                        .from(EVENTSTIMES)
                        .where(EVENTSTIMES.EVENTID.eq(this.eventID))
                        .fetchInto(EVENTSTIMES);


                ArrayList<Time> startTime = new ArrayList<Time>();
                ArrayList<Time> endTime = new ArrayList<Time>();

                //gets all the results into the 2 variables.
                for (int i = 0; i < eventTimes.size(); i++) {
                    startTime.add(eventTimes.get(i).getStarttime());
                    endTime.add(eventTimes.get(i).getEndtime());
                }


                //add the event to the booking event model with multiple start/end times.
                newEvent = new BookingEventModel(addNewEvent.get(0).getBusinessid(), addNewEvent.get(0).getEventid(), addNewEvent.get(0).getStartdate(), addNewEvent.get(0).getEnddate(), startTime, endTime, eventImages.get(0).getEventimageurl(), true, addNewEvent.get(0).getOldprice(), addNewEvent.get(0).getPrice(), addNewEvent.get(0).getTitle(), addNewEvent.get(0).getEventdescription());

                newPackage.addEventToPackage(newEvent);


            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return SUCCESS; }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        db = ctx;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}