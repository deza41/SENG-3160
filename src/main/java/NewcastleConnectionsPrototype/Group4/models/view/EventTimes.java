package NewcastleConnectionsPrototype.Group4.models.view;

import java.sql.Time;
import java.util.Date;

/**
 * Created by simon janmaat on 7/08/2017.
 */
public class EventTimes {

    Time startTime, endTime;
    Date dayDate;

    public EventTimes(Time startTime, Time endTime, Date dayDate) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayDate = dayDate;
    }

    //getters
    public Date getDayDate() {
        return dayDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    //setters
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }
}
