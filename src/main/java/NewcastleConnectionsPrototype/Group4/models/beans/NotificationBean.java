package NewcastleConnectionsPrototype.Group4.models.beans;

import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.NotificationsRecord;
import org.jooq.Result;

import java.io.Serializable;

/**
 * Created by simon janmaat on 29/08/2017.
 */
public class NotificationBean implements Serializable {

    private Result<NotificationsRecord> result;

    public Result<NotificationsRecord> getResult() {
        return result;
    }

    public void setResult(Result<NotificationsRecord> result) {
        this.result = result;
    }
}
