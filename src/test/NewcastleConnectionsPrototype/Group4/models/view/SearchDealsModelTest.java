package NewcastleConnectionsPrototype.Group4.models.view;

import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Likedbusinesses;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.LikedbusinessesRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.ReviewRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import NewcastleConnectionsPrototype.Group4.TestDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.REVIEW;
import static org.junit.Assert.assertEquals;

/**
 * Created by simon janmaat on 26/09/2017.
 */

public class SearchDealsModelTest {

    SearchDealsModel searchDealsModel = new SearchDealsModel();

    @Test
    public void setListOfBusinesses() throws Exception {

        SearchDealsModel newSearch = new SearchDealsModel();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/db");

        try(Connection conn = ds.getConnection()){


            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            Result<BusinessuserRecord> dbResult = db.select(BUSINESSUSER.fields())
                    .from(BUSINESSUSER)
                    .join(CATEGORIES)
                    .on(BUSINESSUSER.CATEGORYID.eq(CATEGORIES.CATEGORIESID))
                    .where(CATEGORIES.CATEGORY.eq("food"))
                    .fetchInto(BUSINESSUSER);

            int resultCount = dbResult.size();

            newSearch.setListOfBusinesses(dbResult);

            Result<BusinessuserRecord> listOfBusinesses = newSearch.getListOfBusinesses();

            BusinessuserRecord rec = listOfBusinesses.get(0);

            // assert results
            assertEquals(resultCount, listOfBusinesses.size());
            assertEquals(new Integer(1), rec.getBusinessid());
            assertEquals(new Integer(1), rec.getCategoryid());
            assertEquals(resultCount, newSearch.getBusinessListSize());
        }
    }



    @Test
    public void setListOfDeals() throws Exception {

        SearchDealsModel newSearch = new SearchDealsModel();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/db");

        try(Connection conn = ds.getConnection()){

            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            Result<DealsRecord> dbResult = db.select(DEALS.fields())
                    .from(DEALS)
                    .where(DEALS.BUSINESSID.eq(1))
                    .orderBy(DEALS.TOTALPURCHASED.desc())
                    .fetchInto(DEALS);

            int resultCount = dbResult.size();

            newSearch.setListOfDeals(dbResult);

            Result<DealsRecord> listoflistofdeals = newSearch.getListOfListOfDeals().get(0);

            DealsRecord rec = listoflistofdeals.get(0);

            // assert results
            assertEquals(resultCount, listoflistofdeals.size());
            assertEquals(1, newSearch.getDealsListsSize());
            assertEquals(new Integer(1), rec.getBusinessid());
            assertEquals(new Integer(1), rec.getDealid());
        }
    }

    @Test
    public void setListOfReviews() throws Exception {

        SearchDealsModel newSearch = new SearchDealsModel();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/db");

        try(Connection conn = ds.getConnection()){

            DSLContext db = DSL.using(conn, SQLDialect.MYSQL);

            Result<ReviewRecord> dbResult = db.select(REVIEW.fields())
                    .from(REVIEW)
                    .where(REVIEW.BUSINESSID.eq(1))
                    .orderBy(REVIEW.DATE.desc())
                    .fetchInto(REVIEW);


            int resultCount = dbResult.size();

            newSearch.setListOfReviews(dbResult);

            Result<ReviewRecord> listoflistofreviews = newSearch.getListOfListOfReviews().get(0);

            ReviewRecord rec = listoflistofreviews.get(0);

            // assert results
            assertEquals(resultCount, listoflistofreviews.size());
            assertEquals(resultCount, newSearch.getReviewsListsSize());
            assertEquals(new Integer(1), rec.getBusinessid());
            assertEquals(new Integer(1), rec.getReviewid());
        }
    }

    @Test
    public void setListOfLikedBusiness() throws Exception {

        SearchDealsModel newSearch = new SearchDealsModel();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());

        Context ctx = new InitialContext();

        newSearch.addListOfLikedBusiness(true);
        newSearch.addListOfLikedBusiness(false);

        ArrayList<Boolean> list = newSearch.getListOfLikedBusiness();

        // assert results
        assertEquals(2, list.size());
        assertEquals(true, list.get(0));
        assertEquals(false, list.get(1));

    }

}