package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.CategoriesRecord;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.CATEGORIES;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSIMAGECONTENT;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.User.USER;

/**
 * Created by Tyrone on 17/08/2017.
 */

public class CreateBusinessAccountAction extends ActionSupport implements RequiresDBConnection, ModelDriven, CheckLogin, SessionAware {
    //upload var
    private File profileImageFile;
    private String myFileContentType;
    private String profileImageFileFileName;
    private String destPath;


    private DSLContext db;
    private String access_granted;
    public BusinessModel newBusiness = new BusinessModel();
    private Result<CategoriesRecord> categoryResult;
    private boolean emailError, usernameError;

    private List<File> files;
    private List<String> filesContentType;
    private List<String> filesFileName;


    private String saveDirectory = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS"); ///dir of the folder;

    private Map<String, Object> session;

    @Override
    public String execute() throws Exception {

        UserBean user = (UserBean) session.get("userBean");

        validation();

        destPath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS");

        if(profileImageFile != null) {
            /* saving files */
            /*
            try {


                File destFile = new File(destPath, profileImageFileFileName);
                FileUtils.copyFile(profileImageFile, destFile);

            } catch (IOException e) {

                e.printStackTrace();
                return "error";
            }
            */
        }else{
            profileImageFileFileName= "default Business Pic";
        }

        //this will add a new folder
        //Files.createDirectories(Paths.get("/path/to/directory"));
        //insert into database
        if(access_granted == "success") {

            this.categoryResult = db.select(CATEGORIES.fields())
                    .from(CATEGORIES)
                    .where(CATEGORIES.CATEGORY.eq(newBusiness.getCategoryName()))
                    .fetchInto(CATEGORIES);


            Date date = newBusiness.getBusinessOpen();
            Time newTime = new java.sql.Time(date.getTime());

            Date date2 = newBusiness.getBusinessClose();
            Time newTime2 = new java.sql.Time(date2.getTime());

           Result result = db.insertInto(BUSINESSUSER)
                    .columns(BUSINESSUSER.USERNAME,BUSINESSUSER.FIRSTNAME,BUSINESSUSER.LASTNAME, BUSINESSUSER.PHONENUMBER, BUSINESSUSER.EMAIL,
                            BUSINESSUSER.POSTCODE, BUSINESSUSER.PASSWORD, BUSINESSUSER.PROFILEIMAGEURL, BUSINESSUSER.BUSINESSNAME, BUSINESSUSER.STREET,
                            BUSINESSUSER.UNIT, BUSINESSUSER.SUBURB, BUSINESSUSER.BUSINESSDESCRIPTION, BUSINESSUSER.LATITUDE, BUSINESSUSER.LONGITUDE, BUSINESSUSER.NUMBER,
                            BUSINESSUSER.CATEGORYID, BUSINESSUSER.RATING, BUSINESSUSER.BUSINESSVIEWS, BUSINESSUSER.BUSINESSOPEN, BUSINESSUSER.BUSINESSCLOSE)
                    .values(newBusiness.getUserName(), newBusiness.getFirstName(), newBusiness.getLastName(), newBusiness.getPhoneNumber(), newBusiness.getEmail(),
                            newBusiness.getPostCode(), newBusiness.getPassword(), destPath + "/" + profileImageFileFileName, newBusiness.getBusinessName(), newBusiness.getStreet(),
                            newBusiness.getUnit(), newBusiness.getSuburb(), newBusiness.getBusinessDescription(), newBusiness.getLatitude(), newBusiness.getLongitude(),Integer.parseInt(newBusiness.getNumber()),
                            categoryResult.get(0).getCategoriesid(), newBusiness.getRating(), newBusiness.getBusinessViews(),newTime,newTime2)
                    .returning(BUSINESSUSER.BUSINESSID)
                    .fetch();
            //gets the new set user ID.
            int userID = (int) result.getValue(0, result.field(0));
            user.setID(userID);
            user.setLoggedIn(true);
            user.setRole("business");
            user.setFirstName(newBusiness.getFirstName());
            user.setLastName(newBusiness.getLastName());
            user.setEmail(newBusiness.getEmail());
            user.setContactNo(newBusiness.getPhoneNumber());
        }
        return access_granted;

    }

    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }


    public Object getModel() {
        return newBusiness;
    }

    public void validation()
    {
        //checks if the username has been used.
        int checkUsername =   db.select(DSL.count())
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.USERNAME.eq(newBusiness.getUserName()))
                .fetchOne(0, int.class);

        int checkEmail =   db.select(DSL.count())
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.EMAIL.eq(newBusiness.getEmail()))
                .fetchOne(0, int.class);

        int temp =   db.select(DSL.count())
                .from(USER)
                .where(USER.EMAIL.eq(newBusiness.getEmail()))
                .fetchOne(0, int.class);

        checkEmail += temp;
        if(checkUsername==1 && newBusiness.getUserName() != null)
        {
            //username in use.
            usernameError = true;
        }else {
            //username not used
            usernameError = false;
        }

        //email should only be able to add in once so it should be okay.
        if(checkEmail == 1 && newBusiness.getEmail() !=null)
        {
            //email in use
            emailError = true;
        }else {
            //email not used.
            emailError = false;
        }

        //email address format validation
        if(newBusiness.getEmail().contains(" ")){
            //email address contains at least one space, email address is invalid
            emailError = true;
        }else if(StringUtils.countOccurrencesOf(newBusiness.getEmail(), "@") != 1){
            //email address has either 0 or more than one instance of "@", invalid
            emailError = true;
        }

        if(usernameError || emailError)
        {
            //either username or email is in use.
            access_granted = "error";
            //set an error message accordingly.
        }else {
            access_granted = "success";
        }

        //test for null values of required fields

        if(newBusiness.getUserName() == null || newBusiness.getUserName().equals("")){
            access_granted = "error";

        }else if(newBusiness.getFirstName() == null || newBusiness.getFirstName().equals("")){
            access_granted = "error";

        }else if(newBusiness.getLastName() == null || newBusiness.getLastName().equals("")){
            access_granted = "error";

        }else if(newBusiness.getPhoneNumber() == null || newBusiness.getPhoneNumber().equals("")){
            access_granted = "error";

        }else if(newBusiness.getPassword() == null || newBusiness.getPassword().equals("")){
            access_granted = "error";

        }else if(newBusiness.getBusinessName() == null || newBusiness.getBusinessName().equals("")){
            access_granted = "error";

        }else if(newBusiness.getStreet() == null || newBusiness.getStreet().equals("")){
            access_granted = "error";

        }else if(newBusiness.getSuburb() == null || newBusiness.getSuburb().equals("")){
            access_granted = "error";

        }else if(newBusiness.getBusinessDescription() == null || newBusiness.getBusinessDescription().equals("")){
            access_granted = "error";

        }else if(newBusiness.getCategoryName() == null || newBusiness.getCategoryName().equals("")){
            access_granted = "error";

        }else if(newBusiness.getNumber() == null || newBusiness.getNumber().equals("")){
            access_granted = "error";

        }else if(newBusiness.getBusinessOpen() == null){
            access_granted = "error";

        }else if(newBusiness.getBusinessClose() == null){
            access_granted = "error";

        }

        //test for non-matching values for categoryName

        if(newBusiness.getCategoryName() != null && !newBusiness.getCategoryName().equals("")){

            int checkCategory =   db.select(DSL.count())
                    .from(CATEGORIES)
                    .where(CATEGORIES.CATEGORY.eq(newBusiness.getCategoryName()))
                    .fetchOne(0, int.class);

            if(checkCategory == 0){
                access_granted = "error";
            }
        }else{
            access_granted = "error";
        }

    }


    public File getProfileImageFile() {
        return profileImageFile;
    }
    public void setProfileImageFile(File profileImageFile) {
        this.profileImageFile = profileImageFile;
    }
    public String getMyFileContentType() {
        return myFileContentType;
    }
    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }
    public String getProfileImageFileFileName() {
        return profileImageFileFileName;
    }
    public void setProfileImageFileFileName(String profileImageName) {
        this.profileImageFileFileName = profileImageName;
    }


    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<String> getFilesContentType() {
        return filesContentType;
    }

    public List<String> getFilesFileName() {
        return filesFileName;
    }

    public void setFilesContentType(List<String> filesContentType) {
        this.filesContentType = filesContentType;
    }

    public void setFilesFileName(List<String> filesFileName) {
        this.filesFileName = filesFileName;
    }

    public String display() {
        return access_granted;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}