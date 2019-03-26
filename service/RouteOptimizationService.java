package service;

import model.*;

import java.util.*;

public class RouteOptimizationService {

    private boolean isIncidentTo(City city, Road road) {
        return road.getDeparture().equals(city) || road.getDestination().equals(city);
    }

    public Route shortestRoute(City departure, City destination, List<Road> roads) {

        Map<City, Double> distanceMap = new HashMap<>();
        Map<City, Road> bestBefore = new HashMap<>();
        bestBefore.put(departure, null);
        distanceMap.put(departure, 0.0);
        //distanceMap.put(destination, Double.POSITIVE_INFINITY);
        LinkedList<City> queue = new LinkedList<>();
        queue.add(departure);
        while (queue.isEmpty() == false) {
            City bestElement;
            bestElement = queue.get(0);
            double bestDistance = distanceMap.get(bestElement);
            for (City itCity : queue) {
                double itDistance = distanceMap.get(itCity);
                if (itDistance < bestDistance) {
                    bestElement = itCity;
                    bestDistance = itDistance;
                }
            }
            queue.remove(queue.indexOf(bestElement));
            for (Road road : roads) {
                if (isIncidentTo(bestElement, road) == true) {
                    City nextElement;
                    if (road.getDeparture().equals(bestElement) == true)
                        nextElement = road.getDestination();
                    else
                        nextElement = road.getDeparture();
                    if (distanceMap.containsKey(nextElement) == true) {
                        double currentDistance = distanceMap.get(nextElement);
                        double possibleBest = bestDistance + road.getLength();
                        if (currentDistance > possibleBest) {
                            distanceMap.put(nextElement, possibleBest);
                            bestBefore.put(nextElement, road);
                        }
                    }
                    else {
                        queue.add(nextElement);
                        distanceMap.put(nextElement, bestDistance + road.getLength());
                        bestBefore.put(nextElement, road);
                    }
                }
            }
        }
        List<Road> bestRoute = new ArrayList<>();
        if (distanceMap.containsKey(destination) == false)
            return null;
        else {
            City currentCity = destination;
            Road currentRoad = bestBefore.get(currentCity);
            do {
                bestRoute.add(0, currentRoad);
                if (currentRoad.getDeparture().equals(currentCity) == true)
                    currentCity = currentRoad.getDestination();
                else
                    currentCity = currentRoad.getDeparture();
                currentRoad = bestBefore.get(currentCity);
            } while (currentRoad != null);
        }
        Route route = new Route(bestRoute);
        return route;
    }
}
