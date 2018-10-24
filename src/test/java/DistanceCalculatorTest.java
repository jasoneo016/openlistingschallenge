import api.DistanceCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest {

    @Test
    public void testGetDistance() {
        String origin = "2301 Hyperion Ave., Los Angeles, CA 90027";
        String destination = "1061 Market St., San Francisco, CA 94103";

        String sameloc1 = "2301 Hyperion Ave., Los Angeles, CA 90027";
        String sameloc2 = "2301 Hyperion Ave., Los Angeles, CA 90027";


        assertEquals("383 mi", DistanceCalculator.getDistance(origin, destination).toString());
        assertEquals("1 ft", DistanceCalculator.getDistance(sameloc1, sameloc2).toString());
    }
}
