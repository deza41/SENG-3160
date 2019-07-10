package NewcastleConnectionsPrototype.Group4.models.beans;

import java.io.Serializable;

/**
 * Created by Will on 06/08/2017.
 */
public class ItineraryItemBean implements Serializable {
    private int itineraryItemId;
    private String dayId;
    private int hourNumber;
    private int intervalNumber;
    private int intervalAmount;
    private int localBookingID;
    private int numberOfChildren;
    private int numberOfAdults;

    public ItineraryItemBean(int itineraryItemId, String dayId, int hourNumber, int intervalNumber, int intervalAmount, int numberOfChildren, int numberOfAdults) {
        this.itineraryItemId = itineraryItemId;
        this.dayId = dayId;
        this.hourNumber = hourNumber;
        this.intervalNumber = intervalNumber;
        this.intervalAmount = intervalAmount;
        this.numberOfChildren = numberOfChildren;
        this.numberOfAdults = numberOfAdults;
    }


    // getters and setters
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public ItineraryItemBean(){
        this.itineraryItemId = -1;

    }

    public int getLocalBookingID() {
        return localBookingID;
    }

    public void setLocalBookingID(int localBookingID) {
        this.localBookingID = localBookingID;
    }

    public int getItineraryItemId() {
        return itineraryItemId;
    }

    public void setItineraryItemId(int itineraryItemId) {
        this.itineraryItemId = itineraryItemId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public int getHourNumber() {
        return hourNumber;
    }

    public void setHourNumber(int hourNumber) {
        this.hourNumber = hourNumber;
    }

    public int getIntervalNumber() {
        return intervalNumber;
    }

    public void setIntervalNumber(int intervalNumber) {
        this.intervalNumber = intervalNumber;
    }

    public int getIntervalAmount() {
        return intervalAmount;
    }

    public void setIntervalAmount(int intervalAmount) {
        this.intervalAmount = intervalAmount;
    }
}
