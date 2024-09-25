package coordinates_calculations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Main {
    public static void main(String[] args) {

        Coordinates coordinates1 = new Coordinates(47.046651895466326, 21.90761404682888);
        Coordinates coordinates2 = new Coordinates(47.0697977725045, 21.93563407947531);
        long startTime = System.nanoTime();
        System.out.println(getDistanceBetweenCoordinates(coordinates1, coordinates2));
        long endTime = System.nanoTime() - startTime;
        System.out.println("First method took: " + endTime + " ns");
        long startTime2 = System.nanoTime();
        System.out.println(getDistance(coordinates1, coordinates2));
        long endTime2 = System.nanoTime() - startTime2;
        System.out.println("Second method took: " + endTime2 + " ns");
    }

    private static double getDistanceBetweenCoordinates(Coordinates coordinates, Coordinates otherCoordinates) {
        final int EARTH_RADIUS_IN_KM = 6_371;
        double dLat = Math.toRadians(otherCoordinates.getLat() - coordinates.getLat());
        double dLon = Math.toRadians(otherCoordinates.getLng() - coordinates.getLng());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(coordinates.getLat())) * Math.cos(Math.toRadians(otherCoordinates.getLat()))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_IN_KM * c;
    }

    private static Double getDistance(Coordinates origin, Coordinates destination) {
        double theta = origin.getLng() - destination.getLng();
        double latitudeSin = Math.sin(deg2rad(origin.getLat())) * Math.sin(deg2rad(destination.getLat()));
        double latitudeCos = Math.cos(deg2rad(origin.getLat())) * Math.cos(deg2rad(destination.getLat())) * Math.cos(deg2rad(theta));
        double dist = latitudeSin + latitudeCos;
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60.0 * 1.1515 * 1.609344;
        return dist;
    }

    private static double deg2rad(double deg) {
        return deg * Math.PI / 180.0;
    }

    private static double rad2deg(double rad) {
        return rad * 180.0 / Math.PI;
    }

}

@AllArgsConstructor
@Getter
@Setter
class Coordinates {

    private Double lat;
    private Double lng;
}
