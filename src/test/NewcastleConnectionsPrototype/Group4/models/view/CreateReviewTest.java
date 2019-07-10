package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateReviewTest {
    @Test
    public void getUserID() throws Exception {
        CreateReview createReview = new CreateReview();

        createReview.setUserID(1);
        assertEquals(1, createReview.getUserID());
    }

    @Test
    public void getBusinessID() throws Exception {
        CreateReview createReview = new CreateReview();

        createReview.setBusinessID(1);
        assertEquals(1, createReview.getBusinessID());
    }

    @Test
    public void getUserRating() throws Exception {
        CreateReview createReview = new CreateReview();

        createReview.setUserRating(1);
        assertEquals(1, createReview.getUserRating());
    }

    @Test
    public void getReviewContent() throws Exception {
        CreateReview createReview = new CreateReview();

        createReview.setReviewContent("Testing ReviewContent");
        assertEquals("Testing ReviewContent", createReview.getReviewContent());
    }

    @Test
    public void getReviewTitle() throws Exception {
        CreateReview createReview = new CreateReview();

        createReview.setReviewTitle("Testing ReviewTitle");
        assertEquals("Testing ReviewTitle", createReview.getReviewTitle());
    }

    @Test
    public void getTitle() throws Exception {
        CreateReview createReview = new CreateReview();

        createReview.setTitle("Testing Title");
        assertEquals("Testing Title", createReview.getTitle());
    }

}