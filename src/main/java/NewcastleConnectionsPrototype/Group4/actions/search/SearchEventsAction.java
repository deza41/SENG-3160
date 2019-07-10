package NewcastleConnectionsPrototype.Group4.actions.search;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsimageRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventstimesRecord;
import NewcastleConnectionsPrototype.Group4.models.view.SearchEventsModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTS;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTSIMAGE;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTSTIMES;

/**
 * Created by simon janmaat on 17/08/2017.
 */
public class SearchEventsAction extends ActionSupport implements RequiresDBConnection, ModelDriven<SearchEventsModel> {

        private DSLContext db;
        SearchEventsModel newSearch = new SearchEventsModel();

        Result<EventsRecord> listOfEvents;
        ArrayList<Result<EventstimesRecord>> listOfListOfeventTimes = new ArrayList<>();

        // this action just fetches all the events that match the category
        public String execute()throws Exception {
            listOfEvents = db.select(EVENTS.fields())
                             .from(EVENTS)
                             .fetchInto(EVENTS);
            Double temp;
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            for(int i = 0; i < listOfEvents.size(); i++)
            {
            temp =  (( listOfEvents.get(i).getOldprice() -listOfEvents.get(i).getPrice() )/ listOfEvents.get(i).getOldprice()) *100;

            tempList.add(temp.intValue());

                        listOfListOfeventTimes.add(db.select(EVENTSTIMES.fields())
                                             .from(EVENTSTIMES)
                                             .where(EVENTSTIMES.EVENTID.eq(listOfEvents.get(i).getEventid()))
                                             .fetchInto(EVENTSTIMES));

                Result<EventsimageRecord> result = db.select(EVENTSIMAGE.fields())
                                                    .from(EVENTSIMAGE)
                                                    .where(EVENTSIMAGE.EVENTID.eq((listOfEvents.get(i).getEventid())))
                                                    .fetchInto(EVENTSIMAGE);
                newSearch.setEventImages(result);

            }

            newSearch.setListOfEvents(listOfEvents);
            newSearch.setEventTimes(listOfListOfeventTimes);



            return "success";
        }

        @Override
        public void setDSLContext(DSLContext ctx) {
            this.db = ctx;
        }

    @Override
    public SearchEventsModel getModel() {
        return newSearch;
    }
}

