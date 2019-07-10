package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.models.beans.ItineraryItemBean;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingModel;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Will on 06/08/2017.
 */
public class ItinerarySaveAction extends ActionSupport implements SessionAware {

    private List<ItineraryItemBean> itineraryItems;

    public void setItineraryItems(List<ItineraryItemBean> itineraryItems)
    {
        this.itineraryItems = itineraryItems;
    }

    public List<ItineraryItemBean> getItineraryItems()
    {
        return itineraryItems;
    }

    private Map<String, Object> session, request;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String execute() throws Exception {
        PackageBean userPackage = (PackageBean)session.get("PackageBean");

        Map<Integer, BookingDealModel> dealCart = new HashMap<Integer, BookingDealModel>();

        for(BookingDealModel deal : userPackage.getDealCart() ) {
            dealCart.put( deal.getDealID(), deal );
        }

        Map<Integer, BookingEventModel> eventCart = new HashMap<Integer, BookingEventModel>();

        for(BookingEventModel event : userPackage.getEventsCart() ) {
            eventCart.put( event.getEventID(), event );
        }

        Map<Integer, BookingModel> bookings = new HashMap<Integer, BookingModel>();

        for(BookingModel booking : userPackage.getBookings() ) {
            bookings.put( booking.getLocalBookingID(), booking );
        }

        // grab deals and put any that have a time assigned into a booking
        for (ItineraryItemBean itineraryItem : itineraryItems) {

            if(itineraryItem.getDayId().equals("UNASSIGNED") == false && itineraryItem.getItineraryItemId() != -1) {
                Calendar calendar = Calendar.getInstance();

                BookingDealModel matchingDeal = dealCart.get(itineraryItem.getItineraryItemId());

                BusinessModel bookedBusiness = new BusinessModel();
                int INTERVAL_LENGTH = 15;

                // values we need to make a booking
                int numberOfAdults = itineraryItem.getNumberOfAdults();
                int numberOfChildren = itineraryItem.getNumberOfChildren();
                Time endTime;
                Time startTime;
                Date daysDate;

                // Set start time
                calendar.set(Calendar.HOUR_OF_DAY, itineraryItem.getHourNumber());
                calendar.set(Calendar.MINUTE, itineraryItem.getIntervalNumber() * INTERVAL_LENGTH);
                calendar.set(Calendar.SECOND, 0);
                java.util.Date utilDate = calendar.getTime();
                startTime = new java.sql.Time(utilDate.getTime());

                // Set end time
                int minutesLength = ((itineraryItem.getIntervalNumber() + itineraryItem.getIntervalAmount()) * INTERVAL_LENGTH);
                // add an extra 15 because the system stores the time slot it ends, not the time it ends,
                int hoursLength = minutesLength / 60;

                calendar.set(Calendar.HOUR_OF_DAY, itineraryItem.getHourNumber() + hoursLength);
                calendar.set(Calendar.MINUTE, minutesLength % 60);
                calendar.set(Calendar.SECOND, 0);
                utilDate = calendar.getTime();
                endTime = new java.sql.Time(utilDate.getTime());

                // convert days date
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                daysDate = sdf.parse(itineraryItem.getDayId());

                if(itineraryItem.getLocalBookingID() == -1) {
                    // if -1 then we don't have a booking ID stored so it is a new booking
                    int localBookingID = userPackage.generateUniqueId();

                    itineraryItem.setLocalBookingID(localBookingID);

                    BookingModel newBooking = new BookingModel(localBookingID, startTime, endTime, daysDate, numberOfAdults, numberOfChildren, matchingDeal);
                    userPackage.addBookingToPackage(newBooking);
                    userPackage.removeDeal(itineraryItem.getItineraryItemId(), 1);
                }
                else
                {
                    // if not -1 we are to update the old booking as it has changed details
                    BookingModel matchingBooking = bookings.get(itineraryItem.getLocalBookingID());
                    matchingBooking.setStartTime(startTime);
                    matchingBooking.setEndTime(endTime);
                    matchingBooking.setDaysDate(daysDate);
                    matchingBooking.setNumberOfAdults(itineraryItem.getNumberOfAdults());
                    matchingBooking.setNumberOfChildren(itineraryItem.getNumberOfChildren());
                }

            }
            else
            {
                // deal is unassigned to a time slot
                BookingModel matchingBooking = bookings.get(itineraryItem.getLocalBookingID());

                // if this deal was previously a booking, unassign it
                if(matchingBooking != null){
                    BookingDealModel deal = matchingBooking.getDeal();

                    deal.setNumberOfAdults(itineraryItem.getNumberOfAdults());
                    deal.setNumberOfChildren(itineraryItem.getNumberOfChildren());

                    userPackage.addDealToPackage(deal);

                    userPackage.removeBookingFromPackage(matchingBooking.getStartTime(), matchingBooking.getEndTime(), matchingBooking.getDaysDate(), deal.getDealID());
                }

            }
        }


        //return SUCCESS;
        return SUCCESS;
    }
}