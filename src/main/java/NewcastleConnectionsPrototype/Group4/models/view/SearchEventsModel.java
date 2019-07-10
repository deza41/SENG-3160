package NewcastleConnectionsPrototype.Group4.models.view;

import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsimageRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventstimesRecord;
import org.jooq.Result;

import java.util.ArrayList;

/**
 * Created by simon janmaat on 17/08/2017.
 */
public class SearchEventsModel {

    private Result<EventsRecord> listOfEvents;
    private ArrayList<Result<EventstimesRecord>> eventTimes = new ArrayList<>();
    private Result<EventsimageRecord> eventImages;
    private ArrayList<Integer> percentage = new ArrayList<Integer>();

    //getters
    public Result<EventsRecord> getListOfEvents() {
        return listOfEvents;
    }

    public ArrayList<Result<EventstimesRecord>> getEventTimes() {
        return eventTimes;
    }

    public Result<EventsimageRecord> getEventImages() {
        return eventImages;
    }

    public ArrayList<Integer> getPercentage() {
        return percentage;
    }

    //setters
    public void setListOfEvents(Result<EventsRecord> newSearch) {
        this.listOfEvents = newSearch;
    }

    public void setEventTimes(ArrayList<Result<EventstimesRecord>> eventTimes) {
        this.eventTimes = eventTimes;
    }

    public void setEventImages(Result<EventsimageRecord> eventImages) {
        this.eventImages = eventImages;
    }

    public void setPercentage(ArrayList<Integer> percentage) {
        this.percentage = percentage;
    }
}
