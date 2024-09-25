package test;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Clustering {

    static List<BusinessCluster> clusterBusinesses(List<Business> businesses, int minOrdersForBase, GeometryFactory geometryFactory) {
        // List to store final clusters
        List<BusinessCluster> finalClusters = new ArrayList<>();

        // Priority queue to help find the closest business to a cluster
        PriorityQueue<Business> businessQueue = new PriorityQueue<>(Comparator.comparingDouble(b -> b.getCoordinate().x + b.getCoordinate().y));

        // Add all businesses to the queue
        businessQueue.addAll(businesses);

        // While there are businesses left to cluster
        while (!businessQueue.isEmpty()) {
            BusinessCluster currentCluster = new BusinessCluster();

            // Continue adding businesses until we meet or exceed the minimum order requirement
            while (currentCluster.getTotalOrders() < minOrdersForBase && !businessQueue.isEmpty()) {
                Business closestBusiness = businessQueue.poll();
                currentCluster.addBusiness(closestBusiness, geometryFactory);

                // Remove the closest business to prevent duplicates
                businessQueue.removeIf(business -> business.equals(closestBusiness));
            }

            finalClusters.add(currentCluster);
        }

        // Ensure all clusters meet the minimum order requirement, merging small clusters if necessary
        return mergeSmallClusters(finalClusters, minOrdersForBase, geometryFactory);
    }

    private static List<BusinessCluster> mergeSmallClusters(List<BusinessCluster> clusters, int minOrdersForBase, GeometryFactory geometryFactory) {
        List<BusinessCluster> mergedClusters = new ArrayList<>();

        for (BusinessCluster cluster : clusters) {
            if (cluster.getTotalOrders() < minOrdersForBase) {
                BusinessCluster nearestCluster = findNearestCluster(cluster, mergedClusters);
                if (nearestCluster != null)
                    cluster.getBusinesses().forEach(business -> nearestCluster.addBusiness(business, geometryFactory));
                else
                    mergedClusters.add(cluster);
            } else {
                mergedClusters.add(cluster);
            }
        }

        return mergedClusters;
    }

    private static BusinessCluster findNearestCluster(BusinessCluster cluster, List<BusinessCluster> clusters) {
        BusinessCluster nearestCluster = null;
        double nearestDistance = Double.MAX_VALUE;

        Point clusterCentroid = cluster.getCentroid();

        for (BusinessCluster otherCluster : clusters) {
            if (otherCluster != cluster) {
                Point otherCentroid = otherCluster.getCentroid();
                double distance = clusterCentroid.distance(otherCentroid);

                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearestCluster = otherCluster;
                }
            }
        }

        return nearestCluster;
    }


}
