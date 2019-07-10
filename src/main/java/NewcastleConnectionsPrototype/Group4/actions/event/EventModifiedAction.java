package NewcastleConnectionsPrototype.Group4.actions.event;


import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.CategoriesRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsimageRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventstimesRecord;
import NewcastleConnectionsPrototype.Group4.models.view.CreateEventModel;
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
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTSTIMES;

public class EventModifiedAction extends ActionSupport implements RequiresDBConnection, ModelDriven, CheckLogin, SessionAware {

    private DSLContext db;
    private CreateEventModel newEvent = new CreateEventModel();
    private int oldEventID;
    private Result<CategoriesRecord> categoryResult;
    private Map<String, Object> session;

    private File eventImageFile;
    private String myFileContentType;
    private String eventImageFileFileName;
    private String destPath;

    public String execute() throws Exception {

        UserBean user = (UserBean) session.get("userBean");

        Date startDate = new Date(newEvent.getStartDate().getTime());
        Date endDate = new Date(newEvent.getEndDate().getTime());

        Result<EventsRecord> eventsResults;
        Result<EventsimageRecord> eventsImageResults;
        Result<EventstimesRecord> eventsTimesResults;

        //Timestamp startDate = new Timestamp(newEvent.getStartDate().getTime());
        //Timestamp endDate = new Timestamp(newEvent.getEndDate().getTime());

        String newEventImageURL;

        if(eventImageFile != null) {
            /* saving files */
            destPath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS");

            newEventImageURL = destPath + "/" + eventImageFileFileName;

            try {

                File destFile = new File(destPath, eventImageFileFileName);
                FileUtils.copyFile(eventImageFile, destFile);

            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }else{
            eventsImageResults = db.select()
                    .from(EVENTSIMAGE)
                    .where(EVENTSIMAGE.EVENTID.eq(oldEventID))
                    .fetchInto(EVENTSIMAGE);

            newEventImageURL = eventsImageResults.get(0).getEventimageurl();

        }

        this.categoryResult = db.select(CATEGORIES.fields())
                .from(CATEGORIES)
                .where(CATEGORIES.CATEGORY.eq(newEvent.getCategory()))
                .fetchInto(CATEGORIES);

        eventsResults = db.insertInto(EVENTS)
                .columns(EVENTS.BUSINESSID, EVENTS.TITLE, EVENTS.CATEGORIESID, EVENTS.EVENTDESCRIPTION, EVENTS.STARTDATE, EVENTS.ENDDATE, EVENTS.PRICE, EVENTS.OLDPRICE)
                .values(user.getID(), newEvent.getTitle(), categoryResult.get(0).getCategoriesid(), newEvent.getEventDescription(), startDate, endDate, newEvent.getPrice(), newEvent.getOldPrice())
                .returning(EVENTS.EVENTID)
                .fetch();

        //deactivate the old event
        db.update(EVENTS).set(EVENTS.ACTIVEEVENT, (byte)0)
                .where(EVENTS.EVENTID.eq(oldEventID))
                    .execute();

        db.insertInto(EVENTSIMAGE)
                .columns(EVENTSIMAGE.EVENTID, EVENTSIMAGE.EVENTIMAGEURL, EVENTSIMAGE.PROFILEPICTURE)
                .values(eventsResults.get(0).getEventid(), newEventImageURL, (byte)1)
                .execute();

        eventsTimesResults = db.select()
                .from(EVENTSTIMES)
                .where(EVENTSTIMES.EVENTID.eq(oldEventID))
                .fetchInto(EVENTSTIMES);

        for(int i=0; i<eventsTimesResults.size(); i++){
            db.insertInto(EVENTSTIMES)
                    .columns(EVENTSTIMES.EVENTID, EVENTSTIMES.STARTTIME, EVENTSTIMES.ENDTIME, EVENTSTIMES.DAYSDATE)
                    .values(eventsResults.get(0).getEventid(), eventsTimesResults.get(i).getStarttime(), eventsTimesResults.get(i).getEndtime(), eventsTimesResults.get(i).getDaysdate())
                    .execute();
        }

        return "success";
    }

    public int getOldEventID() {
        return oldEventID;
    }

    public void setOldEventID(int oldEventID) {
        this.oldEventID = oldEventID;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Object getModel() {
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
}

