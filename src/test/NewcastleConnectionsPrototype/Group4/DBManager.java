package NewcastleConnectionsPrototype.Group4;

import NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.CategoriesRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.NotificationsRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.management.remote.NotificationResult;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Time;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

public class DBManager {

    String userName = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/prototype";

    public void delete(String table, String identifier){

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            if(table.equals("user")){
                db.delete(USER)
                    .where(USER.USERNAME.eq(identifier))
                    .execute();
            }else if(table.equals("businessUser")){
                db.delete(BUSINESSUSER)
                    .where(BUSINESSUSER.USERNAME.eq(identifier))
                    .execute();
            }else if(table.equals("shoppingcart")){
                db.delete(USER)
                        .where(USER.FIRSTNAME.eq(identifier))
                        .execute();
            } else if(table.equals("notifications")){
                db.delete(NOTIFICATIONS)
                        .where(NOTIFICATIONS.DESCRIPTION.equal(identifier))
                        .execute();
            } else if(table.equals("relationUserNotification")) {

                db.delete(RELATIONBUSINESSUSERNOTIFICATION)
                        .where(RELATIONBUSINESSUSERNOTIFICATION.NOTIFICATIONID.equal(Integer.parseInt(identifier)))
                        .execute();

                db.delete(RELATIONUSERNOTIFICATION)
                        .where(RELATIONUSERNOTIFICATION.NOTIFICATIONID.equal(Integer.parseInt(identifier)))
                        .execute();
            }else if(table.equals("liked")){
                db.delete(LIKEDBUSINESSES)
                        .where(LIKEDBUSINESSES.BUSINESSID.eq(2))
                        .and(LIKEDBUSINESSES.USERID.eq(1))
                        .execute();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHighestId(String table) {
        int id = -1;
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            if (table.equals("notifications")) {
                Result<NotificationsRecord> result = db.select(NOTIFICATIONS.NOTIFICATIONID)
                        .from(NOTIFICATIONS)
                        .orderBy(NOTIFICATIONS.NOTIFICATIONID.desc())
                        .fetchInto(NOTIFICATIONS);

                id = result.get(0).getNotificationid();
            }

        } catch(Exception e){
                e.printStackTrace();
        }

        return id;
    }

    public void createTestBusinessUser(){

        String  businessUserName = "Test userName",
                firstName = "Test firstName",
                lastName = "Test lastName",
                phoneNumber = "Test phoneNumber",
                email = "Test@email",
                postCode = "Test postCode",
                businessPassword = "Test password",
                profileImageURL = "Test profileImageURL",
                businessName = "Test businessName",
                street = "Test street",
                unit = "Test unit",
                suburb = "Test suburb",
                businessDescription = "Test businessDescription",
                latitude = "Test latitude",
                longitude = "Test longitude",
                categoryName = "food",
                number = "1",
                businessOpen = "",
                businessClose = "2017-10-19";

        int     rating = 3,
                businessViews = 2;

        String profileImageFileFileName = "default Business Pic";

        String destPath = String.format(System.getProperty("user.dir") + "/../" + "webapps" + "/DMS");

        //this will add a new folder
        //Files.createDirectories(Paths.get("/path/to/directory"));
        //insert into database

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            Result<CategoriesRecord> categoryResult = db.select(CATEGORIES.fields())
                    .from(CATEGORIES)
                    .where(CATEGORIES.CATEGORY.eq(categoryName))
                    .fetchInto(CATEGORIES);


            java.util.Date date = new java.util.Date();
            Time newTime = new java.sql.Time(date.getTime());
            Time newTime2 = new java.sql.Time(date.getTime());

            db.insertInto(Tables.BUSINESSUSER)
                    .columns(Tables.BUSINESSUSER.USERNAME, Tables.BUSINESSUSER.FIRSTNAME, Tables.BUSINESSUSER.LASTNAME, Tables.BUSINESSUSER.PHONENUMBER, Tables.BUSINESSUSER.EMAIL,
                            Tables.BUSINESSUSER.POSTCODE, Tables.BUSINESSUSER.PASSWORD, Tables.BUSINESSUSER.PROFILEIMAGEURL, Tables.BUSINESSUSER.BUSINESSNAME, Tables.BUSINESSUSER.STREET,
                            Tables.BUSINESSUSER.UNIT, Tables.BUSINESSUSER.SUBURB, Tables.BUSINESSUSER.BUSINESSDESCRIPTION, Tables.BUSINESSUSER.LATITUDE, Tables.BUSINESSUSER.LONGITUDE, Tables.BUSINESSUSER.NUMBER,
                            Tables.BUSINESSUSER.CATEGORYID, Tables.BUSINESSUSER.RATING, Tables.BUSINESSUSER.BUSINESSVIEWS, Tables.BUSINESSUSER.BUSINESSOPEN, Tables.BUSINESSUSER.BUSINESSCLOSE)
                    .values(businessUserName, firstName, lastName, phoneNumber, email,
                            postCode, businessPassword, destPath + "/" + profileImageFileFileName, businessName, street,
                            unit, suburb, businessDescription, latitude, longitude,Integer.parseInt(number),
                            categoryResult.get(0).getCategoriesid(), rating, businessViews,newTime,newTime2)
                    .returning(Tables.BUSINESSUSER.BUSINESSID)
                    .fetch();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void deleteTestBusinessUser(){
        delete("businessUser", "Test userName");
    }
}
