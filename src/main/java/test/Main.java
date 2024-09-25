package test;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int MIN_ORDERS_FOR_BASE = 4;

    public static void main(String[] args) {

        GeometryFactory geometryFactory = new GeometryFactory();

//        Business A = new Business(new Coordinate(45.76389220278282, 22.907282255017122), 3, "A", false);
//        Business B = new Business(new Coordinate(45.763328720606275, 22.909649426109382), 2, "B", false);
//        Business C = new Business(new Coordinate(45.76282980723513, 22.910972591887376), 2, "C", false);
//        Business D = new Business(new Coordinate(45.761656697991164, 22.912969171470984), 2, "D", false);
//        Business E = new Business(new Coordinate(45.760260727837334, 22.911326502726403), 2, "E", false);
//        Business F = new Business(new Coordinate(45.7579361663388, 22.907660375810725), 1, "F", false);


        Business A = new Business(new Coordinate(47.06993805182788, 21.935882113764716), 4, "A", false);
        Business B = new Business(new Coordinate(47.0670633816867, 21.936090872111766), 1, "B", false);
        Business C = new Business(new Coordinate(47.06039475866779, 21.93935185901848), 2, "C", false);
        Business D = new Business(new Coordinate(47.065530016932314, 21.94918144947595), 2, "D", false);
        Business E = new Business(new Coordinate(47.0696269460882, 21.93155353218553), 1, "E", false);

        List<BusinessCluster> businessClusters = Clustering.clusterBusinesses(List.of(A, B, C, D, E), MIN_ORDERS_FOR_BASE, geometryFactory);
        double totalDistance = DistanceUtils.calculateTotalDistance(businessClusters);
        System.out.println(totalDistance);
        for (BusinessCluster businessCluster : businessClusters) {
            while (businessCluster.hasUnresolvedBusinesses()) {
                Business business = businessCluster.getUnresolvedBusiness();
                if (businessCluster.canLeave(business, MIN_ORDERS_FOR_BASE)) {
                    List<BusinessCluster> clustersToIgnore = new ArrayList<>(List.of(businessCluster));
                    BusinessCluster nearestOtherCluster = DistanceUtils.getNearestOtherCluster(business.getCoordinate(), businessClusters, clustersToIgnore);
                    if (nearestOtherCluster == null)
                        break;
                    while (nearestOtherCluster != null) {
                        nearestOtherCluster.addBusiness(business, geometryFactory);
                        businessCluster.removeBusiness(business, geometryFactory);
                        double newTotalDistance = DistanceUtils.calculateTotalDistance(businessClusters);
                        if (newTotalDistance < totalDistance) {
                            totalDistance = newTotalDistance;
                        } else {
                            nearestOtherCluster.removeBusiness(business, geometryFactory);
                            businessCluster.addBusiness(business, geometryFactory);
                        }
                        business.setResolved(true);
                        clustersToIgnore.add(nearestOtherCluster);
                        nearestOtherCluster = DistanceUtils.getNearestOtherCluster(business.getCoordinate(), businessClusters, clustersToIgnore);
                    }
                } else {
                    List<BusinessCluster> clustersToIgnore = new ArrayList<>(List.of(businessCluster));
                    BusinessCluster nearestOtherCluster = DistanceUtils.getNearestOtherCluster(business.getCoordinate(), businessClusters, clustersToIgnore);
                    if (nearestOtherCluster == null)
                        break;
                    while (nearestOtherCluster != null && !business.isResolved()) {
                        List<Business> businessesEligibleForExchangeOrdered = businessCluster.getBusinessesEligibleForExchangeOrdered(business, nearestOtherCluster, MIN_ORDERS_FOR_BASE);
                        if (businessesEligibleForExchangeOrdered.isEmpty()) {
                            business.setResolved(true);
                            break;
                        }
                        while (!businessesEligibleForExchangeOrdered.isEmpty()) {
                            Business businessEligibleForExchange = businessesEligibleForExchangeOrdered.get(0);
                            List<Business> businessesThatCanGo = businessCluster.getBusinessesThatCanGo(business, businessEligibleForExchange.getNumberOfOrders(), MIN_ORDERS_FOR_BASE);
                            if (businessesThatCanGo.isEmpty()) {
                                business.setResolved(true);
                                break;
                            }
                            Business mostFitToGo = null;
                            for (Business businessThatCanGo : businessesThatCanGo) {
                                businessCluster.exchangeBusinesses(businessEligibleForExchange, businessThatCanGo, geometryFactory);
                                nearestOtherCluster.exchangeBusinesses(businessThatCanGo, businessEligibleForExchange, geometryFactory);
                                double newTotalDistance = DistanceUtils.calculateTotalDistance(businessClusters);
                                if (newTotalDistance < totalDistance) {
                                    totalDistance = newTotalDistance;
                                    mostFitToGo = businessThatCanGo;
                                }
                                businessCluster.exchangeBusinesses(businessThatCanGo, businessEligibleForExchange, geometryFactory);
                                nearestOtherCluster.exchangeBusinesses(businessEligibleForExchange, businessThatCanGo, geometryFactory);
                                businessThatCanGo.setResolved(true);
                                businessesEligibleForExchangeOrdered = businessCluster.getBusinessesEligibleForExchangeOrdered(business, nearestOtherCluster, MIN_ORDERS_FOR_BASE);
                            }
                            if (mostFitToGo != null) {
                                businessCluster.exchangeBusinesses(businessEligibleForExchange, mostFitToGo, geometryFactory);
                                nearestOtherCluster.exchangeBusinesses(mostFitToGo, businessEligibleForExchange, geometryFactory);
                                businessEligibleForExchange.setResolved(true);
                                business.setResolved(true);
                            }
                        }
                        clustersToIgnore.add(nearestOtherCluster);
                        nearestOtherCluster = DistanceUtils.getNearestOtherCluster(business.getCoordinate(), businessClusters, clustersToIgnore);
                    }
                }
            }
            businessClusters.forEach(BusinessCluster::resetBusinessesResolvedState);
        }

        System.out.println(totalDistance);

    }

}
