package NewcastleConnectionsPrototype.Group4.actions.deal;


import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.CreateDealModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;

public class CreateDealAction extends ActionSupport implements RequiresDBConnection, ModelDriven, CheckLogin, SessionAware, RequestAware {

    private DSLContext db;
    private CreateDealModel newDeal = new CreateDealModel();

    private File dealImageFile;
    private String myFileContentType;
    private String dealImageFileFileName;
    private String destPath;
    private Map<String, Object> session, request;

    public String execute() throws Exception {

        //HttpServletRequest request = ServletActionContext.getRequest();
        //HttpSession session = request.getSession();
        //UserBean user = (UserBean) session.getAttribute("userBean");
        UserBean user = (UserBean) session.get("userBean");



        Timestamp startDate = new Timestamp(newDeal.getStartDate().getTime());
        Timestamp endDate = new Timestamp(newDeal.getEndDate().getTime());


        /*
        if(dealImageFile != null) {
            saving files
            destPath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS");

           try {

               File destFile = new File(destPath, dealImageFileFileName);
                FileUtils.copyFile(dealImageFile, destFile);

           } catch (IOException e) {
               e.printStackTrace();
               return "error";
            }
        }
        */

        db.insertInto(DEALS)
                .columns(DEALS.BUSINESSID, DEALS.DEALTITLE, DEALS.DEALDESCRIPTION, DEALS.STARTDATE, DEALS.ENDDATE, DEALS.VALIDDURATION, DEALS.DEALIMAGEURL, DEALS.PRICE, DEALS.OLDPRICE, DEALS.DEALIMAGEURL)
                .values(user.getID(), newDeal.getDealTitle(), newDeal.getDescription(), startDate, endDate, newDeal.getValidDuration(), newDeal.getDealImageURL(), newDeal.getPrice(), newDeal.getOldPrice(), destPath + "/" + dealImageFileFileName)
                .execute();
        return "success";
    }

    public String navToPage(){
        return SUCCESS;
    }


    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public CreateDealModel getModel() {
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