package NewcastleConnectionsPrototype.Group4.models.view;


import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by simon janmaat on 16/08/2017.
 */
public class BookingEventModel {
    int businessId, eventID, quantity;
    Date startDate, endDate;
    ArrayList<Time> startTime, endTime;
    String eventImage, businessImage, eventTitle, eventDescription;
    boolean multistartOrEndTime = false;
    double oldPrice, price, savings;
    String savingsPercentage;
    DecimalFormat df = new DecimalFormat("#0");


    private int timeDayToUse = 0; // MUST DEFAULT TO 1! which time index to use from the times array. used in itinerary

    public int getTimeDayToUse() {
        return timeDayToUse;
    }

    public void setTimeDayToUse(int timeDayToUse) {
        this.timeDayToUse = timeDayToUse;
    }



    public BookingEventModel(int businessid, int eventid, Date start, Date end, ArrayList<Time> startTime, ArrayList<Time> endTime, String eventimage, boolean multiTime, double oldprice, double price, String title, String description)
    {

        this.businessId = businessid;
        this.eventTitle = title;
        this.eventDescription = description;
        this.eventID = eventid;
        this.startDate = start;
        this.endDate = end;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventImage = eventimage;
        multistartOrEndTime = multiTime;
        this.oldPrice = oldprice;
        this.price = price;
        this.savings = oldprice - price;
        this.savingsPercentage = this.savings + " ("+ df.format(this.savings / (oldPrice/100)) + "%)";
        this.quantity = 1;
    }


    public String getFormattedEventItemId() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime.get(timeDayToUse));
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int intervals = cal.get(Calendar.MINUTE) / 15;

        // date-hour-interval
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String returnString = sdf.format(startDate);
        returnString += "-" + hours + "-" + intervals;
        return returnString;

    }

    public int getEventIntervalAmount(){
        int INTERVAL_LENGTH = 15;
        int intervalAmount = 0;

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime.get(timeDayToUse));
        int hourStart = cal.get(Calendar.HOUR_OF_DAY);
        int minuteStart = cal.get(Calendar.MINUTE);

        cal.setTime(endTime.get(timeDayToUse));
        int hourEnd = cal.get(Calendar.HOUR_OF_DAY);
        int minuteEnd = cal.get(Calendar.MINUTE);

        int hours = hourEnd - hourStart;
        int minutes = minuteEnd - minuteStart;

        intervalAmount =  ((hours * 60) + minutes) / 15;

        return intervalAmount;
    }

    //getters
    public int getBusinessId() {
        return businessId;
    }

    public int getEventID() {
        return eventID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ArrayList<Time> getStartTime() {
        return startTime;
    }

    public ArrayList<Time> getEndTime() {
        return endTime;
    }

    public String getEventImage() {
        return eventImage;
    }

    public String getBusinessImage() {
        return businessImage;
    }

    public boolean isMultistartOrEndTime() {
        return multistartOrEndTime;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public double getPrice() {
        return price;
    }

    public double getSavings() {
        return savings;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSavingsPercentage() {
        return savingsPercentage;
    }

    //setters
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(ArrayList<Time> startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(ArrayList<Time> endTime) {
        this.endTime = endTime;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public void setBusinessImage(String businessImage) {
        this.businessImage = businessImage;
    }

    public void setMultistartOrEndTime(boolean multistartOrEndTime) {
        this.multistartOrEndTime = multistartOrEndTime;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setEventDescription(String eventdescription) {
        this.eventDescription = eventdescription;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void setSavingsPercentage(String savingsPercentage) {
        this.savingsPercentage = savingsPercentage;
    }
}
