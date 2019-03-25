package service;

import model.*;

import java.util.*;

public class RouteOptimizationService {

    /*private class Graph{

        private Map<City, Node> nodes = new HashMap<>();

        public void addNode(Node node) {
            nodes.put(node.getLabel(), node);
        }

        public void addEdge(Node node1, Node node2) {
            node1.addAdjacentNode(node2, 0.0);
            node2.addAdjacentNode(node1, 0.0);
        }

        public void addEdge(Node node1, Node node2, double cost) {
            node1.addAdjacentNode(node2, cost);
            node2.addAdjacentNode(node1, cost);
        }

        public Map<City, Node> getNodes() {
            return nodes;
        }

        public void setNodes(Map<City, Node> nodes) {
            this.nodes = nodes;
        }

    }

    private class Node{

        private City label;

        Map <Node, Double> adjacentNodes = new HashMap<>();

        public Node(City label) {
            this.label = label;
        }

        public void addAdjacentNode(Node node, double cost) {
            adjacentNodes.put(node, cost);
        }

        public City getLabel() {
            return label;
        }

        public void setLabel(City label) {
            this.label = label;
        }

        public Map<Node, Double> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
            this.adjacentNodes = adjacentNodes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(label, node.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }
    }

    private Map<City, Node> getCities(List<Road> roads) {

        Map<City, Node> cities = new HashMap<>();
        for (Road road : roads) {
            City city1 = road.getDeparture();
            City city2 = road.getDestination();
            if (cities.containsKey(city1) == false) {
                Node node = new Node(city1);
                cities.put(city1, node);
            }
            if (cities.containsKey(city2) == false) {
                Node node = new Node(city2);
                cities.put(city2, node);
            }
        }
        return cities;
    }

    private Graph turnNetworkToGraph(List<Road> roads) {

        Graph graph = new Graph();

        Map<City, Node> cities = getCities(roads);

        for (City city : cities.keySet()) {
            graph.addNode(cities.get(city));
        }

        for (Road road : roads) {
            graph.addEdge(cities.get(road.getDeparture()), cities.get(road.getDestination()), road.getLength());
        }

        return graph;
    }

    private List<Road> dijkstra(Graph graph, Node source, Node destination) {

        return null;
    }*/

    private boolean isIncidentTo(City city, Road road) {
        return road.getDeparture().equals(city) || road.getDestination().equals(city);
    }

    public Route shortestRoute(City departure, City destination, List<Road> roads) {

        /*Graph graph = turnNetworkToGraph(roads);
        Node sourceNode = graph.getNodes().get(departure);
        Node destinationNode = graph.getNodes().get(destination);
        Route bestRoute = new Route(dijkstra(graph, sourceNode, destinationNode));
        return bestRoute;*/
        Map<City, Double> distanceMap = new HashMap<>();
        Map<City, Road> bestBefore = new HashMap<>();
        bestBefore.put(departure, null);
        distanceMap.put(departure, 0.0);
        //distanceMap.put(destination, Double.POSITIVE_INFINITY);
        LinkedList<City> queue = new LinkedList<>();
        queue.add(departure);
        while (queue.isEmpty() == false) {
            City bestElement;
            //City[] queuedCities = queue.toArray(new City[0]);
            bestElement = queue.get(0);
            //queue.remove(0);
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
