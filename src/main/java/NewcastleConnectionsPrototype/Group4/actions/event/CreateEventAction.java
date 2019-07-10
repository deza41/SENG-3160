package NewcastleConnectionsPrototype.Group4.actions.event;

import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.EventTimeBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.CategoriesRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import NewcastleConnectionsPrototype.Group4.models.view.CreateEventModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTS;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTSIMAGE;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTSTIMES;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.CATEGORIES;


public class CreateEventAction extends ActionSupport implements RequiresDBConnection, ModelDriven, CheckLogin, SessionAware {

    private DSLContext db;
    private CreateEventModel newEvent = new CreateEventModel();
    private Result<CategoriesRecord> categoryResult;
    private Map<String, Object> session;

    private List<EventTimeBean> eventTimes;

    private File eventImageFile;
    private String myFileContentType;
    private String eventImageFileFileName;
    private String destPath;

    public String jsonMethod(){
        // this method just saves the dynamic event times from the page
        Map session = ActionContext.getContext().getSession();
        session.put("eventTimes",this.eventTimes);

        return SUCCESS;
    }

    public String execute() throws Exception {

        UserBean user = (UserBean) session.get("userBean");

        Date startDate = new Date(newEvent.getStartDate().getTime());
        Date endDate = new Date(newEvent.getEndDate().getTime());

        Result<EventsRecord> eventsResults;

        this.eventTimes = (List<EventTimeBean>) session.get("eventTimes");

        if(eventImageFile != null) {
            /* saving files */
            destPath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS");

            try {

                File destFile = new File(destPath, eventImageFileFileName);
                FileUtils.copyFile(eventImageFile, destFile);

            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        this.categoryResult = db.select()
                .from(CATEGORIES)
                .where(CATEGORIES.CATEGORY.eq(newEvent.getCategory()))
                .fetchInto(CATEGORIES);

        // create insert query to save deal

         db.insertInto(EVENTS)
                .columns(EVENTS.BUSINESSID, EVENTS.TITLE, EVENTS.CATEGORIESID, EVENTS.EVENTDESCRIPTION, EVENTS.STARTDATE, EVENTS.ENDDATE, EVENTS.PRICE, EVENTS.OLDPRICE)
                .values(user.getID(), newEvent.getTitle(), categoryResult.get(0).getCategoriesid(), newEvent.getEventDescription(), startDate, endDate, newEvent.getPrice(), newEvent.getOldPrice())
                .execute();

        eventsResults = db.select()
                .from(EVENTS)
                .where(EVENTS.BUSINESSID.eq(user.getID()), EVENTS.TITLE.eq(newEvent.getTitle()), EVENTS.CATEGORIESID.eq(categoryResult.get(0).getCategoriesid()), EVENTS.EVENTDESCRIPTION.eq(newEvent.getEventDescription()),
                        EVENTS.STARTDATE.eq(startDate), EVENTS.ENDDATE.eq(endDate), EVENTS.PRICE.eq(newEvent.getPrice()), EVENTS.OLDPRICE.eq(newEvent.getOldPrice()))
                .fetchInto(EVENTS);

        db.insertInto(EVENTSIMAGE)
                .columns(EVENTSIMAGE.EVENTID, EVENTSIMAGE.EVENTIMAGEURL, EVENTSIMAGE.PROFILEPICTURE)
                .values(eventsResults.get(0).getEventid(), destPath + "/" + eventImageFileFileName, (byte)1)
                .execute();

        //select one from events where events startdate = newEvent.getStartDate() and enddate = newEvent.getEndDate() and title = newEvent.getTitle() and businessID = newEvent.getBussinessID();
        //need to get the event id for this.


        //iterates through the times added into the model. (needs at least 1 time.)
        ListIterator<EventTimeBean> it = eventTimes.listIterator();
        EventTimeBean eTBean;
        String timeId;
        String timeAmount;

        String[] splitDate;
        String[] splitTime;

        String startHour;
        String startMinute;
        String endHour;
        String endMinute;
        String year;
        String month;
        String day;

        int startHourInt;
        int startMinuteInt;
        int endHourInt;
        int endMinuteInt;

        Calendar calendar = Calendar.getInstance();

        java.util.Date utilDate;
        java.sql.Time outStartTime;
        java.sql.Time outEndTime;

        // iterate through all the dynamic times we received and parse them to Date objects
        // for insertion into the database
        while(it.hasNext()){
            //start time
            eTBean = it.next();
            timeId = eTBean.getTimeId();
            timeAmount = eTBean.getTimeAmount();

            splitDate = timeId.split("-");
            year = splitDate[0];
            month = splitDate[1];
            day = splitDate[2];

            splitTime = timeAmount.split(":");
            startHour = splitTime[0];
            splitTime = splitTime[1].split(" ");
            startMinute = splitTime[0];

            startHourInt = Integer.parseInt(startHour);
            startMinuteInt = Integer.parseInt(startMinute);

            if(splitTime[1].equals("pm")){
                startHourInt = startHourInt + 12;
            }

            calendar.set(Calendar.HOUR_OF_DAY,  startHourInt);
            calendar.set(Calendar.MINUTE, startMinuteInt);
            utilDate = calendar.getTime();
            outStartTime = new java.sql.Time(utilDate.getTime());
            
            
            //end time
            eTBean = it.next();
            timeId = eTBean.getTimeId();
            timeAmount = eTBean.getTimeAmount();

            splitDate = timeId.split("-");
            year = splitDate[0];
            month = splitDate[1];
            day = splitDate[2];

            splitTime = timeAmount.split(":");
            endHour = splitTime[0];
            splitTime = splitTime[1].split(" ");
            endMinute = splitTime[0];

            endHourInt = Integer.parseInt(endHour);
            endMinuteInt = Integer.parseInt(endMinute);

            if(splitTime[1].equals("pm")){
                endHourInt = endHourInt + 12;
            }

            calendar.set(Calendar.HOUR_OF_DAY,  endHourInt);
            calendar.set(Calendar.MINUTE, endMinuteInt);
            utilDate = calendar.getTime();
            outEndTime = new java.sql.Time(utilDate.getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            java.sql.Date sqlDate = new java.sql.Date(sdf.parse(day + "-" + month + "-" + year).getTime());

            //insert into eventsTimes in db
            db.insertInto(EVENTSTIMES)
                    .columns(EVENTSTIMES.EVENTID, EVENTSTIMES.STARTTIME, EVENTSTIMES.ENDTIME, EVENTSTIMES.DAYSDATE)
                    .values(eventsResults.get(0).getEventid(), outStartTime, outEndTime, sqlDate)
                    .execute();

        }

        return SUCCESS;
    }

    public String navToPage(){
        return SUCCESS;
    }


    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public CreateEventModel getModel() {
        return newEvent;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public File getEventImageFile() {
        return eventImageFile;
    }
    public void setEventImageFile(File eventImageFile) {
        this.eventImageFile = eventImageFile;
    }
    public String getMyFileContentType() {
        return myFileContentType;
    }
    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }
    public String getEventImageFileFileName() {
        return eventImageFileFileName;
    }
    public void setEventImageFileFileName(String eventImageName) {
        this.eventImageFileFileName = eventImageName;
    }

    public List<EventTimeBean> getEventTimes() {
        return eventTimes;
    }

    public void setEventTimes(List<EventTimeBean> eventTimes) {
        this.eventTimes = eventTimes;
    }
}

