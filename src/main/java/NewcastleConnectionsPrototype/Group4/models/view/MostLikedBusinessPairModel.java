package NewcastleConnectionsPrototype.Group4.models.view;

public class MostLikedBusinessPairModel {

    private String name;
    private int numLikes;

    public MostLikedBusinessPairModel(){}

    public MostLikedBusinessPairModel(String name, int numLikes) {
        this.name = name;
        this.numLikes = numLikes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }
}
