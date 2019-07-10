package NewcastleConnectionsPrototype.Group4.models.beans;

import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingModel;
import NewcastleConnectionsPrototype.Group4.models.view.UserModel;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by Simon on 15/08/2017.
 */
public class PackageBean implements Serializable {

    private Date startDate, endDate;
    private String date1, date2;
    private int numberOfAdults, numberOfChildren;
    private ArrayList<BookingDealModel> dealCart;
    private ArrayList<BookingEventModel> eventsCart;
    private ArrayList<BookingModel> bookings;
    private UserModel user;
    private int idCount = 0;
    private double totalCost = 0;
    private double discountAmount = 0;
    private double fullCost = 0;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

    public ArrayList<String> getItineraryDays() {
        ArrayList<String> itineraryDays = new ArrayList<String>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

            Calendar start = Calendar.getInstance();
            start.setTime(startDate);

            Calendar end = Calendar.getInstance();
            end.setTime(endDate);

            while (!start.after(end)) {
                Date targetDay = start.getTime();

                itineraryDays.add(sdf.format(targetDay.getTime()));

                start.add(Calendar.DATE, 1);
            }

        } catch(Exception e){
            System.out.println(e);
        }
        return itineraryDays;
    }

    public int generateUniqueId(){
        int i = idCount;
        idCount++;
        return i;
    }

    public PackageBean()
    {
        dealCart = new ArrayList<BookingDealModel>();
        eventsCart = new ArrayList<BookingEventModel>();
        bookings = new ArrayList<BookingModel>();
        user = new UserModel();
        startDate = new Date();
        endDate = new Date();

        date1 = dateFormat.format(startDate); //2016/11/16 12:08:43
        date2 = dateFormat.format(endDate);
    }


    public void addDealToPackage(BookingDealModel addDeal)
    {
        this.dealCart.add(addDeal);
    }

    public void addEventToPackage(BookingEventModel addEvent)
    {
        this.eventsCart.add(addEvent);
    }

    //getters.
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public ArrayList<BookingDealModel> getDealCart() {
        return dealCart;
    }

    public ArrayList<BookingDealModel> getItineraryDealCart() {
        // we need to return one with duplications for quantity
        ArrayList<BookingDealModel> tmpDealList = new ArrayList<BookingDealModel>();

        for (BookingDealModel deal: dealCart) {
            int quantity = deal.getQuantity();

            for(int i = 0; i < quantity; i++){
                tmpDealList.add(new BookingDealModel(deal.getDealID(), deal.getBusiness(), deal.getDealTitle(), deal.getDealImage(), deal.getPrice(), deal.getOldPrice(), deal.getDealDescription(), deal.getNumberOfChildren(), deal.getNumberOfAdults()));
            }

        }

        return tmpDealList;
    }

    public ArrayList<BookingEventModel> getEventsCart() {
        return eventsCart;
    }

    public ArrayList<BookingEventModel> getItineraryEventsCart() {
        // copy it for each day it's on

        ArrayList<BookingEventModel> tmpEventsCart = new ArrayList<BookingEventModel>();

        for (BookingEventModel event: eventsCart) {

            long diff = event.getStartDate().getTime() - event.getEndDate().getTime();


            int difference = Math.abs((int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

            Date incrementDate = event.getStartDate();

            for(int i = 0; i < difference; i++){
                Calendar cal = Calendar.getInstance();
                cal.setTime(incrementDate);
                cal.add(Calendar.DATE, 1);
                incrementDate = cal.getTime();

                BookingEventModel bm = new BookingEventModel(event.getBusinessId(), event.getEventID(), incrementDate, event.getEndDate(), event.getStartTime(), event.getEndTime(), event.getEventImage(), event.isMultistartOrEndTime(), event.getOldPrice(), event.getPrice(), event.getEventDescription(), event.getEventTitle());

                bm.setTimeDayToUse(i);

                tmpEventsCart.add(bm);

            }
        }

        return tmpEventsCart;
    }

    public UserModel getUser() {
        return user;
    }

    public ArrayList<BookingModel> getBookings() {
        return bookings;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

    //setters
    public void setStartDate(Date startDate) {
        this.date1 = dateFormat.format(startDate);
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.date2 = dateFormat.format(endDate);
        this.endDate = endDate;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setDealCart(ArrayList<BookingDealModel> dealCart) {
        this.dealCart = dealCart;
}

    public void setEventsCart(ArrayList<BookingEventModel> eventsCart) {
        this.eventsCart = eventsCart;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setBookings(ArrayList<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public void addBookingToPackage(BookingModel booking) {
        this.bookings.add(booking);
    }

    public void removeBookingFromPackage(Time startTime, Time endTime, Date daysDate, int dealID ) {
        this.bookings.removeIf(rmodel -> rmodel.getStartTime() == startTime && rmodel.getEndTime() == endTime && rmodel.getDaysDate() == daysDate && rmodel.getDeal().getDealID() == dealID);
    }

    public void removeBookingFrmPackage(int localBookingID){
        BookingModel temp;
        boolean added = false;
        Iterator<BookingModel> iter = this.bookings.iterator();

        while(iter.hasNext()){
            temp = iter.next();
            if(temp.getLocalBookingID() == localBookingID){
                for(int i = 0; i < dealCart.size(); i++) {

                    if(this.dealCart.get(i).getDealID() == temp.getDeal().getDealID())
                    {
                        this.dealCart.get(i).setQuantity(1);
                        added = true;
                        break;
                    }


                }
                if(!added){
                    this.dealCart.add(temp.getDeal());
                }
                iter.remove();
                break;
            }
        }
    }

    public void removeDeal(int i, int j){
        int qty = j;

        for(int k = 0; k < dealCart.size(); k++){
            if(dealCart.get(k).getDealID() == i && qty > 0){
                if(dealCart.get(k).getQuantity() == qty){
                    dealCart.remove(k);
                    break;
                }else if(dealCart.get(k).getQuantity() > qty){
                    dealCart.get(k).setQuantity(-qty);
                    break;
            }

            }
        }
        setTotalCost();
    }

    public void removeEvent(int i, int j){
        int qty = j;

        for(int k = 0; k < eventsCart.size(); k++){
            if(eventsCart.get(k).getEventID() == i && qty > 0){
                if(eventsCart.get(k).getQuantity() == qty){
                    eventsCart.remove(k);

                    break;
                }else if(eventsCart.get(k).getQuantity() > qty){
                    eventsCart.get(k).setQuantity(-qty);

                    break;
                }

            }
        }
        setTotalCost();
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public void setTotalCost()
    {
        totalCost = 0;

        for(BookingDealModel deal: dealCart){ //get price from each deal
            totalCost += deal.getPrice() * deal.getQuantity();
        }
        for(BookingEventModel event: eventsCart){ //get price from each event
            totalCost += event.getPrice() * event.getQuantity();
        }
        for(BookingModel booking: bookings){
            totalCost += booking.getDeal().getPrice();
        }
    }

    public String getTotalCost()
    {
        setTotalCost();
        return String.format("%5.2f", totalCost);
    }

    public String getFullCost()
    {
        fullCost = 0;
        for(BookingDealModel deal: dealCart){ //get price from each deal
            fullCost += deal.getOldPrice() * deal.getQuantity();
        }
        for(BookingEventModel event: eventsCart){ //get price from each event
            fullCost += event.getOldPrice() * event.getQuantity();
        }
        for(BookingModel booking: bookings){
            fullCost += booking.getDeal().getOldPrice();
        }

        return String.format("%5.2f", fullCost);
    }

    public String getDiscountAmount()
    {
        double discountAmount = fullCost - totalCost;

        return String.format("%5.2f", discountAmount);
    }
}
