package api;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

import java.io.IOException;

public class DistanceCalculator {

    private static final String API_KEY = "AIzaSyDBAjidO8byrVXcY6bR9yqxbhE4HRp_SaU";

    /**
     *
     * Take in an origin and destination address and be able to return the driving distance in meters or miles.
     *
     * @param origin The starting location to calculate distance from
     * @param destination The ending location to calculate distance to
     * @return Returns the distance in meters or miles.
     */
    public static Distance getDistance(String origin, String destination) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
        DistanceMatrix result = null;
        try {
            result = req.origins(origin)
                    .destinations(destination)
                    .mode(TravelMode.DRIVING)
                    .units(Unit.IMPERIAL)
                    .avoid(DirectionsApi.RouteRestriction.TOLLS)
                    .language("en-US")
                    .await();
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        Distance drivingDistance = result.rows[0].elements[0].distance;
        return drivingDistance;
    }
}
