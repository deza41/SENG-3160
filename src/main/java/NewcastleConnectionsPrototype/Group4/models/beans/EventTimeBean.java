package NewcastleConnectionsPrototype.Group4.models.beans;

public class EventTimeBean {
    private String timeId;
    private String timeAmount;


    public EventTimeBean() {
    }

    public EventTimeBean(String timeId, String timeAmount) {
        this.timeId = timeId;
        this.timeAmount = timeAmount;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getTimeAmount() {
        return timeAmount;
    }

    public void setTimeAmount(String timeAmount) {
        this.timeAmount = timeAmount;
    }
}
