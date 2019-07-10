package NewcastleConnectionsPrototype.Group4.actions.statistics;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import NewcastleConnectionsPrototype.Group4.models.view.MostLikedBusinessPairModel;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;

import java.util.ArrayList;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.LIKEDBUSINESSES;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Businessuser.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Deals.DEALS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static org.jooq.impl.DSL.count;

public class ViewStatisticsAction implements RequiresDBConnection {

    private Result<DealsRecord> mostViewedDeals;
    private Result<DealsRecord> mostPurchasedDeals;
    private Result<org.jooq.Record2<java.lang.String, java.lang.Integer>> mostLikedBusinessNames;

    private ArrayList<MostLikedBusinessPairModel> mostLikedBusinessPairModelList;

    private DSLContext db;

    public String execute(){
        try {
            this.mostViewedDeals = db.select()
                    .from(DEALS)
                    .orderBy(DEALS.TOTALVIEWS.desc())
                    .fetchInto(DEALS);

            this.mostPurchasedDeals = db.select()
                    .from(DEALS)
                    .orderBy(DEALS.TOTALPURCHASED.desc())
                    .fetchInto(DEALS);

            mostLikedBusinessNames = db.select(BUSINESSUSER.BUSINESSNAME, count(LIKEDBUSINESSES.LIKED).as("numlikes"))
                    .from(BUSINESSUSER)
                    .innerJoin(LIKEDBUSINESSES).on(BUSINESSUSER.BUSINESSID.eq(LIKEDBUSINESSES.BUSINESSID))
                    .groupBy(BUSINESSUSER.BUSINESSNAME)
                    .orderBy(count(LIKEDBUSINESSES.LIKED).desc())
                    .fetch();

            mostLikedBusinessPairModelList = new ArrayList<>();

            for(int i = 0; i < mostLikedBusinessNames.size(); i++){

                mostLikedBusinessPairModelList.add(new MostLikedBusinessPairModel(mostLikedBusinessNames.get(i).value1(), mostLikedBusinessNames.get(i).value2()));
            }


        }catch(Exception e){
            return "error";
        }

        return SUCCESS;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
            this.db = ctx;
    }

    public Result<DealsRecord> getMostViewedDeals() {
        return mostViewedDeals;
    }

    public Result<DealsRecord> getMostPurchasedDeals() {
        return mostPurchasedDeals;
    }

    public Result<Record2<String, Integer>> getMostLikedBusinessNames() {
        return mostLikedBusinessNames;
    }

    public ArrayList<MostLikedBusinessPairModel> getMostLikedBusinessPairModelList() {
        return mostLikedBusinessPairModelList;
    }
}
