
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class OpenListingsTest {

    @Test
    public void testGetDimensionsOfArray() {
        String json1 = "[[2], [2, [3]]]";
        String json2 = "[]";
        String json3 = "[[1]]";
        String json4 = "";
        String json5 = "[";


        assertEquals(3, OpenListings.getDimensionsOfArray(json1));
        assertEquals(1, OpenListings.getDimensionsOfArray(json2));
        assertEquals(2, OpenListings.getDimensionsOfArray(json3));
        assertEquals(0, OpenListings.getDimensionsOfArray(json4));
        assertEquals(-1, OpenListings.getDimensionsOfArray(json5));
    }

    @Test
    public void testGetListOfShittyListings() {
        String website = "/Users/jzhang/website";
        List<String> shittyListings = new ArrayList<>();
        shittyListings.add("/Users/jzhang/website/home.html");
        shittyListings.add("/Users/jzhang/website/san diego/255-via-clarita.html");

        assertEquals(shittyListings, OpenListings.getListOfShittyListings(website));
    }
}
