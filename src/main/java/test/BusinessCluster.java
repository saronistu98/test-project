package test;

import lombok.Getter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
class BusinessCluster {

    private final List<Business> businesses = new ArrayList<>();
    private int totalOrders = 0;
    private Point centroid;
    private String name;

    void addBusiness(Business business, GeometryFactory geometryFactory) {
        businesses.add(business);
        totalOrders += business.getNumberOfOrders();
        setCentroid(geometryFactory);
    }

    void removeBusiness(Business business, GeometryFactory geometryFactory) {
        businesses.remove(business);
        totalOrders -= business.getNumberOfOrders();
        setCentroid(geometryFactory);
    }

    private void setCentroid(GeometryFactory geometryFactory) {
        double sumX = 0;
        double sumY = 0;
        for (Business business : businesses) {
            sumX += business.getCoordinate().getX();
            sumY += business.getCoordinate().getY();
        }
        this.centroid = geometryFactory.createPoint(new Coordinate(sumX / businesses.size(), sumY / businesses.size()));
        this.name = businesses.stream().map(Business::getExternalId).collect(Collectors.joining());
    }

    boolean canLeave(Business business, int minimumOrdersForBase) {
        if (business.isResolved())
            return false;
        return totalOrders - business.getNumberOfOrders() >= minimumOrdersForBase;
    }

    List<Business> getBusinessesEligibleForExchangeOrdered(Business business, BusinessCluster nearestOtherCluster, int minimumOrdersForBase) {
        if (nearestOtherCluster.hasOnlyOneBusiness())
            return Collections.emptyList();
        return nearestOtherCluster.getBusinesses().stream()
                .filter(Predicate.not(Business::isResolved))
                .filter(nearestOtherClusterBusiness -> nearestOtherCluster.getTotalOrders() - nearestOtherClusterBusiness.getNumberOfOrders() + business.getNumberOfOrders() >= minimumOrdersForBase &&
                        this.totalOrders - business.getNumberOfOrders() + nearestOtherClusterBusiness.getNumberOfOrders() >= minimumOrdersForBase)
                .sorted(Comparator.comparing(nearestOtherClusterBusiness -> nearestOtherClusterBusiness.getDistanceBetweenCoordinates(business.getCoordinate())))
                .collect(Collectors.toList());
    }

    private boolean hasOnlyOneBusiness() {
        return businesses.size() == 1;
    }

    boolean hasUnresolvedBusinesses() {
        return businesses.stream().anyMatch(Predicate.not(Business::isResolved));
    }

    Business getUnresolvedBusiness() {
        return businesses.stream().filter(Predicate.not(Business::isResolved)).findFirst().get();
    }

    void exchangeBusinesses(Business businessToCome, Business businessToGo, GeometryFactory geometryFactory) {
        businesses.remove(businessToGo);
        totalOrders -= businessToGo.getNumberOfOrders();
        businesses.add(businessToCome);
        totalOrders += businessToCome.getNumberOfOrders();
        setCentroid(geometryFactory);
    }

    List<Business> getBusinessesThatCanGo(Business business, int businessThatIsComingNumberOfOrders, int minimumOrdersForBase) {
        return businesses.stream()
                .filter(b -> b != business)
                .filter(Predicate.not(Business::isResolved))
                .filter(b -> totalOrders - b.getNumberOfOrders() + businessThatIsComingNumberOfOrders >= minimumOrdersForBase)
                .collect(Collectors.toList());
    }

    void resetBusinessesResolvedState() {
        businesses.forEach(business -> business.setResolved(false));
    }

}
