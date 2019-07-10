package NewcastleConnectionsPrototype.Group4.models.view;

import java.util.Date;


public class CreateDealModel {
    String dealTitle, Description, dealImageURL;
    Date endDate, startDate;
    boolean activeDeal;
    int validDuration;
    double price;
    double oldPrice;

    //getters
    public int getDuration() {
        return validDuration;
    }

    public boolean isActiveDeal() {
        return activeDeal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getValidDuration() {
        return validDuration;
    }

    public double getPrice() {
        return price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public String getDealImageURL() {
        return dealImageURL;
    }

    public String getDescription() {
        return Description;
    }

    public String getDealTitle() {
        return dealTitle;
    }



    //setters
    public void setDealTitle(String dealTitle) {
        this.dealTitle = dealTitle;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setValidDuration(int validDuration) {
        this.validDuration = validDuration;
    }

    public void setActiveDeal(boolean activeDeal) {
        this.activeDeal = activeDeal;
    }

    public void setDealImageURL(String dealImageURL) {
        this.dealImageURL = dealImageURL;
    }


}
