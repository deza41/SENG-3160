package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by William on 16/10/2017.
 */
public class MostLikedBusinessPairModelTest {

    private MostLikedBusinessPairModel mostLikedBusinessPairModelTest = new MostLikedBusinessPairModel();

    @Test
    public void setNumLikes() throws Exception {
        mostLikedBusinessPairModelTest.setNumLikes(5);
        assertEquals(5, mostLikedBusinessPairModelTest.getNumLikes());

        mostLikedBusinessPairModelTest.setNumLikes(-1);
        assertEquals(-1, mostLikedBusinessPairModelTest.getNumLikes());

        mostLikedBusinessPairModelTest.setNumLikes(0);
        assertEquals(0, mostLikedBusinessPairModelTest.getNumLikes());

        mostLikedBusinessPairModelTest.setNumLikes(10000000);
        assertEquals(10000000, mostLikedBusinessPairModelTest.getNumLikes());
    }

    @Test
    public void setName() throws Exception {
        mostLikedBusinessPairModelTest.setName("Name");
        assertEquals("Name", mostLikedBusinessPairModelTest.getName());

        mostLikedBusinessPairModelTest.setName("");
        assertEquals("", mostLikedBusinessPairModelTest.getName());

        mostLikedBusinessPairModelTest.setName("123456");
        assertEquals("123456", mostLikedBusinessPairModelTest.getName());

    }

}