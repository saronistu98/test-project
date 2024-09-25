package test;

import lombok.experimental.UtilityClass;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Math.PI;

@UtilityClass
class DistanceUtils {

    double getDistance(Coordinate origin, Coordinate destination) {
        double theta = origin.getY() - destination.getY();
        double latitudeSin = Math.sin(deg2rad(origin.getX())) * Math.sin(deg2rad(destination.getX()));
        double latitudeCos = Math.cos(deg2rad(origin.getX())) * Math.cos(deg2rad(destination.getX())) * Math.cos(deg2rad(theta));
        double dist = latitudeSin + latitudeCos;
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60.0 * 1.1515 * 1.609344;
        return dist;
    }

    double calculateTotalDistance(List<BusinessCluster> businessClusters) {
        return businessClusters.stream().flatMapToDouble(businessCluster -> businessCluster.getBusinesses().stream()
                        .mapToDouble(business -> DistanceUtils.getDistance(business.getCoordinate(), businessCluster.getCentroid().getCoordinate()) * business.getNumberOfOrders()))
                .sum();
    }

    BusinessCluster getNearestOtherCluster(Coordinate coordinate, List<BusinessCluster> businessClusters, List<BusinessCluster> clustersToIgnore) {
        BusinessCluster closestBase = null;
        double distanceToClosestBase = MAX_VALUE;
        for (BusinessCluster businessCluster : businessClusters) {
            if (clustersToIgnore.contains(businessCluster))
                continue;
            double distanceBetweenCoordinatesAndBase = DistanceUtils.getDistance(coordinate, businessCluster.getCentroid().getCoordinate());
            if (distanceBetweenCoordinatesAndBase < distanceToClosestBase) {
                closestBase = businessCluster;
                distanceToClosestBase = distanceBetweenCoordinatesAndBase;
            }
        }
        return closestBase;
    }

    private double deg2rad(double deg) {
        return deg * PI / 180.0;
    }

    private double rad2deg(double rad) {
        return rad * 180.0 / PI;
    }

}
