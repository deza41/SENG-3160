package NewcastleConnectionsPrototype.Group4.models.view;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Events;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsimageRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventstimesRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTSIMAGE;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTSTIMES;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Events.EVENTS;
import static org.junit.Assert.assertEquals;

/**
 * Created by simon janmaat on 26/09/2017.
 */
public class SearchEventsModelTest {

    @Test
    public void getListOfEvents() throws Exception {
        SearchEventsModel newSearch = new SearchEventsModel();

        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/db");

        try(Connection conn = ds.getConnection()){

            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            Result<EventsRecord> dblistOfEvents = db.select(EVENTS.fields())
                    .from(EVENTS)
                    .fetchInto(EVENTS);

            newSearch.setListOfEvents((dblistOfEvents));

            int eventCount = dblistOfEvents.size();

            Result<EventsRecord> listOfEvents = newSearch.getListOfEvents();

            assertEquals(eventCount, listOfEvents.size());
            assertEquals(new Integer(1), listOfEvents.get(0).getEventid());

        }

    }

    @Test
    public void getEventImagess() throws Exception {
        SearchEventsModel newSearch = new SearchEventsModel();

        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/db");

        try(Connection conn = ds.getConnection()){
            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            Result<EventsimageRecord> dbeventImages = db.select(EVENTSIMAGE.fields())
                    .from(EVENTSIMAGE)
                    .fetchInto(EVENTSIMAGE);

            newSearch.setEventImages(dbeventImages);

            Result<EventsimageRecord> eventImages = newSearch.getEventImages();

            assertEquals(dbeventImages.size(), eventImages.size());
            assertEquals(new Integer(1), eventImages.get(0).getEventid());
        }
    }

    @Test
    public void getEventTimes() throws Exception {
        SearchEventsModel newSearch = new SearchEventsModel();

        ArrayList<Result<EventstimesRecord>> dbeventTimes = new ArrayList<>();

        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/db");

        try (Connection conn = ds.getConnection()) {
            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            dbeventTimes.add(db.select(EVENTSTIMES.fields())
                    .from(EVENTSTIMES)
                    .where(EVENTSTIMES.EVENTID.eq(1))
                    .fetchInto(EVENTSTIMES)
            );
        }

        newSearch.setEventTimes(dbeventTimes);

        ArrayList<Result<EventstimesRecord>> eventTimes = newSearch.getEventTimes();

        assertEquals(dbeventTimes.size(), eventTimes.size());
        assertEquals(new Integer(1), eventTimes.get(0).get(0).getEventid()); //wow
    }

    @Test
    public void getPercentage() throws Exception {
        SearchEventsModel newSearch = new SearchEventsModel();

        ArrayList<Integer> percentage = new ArrayList<Integer>();

        percentage.add(50);
        percentage.add(70);

        newSearch.setPercentage(percentage);

        ArrayList<Integer> percentages = newSearch.getPercentage();

        assertEquals(new Integer(50), percentages.get(0));
        assertEquals(new Integer (70), percentages.get(1));
    }

}