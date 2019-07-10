package NewcastleConnectionsPrototype.Group4.models.view;

/**
 * Created by simon janmaat on 18/08/2017.
 */
public class CreateReview {

    int userID, businessID, userRating;
    String reviewContent, reviewTitle, title;


    //setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //getters
    public int getUserID() {
        return userID;
    }

    public int getBusinessID() {
        return businessID;
    }

    public int getUserRating() {
        return userRating;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getTitle() {
        return title;
    }
}
