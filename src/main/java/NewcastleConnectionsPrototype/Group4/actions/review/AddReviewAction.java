package NewcastleConnectionsPrototype.Group4.actions.review;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.ReviewRecord;
import NewcastleConnectionsPrototype.Group4.models.view.CreateReview;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.Date;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.REVIEW;
/**
 * Created by simon janmaat on 18/08/2017.
 */
public class AddReviewAction extends ActionSupport implements RequiresDBConnection, ModelDriven<CreateReview>{
    private DSLContext db;
    CreateReview newReview = new CreateReview();
    Date todaysDate = new Date();
    Result<ReviewRecord> listOfReviews;

    public String execute(){

        java.sql.Date sqlDate = new java.sql.Date(todaysDate.getTime());
        Time nowTime = new Time(System.currentTimeMillis());

        db.insertInto(REVIEW)
                .columns(REVIEW.USERID, REVIEW.BUSINESSID, REVIEW.TITLE,REVIEW.DETAILS,REVIEW.RATING,REVIEW.TIME, REVIEW.DATE)
                .values(1, newReview.getBusinessID(), newReview.getReviewTitle(),newReview.getReviewContent(), newReview.getUserRating(), nowTime, sqlDate)
                .execute();

        listOfReviews=  db.select(REVIEW.fields())
                          .from(REVIEW)
                          .where(REVIEW.BUSINESSID.eq(newReview.getBusinessID()))
                          .fetchInto(REVIEW);
        int totalReviews = 0;


        for(int i = 0; i< listOfReviews.size(); i++)
        {
          totalReviews += listOfReviews.get(i).getRating();
        }
        //calculates the average
        int avgReviews = 0;
        avgReviews = (totalReviews/listOfReviews.size()) * 10;

        //adds the new calc avg to db
        db.update(BUSINESSUSER)
                .set(BUSINESSUSER.RATING, avgReviews)
                .where(BUSINESSUSER.BUSINESSID.eq(newReview.getBusinessID()))
                .execute();

        return SUCCESS;
    }


    @Override
    public void setDSLContext(DSLContext ctx) {
        db = ctx;
    }

    @Override
    public CreateReview getModel() {
        return newReview;
    }
}
