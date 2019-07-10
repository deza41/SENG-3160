package NewcastleConnectionsPrototype.Group4.actions.deal;


import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import NewcastleConnectionsPrototype.Group4.models.view.CreateDealModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;

public class DealModifiedAction extends ActionSupport implements RequiresDBConnection, ModelDriven, CheckLogin, SessionAware, RequestAware {

    private DSLContext db;
    private CreateDealModel newDeal = new CreateDealModel();
    private int oldDealID;

    private File dealImageFile;
    private String myFileContentType;
    private String dealImageFileFileName;
    private String destPath;
    private Map<String, Object> session, request;
    private boolean timeNull = false;
    String  ErrorCheck = "success";

    public String execute() throws Exception {

       // HttpServletRequest request = ServletActionContext.getRequest();
       // HttpSession session = request.getSession();
       // UserBean user = (UserBean) session.getAttribute("userBean");
        UserBean user = (UserBean) session.get("userBean");

        Result<DealsRecord> dealsResults;

        String newDealImageURL = "www.gs";
        /*
        if(dealImageFile != null) {
            saving files
           destPath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS");

            newDealImageURL = destPath + "/" + dealImageFileFileName;

            try {

                File destFile = new File(destPath, dealImageFileFileName);
                FileUtils.copyFile(dealImageFile, destFile);

            } catch (IOException e) {
              e.printStackTrace();
              return "error";
            }
       }else{
            dealsResults = db.select()
                    .from(DEALS)
                    .where(DEALS.DEALID.eq(oldDealID))
                    .fetchInto(DEALS);
            newDealImageURL = dealsResults.get(0).getDealimageurl();
        }
        */
        //checks if the dates are not null
        if(newDeal.getEndDate() != null && newDeal.getStartDate() != null) {

            Timestamp startDate = new Timestamp(newDeal.getStartDate().getTime());
            Timestamp endDate = new Timestamp(newDeal.getEndDate().getTime());

            //create a new deal
            db.insertInto(DEALS)
                    .columns(DEALS.BUSINESSID, DEALS.DEALTITLE, DEALS.DEALDESCRIPTION, DEALS.STARTDATE, DEALS.ENDDATE, DEALS.VALIDDURATION, DEALS.DEALIMAGEURL, DEALS.PRICE, DEALS.OLDPRICE)
                    .values(user.getID(), newDeal.getDealTitle(), newDeal.getDescription(), startDate, endDate, newDeal.getValidDuration(), newDealImageURL, newDeal.getPrice(), newDeal.getOldPrice())
                    .execute();

            //deactivate the old deal
            db.update(DEALS).set(DEALS.ACTIVEDEAL, (byte) 0)
                    .where(DEALS.DEALID.eq(oldDealID))
                    .execute();
            ErrorCheck = "success";
        }
        else
        {
            ErrorCheck = "error";
        }

        return ErrorCheck;
    }

    public int getOldDealID() {
        return oldDealID;
    }

    public void setOldDealID(int oldDealID) {
        this.oldDealID = oldDealID;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Object getModel() {
        return newDeal;
    }

    public File getDealImageFile() {
        return dealImageFile;
    }
    public void setDealImageFile(File dealImageFile) {
        this.dealImageFile = dealImageFile;
    }
    public String getMyFileContentType() {
        return myFileContentType;
    }
    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }
    public String getDealImageFileFileName() {
        return dealImageFileFileName;
    }
    public void setDealImageFileFileName(String dealImageName) {
        this.dealImageFileFileName = dealImageName;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}

