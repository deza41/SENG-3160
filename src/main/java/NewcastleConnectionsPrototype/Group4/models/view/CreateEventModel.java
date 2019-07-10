package NewcastleConnectionsPrototype.Group4.models.view;

import java.util.ArrayList;
import java.util.Date;

public class CreateEventModel{
    //get the event ID to store into the db with.

    ArrayList<EventTimes> eventTimes;
    Date startDate, endDate, dayDate;
    String title, category, eventDescription;
    double price, oldPrice;


    //getters
    public ArrayList<EventTimes> getEventTimes() {
        return eventTimes;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /*
    public Date getDayDate() {
        return dayDate;
    }
    */

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public double getPrice() {
        return price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    //setters
    public void setEventTimes (ArrayList <EventTimes> eventTimes)
    {
            this.eventTimes = eventTimes;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /*
    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }
    */

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setPrice(double price) {
            this.price = price;
        }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }
}
