package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;
/**
 * Created by simon janmaat on 27/08/2017.
 */
public class EditAccountAction extends ActionSupport implements RequiresDBConnection, SessionAware {
    private DSLContext db;
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private String password, firstName, lastName, phoneNumber, email, postCode, filePath;
    Date dateOfBirth;

    private Map<String, Object> session;

    public String execute() throws Exception{

        UserBean user = (UserBean) session.get("userBean");

        String result = validateInput();

        if(result == "success") {

            if (userImage != null) {
                /*
                try {

                    filePath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS"); ///dir of the folder
                    //System.out.println("Server path:" + filePath);
                    File fileToCreate = new File(filePath, this.userImageFileName);

                    FileUtils.copyFile(this.userImage, fileToCreate);
                } catch (Exception e) {
                    e.printStackTrace();
                    addActionError(e.getMessage());

                }
                */

            }
            java.sql.Date sqlDateOfBirth = new java.sql.Date(dateOfBirth.getTime());

            db.update(USER)
                    .set(USER.FIRSTNAME, firstName)
                    .set(USER.LASTNAME, lastName)
                    .set(USER.DATEOFBIRTH, sqlDateOfBirth)
                    .set(USER.PHONENUMBER, phoneNumber)
                    .set(USER.EMAIL, email)
                    .set(USER.POSTCODE, postCode)
                    .set(USER.PROFILEIMAGEURL, filePath + "/" + userImageFileName)
                    .where(USER.USERID.eq(user.getID()))
                    .execute();


            if (!password.equals("")) {

                db.update(USER).set(USER.PASSWORD, password).where(USER.USERID.eq(user.getID()))
                        .execute();
            }
        }


        return result;
    }

    private String validateInput(){
        String success = "success",
                error="error",
                result = success;

        UserBean user = (UserBean) session.get("userBean");

        //checks if the email has been used.
        int checkEmail =   db.select(DSL.count())
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.EMAIL.eq(email))
                .fetchOne(0, int.class);

        int temp =   db.select(DSL.count())
                .from(USER)
                .where(USER.EMAIL.eq(email))
                .fetchOne(0, int.class);

        checkEmail += temp;

        if(checkEmail > 0 && !user.getEmail().equals(email))
        {
            //email in use
            result = error;
        }

        //email address format validation
        if (email != null) {
            if(email.contains(" ")){
                //email address contains at least one space, email address is invalid
                result = error;
            }else if(StringUtils.countOccurrencesOf(email, "@") != 1){
                //email address has either 0 or more than one instance of "@", invalid
                result = error;
            }
        }

        //test for null values of required fields

        if(firstName == null || firstName.equals("")){
            result = error;
            
        }else if(lastName == null || lastName.equals("")){
            result = error;

        }else if(phoneNumber == null || phoneNumber.equals("")){
            result = error;

        }else if(dateOfBirth == null){
            result = error;
        }
        
        return result;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}