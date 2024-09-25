package test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Coordinate;

@Getter
@Setter
@AllArgsConstructor
class Business {

    private Coordinate coordinate;
    private int numberOfOrders;
    private String externalId;
    private boolean isResolved;

    double getDistanceBetweenCoordinates(Coordinate coordinate) {
        return DistanceUtils.getDistance(this.coordinate, coordinate);
    }

}
