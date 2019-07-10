package NewcastleConnectionsPrototype.Group4.actions;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTS;
/**
 * Created by blake on 13/05/2017.
 */
public class HomeAction extends ActionSupport implements RequiresDBConnection, ModelDriven<Result<EventsRecord>> {

    private Result<EventsRecord> model;
    private Result<DealsRecord> dealModel;
    private ArrayList<Integer> percentage = new ArrayList<Integer>();
    private ArrayList<BusinessuserRecord> listOfUsers = new ArrayList<BusinessuserRecord>();
    private DSLContext db;
    private String mappingVar = "success";

    public String execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("userBean");

        if (user != null) {
            if (user.getRole().equals("business")) {
                mappingVar = "business";
            } else if (user.getRole().equals("admin")) {
                //possible admin page.
            }
        }

        this.dealModel = db.select(DEALS.fields())
                            .from(DEALS)
                            .orderBy(DEALS.TOTALVIEWS.desc())
                            .fetchInto(DEALS);
        Iterator<DealsRecord> iter = dealModel.iterator();
        Result<BusinessuserRecord> temp;
        DealsRecord temp2;
        while(iter.hasNext()){
            temp2 = iter.next();
            Double temp3  = ((temp2.getOldprice() - temp2.getPrice()) / temp2.getOldprice() *100);

            percentage.add(temp3.intValue());


               temp = db.select(BUSINESSUSER.fields())
                    .from(DEALS)
                        .join(BUSINESSUSER)
                        .on(BUSINESSUSER.BUSINESSID.eq(DEALS.BUSINESSID))
                    .where(BUSINESSUSER.BUSINESSID.eq(temp2.getBusinessid()))
                    .orderBy(DEALS.TOTALVIEWS.desc())
                    .fetchInto(BUSINESSUSER);

            listOfUsers.add(temp.get(0));


        }


        this.model = db.select(EVENTS.EVENTID, EVENTS.TITLE, EVENTS.STARTDATE, EVENTS.ENDDATE)
                .from(EVENTS)
                .where(EVENTS.ACTIVEEVENT.eq((byte) 1))
                .fetchInto(EVENTS); //should have this sort by most popular events or something
        createEventsJson(context);

        //defaulted to "success"
        return mappingVar;
    }

    public void createEventsJson(ServletContext context) {

        JSONArray eventsList = new JSONArray();
        JSONObject eventsJSON = new JSONObject();

        int monthCount[] = new int[12]; //the number of events per month, so we can get just 5 out
        ArrayList<Integer> eventSpot = new ArrayList<Integer>(); //the spot of the event in database record
        int colour;

        int pos = -1; //position to get event from database
        for(int i = 0; i < this.model.size(); i++){ //for loop to get months where events start, having 5 events per month on calender
            Object eventStartDate = this.model.getValue(i, "startDate"); //getting the month from date object
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date)eventStartDate);
            int month = cal.get(Calendar.MONTH);
            pos++;
            if(monthCount[month - 1] != 5) {
                monthCount[month - 1]++;
                eventSpot.add(pos);
            }
        }

        pos = 0;//loop to create JSON object for calender
        for (int j = 0; j < 12; j++) { //loop through each month
            for(int i = 0; i < monthCount[j]; i++) {//get number of events in each month, max is 5
                Random random = new Random();
                colour = random.nextInt(256 * 256 * 256);
                JSONObject event = new JSONObject();

                event.put("id", this.model.getValue(eventSpot.get(pos), "eventID"));
                event.put("name", this.model.getValue(eventSpot.get(pos), "title"));
                event.put("startdate", this.model.getValue(eventSpot.get(pos), "startDate").toString());
                event.put("enddate", this.model.getValue(eventSpot.get(pos),"endDate").toString());
                event.put("starttime", "");
                event.put("endtime", "");
                event.put("color", String.format("#%06x", colour));
                event.put("url", "");
                eventsList.add(event);
                pos++; //update position
            }
        }
        eventsJSON.put("monthly", eventsList);

        try (FileWriter file = new FileWriter(context.getRealPath("events.json"))) { //save JSON object to a json file as string

            file.write(eventsJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Result<EventsRecord> getModel() {
        return model;
    }

    public Result<DealsRecord> getDealModel() {
        return dealModel;
    }

    public void setDealModel(Result<DealsRecord> dealModel) {
        this.dealModel = dealModel;
    }

    public ArrayList<BusinessuserRecord> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(ArrayList<BusinessuserRecord> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public ArrayList<Integer> getPercentage() {
        return percentage;
    }

    public void setPercentage(ArrayList<Integer> percentage) {
        this.percentage = percentage;
    }
}
