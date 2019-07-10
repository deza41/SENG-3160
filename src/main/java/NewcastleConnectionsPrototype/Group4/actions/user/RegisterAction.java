package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.CreateUserModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;

/**
 * Created by simon janmaat on 2/08/2017.
 */
public class RegisterAction extends ActionSupport implements RequiresDBConnection, ModelDriven<CreateUserModel>, CheckLogin, SessionAware {

    private DSLContext db;
    private CreateUserModel newUser = new CreateUserModel();
    private String access_granted;
    private boolean emailerror, usernameerror;

    private File profileImageFile;
    private String myFileContentType;
    private String profileImageFileFileName;
    private String destPath;

    private Map<String, Object> session;


    @Override
    public String execute() throws Exception {

        UserBean user = (UserBean) session.get("userBean");

        validation();

        destPath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS"); ///dir of the folder

        if(profileImageFile != null) {
            /* saving files */


            /*
            try {
                //System.out.println("Src File name: " + profileImageFile);
                //System.out.println("Dst File name: " + profileImageFileFileName);

                File destFile = new File(destPath, profileImageFileFileName);
                FileUtils.copyFile(profileImageFile, destFile);

            } catch (IOException e) {
                //System.out.println("Could not upload file: " + profileImageFileFileName);
                e.printStackTrace();
                return "error";
            }
            */
        }else{
            profileImageFileFileName= "default Pic";
        }

        //this will add a new folder
        //Files.createDirectories(Paths.get("/path/to/directory"));

        if(access_granted == "success") {
            java.sql.Date sqlDateOfBirth = new java.sql.Date((newUser.getDateOfBirth().getTime()));

           Result result = db.insertInto(USER, USER.USERNAME, USER.FIRSTNAME, USER.LASTNAME, USER.PHONENUMBER, USER.EMAIL, USER.POSTCODE, USER.PASSWORD, USER.PROFILEIMAGEURL, USER.DATEOFBIRTH)
                    .values( newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getPostCode(), newUser.getPassword(), destPath + "/" + profileImageFileFileName, sqlDateOfBirth)
                    .returning(USER.USERID)
                    .fetch();
           //gets the new set user ID.
           int userID = (int) result.getValue(0, result.field(0));

           user.setID(userID);
           user.setLoggedIn(true);
           user.setRole("user");
           user.setFirstName(newUser.getFirstName());
           user.setLastName(newUser.getLastName());
           user.setEmail(newUser.getEmail());
           user.setContactNo(newUser.getPhoneNumber());
        }


        return access_granted;

    }

    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }


    @Override
    public CreateUserModel getModel() {
        return newUser;
    }

    public String validation()
    {
        //checks if the username has been used.
        int checkUsername =   db.select(DSL.count())
                        .from(USER)
                        .where(USER.USERNAME.eq(newUser.getUsername()))
                        .fetchOne(0, int.class);

        int checkEmail =   db.select(DSL.count())
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.EMAIL.eq(newUser.getEmail()))
                .fetchOne(0, int.class);

        int temp =   db.select(DSL.count())
                .from(USER)
                .where(USER.EMAIL.eq(newUser.getEmail()))
                .fetchOne(0, int.class);

        checkEmail += temp;
        if(checkUsername==1 && newUser.getUsername() != null)
        {
            //username in use.
            usernameerror = true;
        }else {
            //username not used
            usernameerror = false;
        }

        //email should only be able to add in once so it should be okay.
        if(checkEmail == 1 && newUser.getEmail() !=null)
        {
            //email in use
            emailerror = true;
        }else {
            //email not used.
            emailerror = false;
        }

        //email address format validation
        if(newUser.getEmail().contains(" ")){
            //email address contains at least one space, email address is invalid
            emailerror = true;
        }else if(StringUtils.countOccurrencesOf(newUser.getEmail(), "@") != 1){
            //email address has either 0 or more than one instance of "@", invalid
            emailerror = true;
        }

        if(usernameerror || emailerror)
        {
            //either username or email is in use.
            access_granted = "error";
            //set an error message accordingly.
        }else {
            access_granted = "success";
        }

        //test for null values of required fields

        if(newUser.getUsername() == null || newUser.getUsername().equals("")){
            access_granted = "error";
        }else if(newUser.getFirstName() == null || newUser.getFirstName().equals("")){
            access_granted = "error";

        }else if(newUser.getLastName() == null || newUser.getLastName().equals("")){
            access_granted = "error";

        }else if(newUser.getPhoneNumber() == null || newUser.getPhoneNumber().equals("")){
            access_granted = "error";

        }else if(newUser.getPassword() == null || newUser.getPassword().equals("")){
            access_granted = "error";

        }else if(newUser.getDateOfBirth() == null){
            access_granted = "error";
        }

        return access_granted;
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

    public String display() {
        return access_granted;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
