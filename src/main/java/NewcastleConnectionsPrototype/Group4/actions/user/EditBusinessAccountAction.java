package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSIMAGECONTENT;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.CATEGORIES;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.User.USER;

/**
 * Created by Tyrone on 12/08/2017.
 */
public class EditBusinessAccountAction extends ActionSupport implements RequiresDBConnection, ModelDriven<BusinessModel>, CheckLogin, SessionAware {
    //upload var

    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private String filePath;

    private DSLContext db;
    private String access_granted;
    private BusinessModel newBusiness = new BusinessModel();

    private List<File> files;
    private List<String> filesContentType;
    private List<String> filesFileName;
    private String saveDirectory = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS"); ///dir of the folder;

    private Map<String, Object> session;

    @Override

    public String execute() throws Exception {

        UserBean user = (UserBean) session.get("userBean");

        validation();

        if(files != null && !files.isEmpty()) {
            /*
            System.out.print("\n\n---------------------------------------");
            int i = 0;
            for (File file : files) {
                System.out.print("\nFile [" + i + "] ");
                System.out.print("; name:" + filesFileName.get(i));
                System.out.print("; contentType: " + filesContentType.get(i));
                System.out.print("; length: " + file.length());

                if (files.get(i) != null) {
                    try {
                        File destFile = new File(saveDirectory, filesFileName.get(i));
                        FileUtils.copyFile(files.get(i), destFile);

                        db.insertInto(BUSINESSIMAGECONTENT
                                ,BUSINESSIMAGECONTENT.BUSINESSID, BUSINESSIMAGECONTENT.BUSINESSIMAGEURL )
                                .values(user.getID() , saveDirectory );


                    } catch (IOException e) {
                        System.out.println("Could not upload file: " + filesFileName);
                        e.printStackTrace();
                        return "error";
                    }
                }
                i++;
            }
            */
        }


        if(access_granted == "success") {
            /* saving files */


            //System.out.println(userImage);
            //System.out.println("firstname = "+newBusiness.getFirstName());
            //System.out.println("street Number"+ newBusiness.getPostCode());
            if(userImage != null) {
                /*
                try {

                    filePath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS");; ///dir of the folder
                    //System.out.println("Server path:" + filePath);
                    //System.out.println(userImage);
                    //System.out.println(userImageFileName);
                    File fileToCreate = new File(filePath, this.userImageFileName);

                    FileUtils.copyFile(this.userImage, fileToCreate);
                } catch (Exception e) {
                    e.printStackTrace();
                    addActionError(e.getMessage());
                    //System.out.println("Failed");
                }
                */
            }
        }

        ///////////////////////  //check if the input has been changed /////////////////////////

        //insert into database
        if(access_granted == "success") {
            if(userImage != null) {
                /*
                db.update(BUSINESSUSER)
                        .set(BUSINESSUSER.FIRSTNAME, newBusiness.getFirstName())
                        .set(BUSINESSUSER.LASTNAME, newBusiness.getLastName())
                        .set(BUSINESSUSER.PHONENUMBER, newBusiness.getPhoneNumber())
                        .set(BUSINESSUSER.EMAIL, newBusiness.getEmail())
                        .set(BUSINESSUSER.POSTCODE, newBusiness.getPostCode())
                        .set(BUSINESSUSER.PROFILEIMAGEURL, filePath + "/" + userImageFileName)
                        .set(BUSINESSUSER.BUSINESSNAME, newBusiness.getBusinessName())
                        .set(BUSINESSUSER.STREET, newBusiness.getStreet())
                        .set(BUSINESSUSER.UNIT, newBusiness.getUnit())
                        .set(BUSINESSUSER.SUBURB, newBusiness.getSuburb())
                        .set(BUSINESSUSER.BUSINESSDESCRIPTION, newBusiness.getBusinessDescription())
                        .set(BUSINESSUSER.LATITUDE, newBusiness.getLatitude())
                        .set(BUSINESSUSER.LONGITUDE, newBusiness.getLongitude())
                        .set(BUSINESSUSER.NUMBER, Integer.parseInt(newBusiness.getNumber()))
                        .where(BUSINESSUSER.BUSINESSID.eq(user.getID()))
                        .execute();
                        */
            }else{
                db.update(BUSINESSUSER)
                        .set(BUSINESSUSER.FIRSTNAME, newBusiness.getFirstName())
                        .set(BUSINESSUSER.LASTNAME, newBusiness.getLastName())
                        .set(BUSINESSUSER.PHONENUMBER, newBusiness.getPhoneNumber())
                        .set(BUSINESSUSER.EMAIL, newBusiness.getEmail())
                        .set(BUSINESSUSER.POSTCODE, newBusiness.getPostCode())
                        .set(BUSINESSUSER.BUSINESSNAME, newBusiness.getBusinessName())
                        .set(BUSINESSUSER.STREET, newBusiness.getStreet())
                        .set(BUSINESSUSER.UNIT, newBusiness.getUnit())
                        .set(BUSINESSUSER.SUBURB, newBusiness.getSuburb())
                        .set(BUSINESSUSER.BUSINESSDESCRIPTION, newBusiness.getBusinessDescription())
                        .set(BUSINESSUSER.LATITUDE, newBusiness.getLatitude())
                        .set(BUSINESSUSER.LONGITUDE, newBusiness.getLongitude())
                        .set(BUSINESSUSER.NUMBER, Integer.parseInt(newBusiness.getNumber()))
                        .where(BUSINESSUSER.BUSINESSID.eq(user.getID()))
                        .execute();
            }

            if(!newBusiness.getPassword().equals(""))
            {

                db.update(BUSINESSUSER).set(BUSINESSUSER.PASSWORD, newBusiness.getPassword()).where(BUSINESSUSER.BUSINESSID.eq(user.getID()))
                        .execute();
            }
        }

        return access_granted;

    }

    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }





    public void validation()
    {
        UserBean user = (UserBean) session.get("userBean");

        this.access_granted = "success";

        int checkEmail =   db.select(DSL.count())
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.EMAIL.eq(newBusiness.getEmail()))
                .fetchOne(0, int.class);

        int temp =   db.select(DSL.count())
                .from(USER)
                .where(USER.EMAIL.eq(newBusiness.getEmail()))
                .fetchOne(0, int.class);

        checkEmail += temp;

        if(checkEmail > 0 && !user.getEmail().equals(newBusiness.getEmail()))
        {
            //email in use
            access_granted = "error";
        }

        //email address format validation
        if(newBusiness.getEmail() != null){
            if(newBusiness.getEmail().contains(" ")){
                //email address contains at least one space, email address is invalid
                access_granted = "error";
            }else if(StringUtils.countOccurrencesOf(newBusiness.getEmail(), "@") != 1){
                //email address has either 0 or more than one instance of "@", invalid
                access_granted = "error";
            }
        }

        //test for null values of required fields

        if(newBusiness.getFirstName() == null || newBusiness.getFirstName().equals("")){
            access_granted = "error";

        }else if(newBusiness.getLastName() == null || newBusiness.getLastName().equals("")){
            access_granted = "error";

        }else if(newBusiness.getPhoneNumber() == null || newBusiness.getPhoneNumber().equals("")){
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




    public java.lang.String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(java.lang.String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public java.lang.String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(java.lang.String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String display() {
        return access_granted;
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

    @Override
    public BusinessModel getModel() {
        return newBusiness;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
