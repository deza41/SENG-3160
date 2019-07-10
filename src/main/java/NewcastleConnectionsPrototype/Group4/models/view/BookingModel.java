package NewcastleConnectionsPrototype.Group4.models.view;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by simon janmaat on 8/08/2017.
 */
public class BookingModel {
    private Time startTime, endTime;
    private Date daysDate;
    private int numberOfAdults, numberOfChildren;
    private BookingDealModel deal;
    private int localBookingID;

    public int getLocalBookingID() {
        return localBookingID;
    }

    public void setLocalBookingID(int localBookingID) {
        this.localBookingID = localBookingID;
    }


    public BookingModel(int localBookingID, Time startTime, Time endTime, Date daysDate, int numberOfAdults, int numberOfChildren, BookingDealModel deal) {
        this.localBookingID = localBookingID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.daysDate = daysDate;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.deal = deal;
    }

    //getters

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Date getDaysDate() {
        return daysDate;
    }

    public String getFormattedItineraryItemId() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int intervals = cal.get(Calendar.MINUTE) / 15;

        // date-hour-interval
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String returnString = sdf.format(daysDate);
        returnString += "-" + hours + "-" + intervals;
        return returnString;

    }

    public int getItineraryIntervalAmount(){
        int INTERVAL_LENGTH = 15;
        int intervalAmount = 0;

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int hourStart = cal.get(Calendar.HOUR_OF_DAY);
        int minuteStart = cal.get(Calendar.MINUTE);

        cal.setTime(endTime);
        int hourEnd = cal.get(Calendar.HOUR_OF_DAY);
        int minuteEnd = cal.get(Calendar.MINUTE);

        int hours = hourEnd - hourStart;
        int minutes = minuteEnd - minuteStart;

        intervalAmount =  ((hours * 60) + minutes) / 15;

        return intervalAmount;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public BookingDealModel getDeal() {
        return deal;
    }

    //setters
    public void setDeal(BookingDealModel deal) {
        this.deal = deal;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setDaysDate(Date daysDate) {
        this.daysDate = daysDate;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
}
