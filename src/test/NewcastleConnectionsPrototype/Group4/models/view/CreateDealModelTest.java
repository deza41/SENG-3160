package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CreateDealModelTest {
    @Test
    public void getDuration() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setValidDuration(1);
        assertEquals(1, createDealModel.getDuration());
    }

    @Test
    public void isActiveDeal() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setActiveDeal(true);
        assertEquals(true, createDealModel.isActiveDeal());
    }

    @Test
    public void getStartDate() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        Date testDate = new Date();

        createDealModel.setStartDate(testDate);
        assertEquals(testDate, createDealModel.getStartDate());
    }

    @Test
    public void getEndDate() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        Date testDate = new Date();

        createDealModel.setEndDate(testDate);
        assertEquals(testDate, createDealModel.getEndDate());
    }

    @Test
    public void getValidDuration() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setValidDuration(1);
        assertEquals(1, createDealModel.getValidDuration());
    }

    @Test
    public void getPrice() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setPrice(1);
        assertEquals(1, createDealModel.getPrice(), 0.0001);
    }

    @Test
    public void getOldPrice() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setOldPrice(1);
        assertEquals(1, createDealModel.getOldPrice(), 0.0001);
    }

    @Test
    public void getDealImageURL() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setDealImageURL("Testing DealImageURL");
        assertEquals("Testing DealImageURL", createDealModel.getDealImageURL());
    }

    @Test
    public void getDescription() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setDescription("Testing Description");
        assertEquals("Testing Description", createDealModel.getDescription());
    }

    @Test
    public void getDealTitle() throws Exception {
        CreateDealModel createDealModel = new CreateDealModel();

        createDealModel.setDealTitle("Testing DealTitle");
        assertEquals("Testing DealTitle", createDealModel.getDealTitle());
    }

}