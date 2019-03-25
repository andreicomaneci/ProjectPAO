package service;

import model.*;

import java.util.LinkedList;
import java.util.List;

public class OrderService {

    private Information info = Information.getInformation();
    private RouteOptimizationService ros = new RouteOptimizationService();

    public Route assignOrder(PeopleTransportOrder order) {
        Route route = ros.shortestRoute(order.getDeparture(), order.getDestination(), info.getRoads());
        order.setAssignedRoute(route);
        int notYetAssignedPeople = order.getNumberOfPeople();
        for (Vehicle vehicle : info.getVehicles()) {
            if (vehicle instanceof Bus) {
                notYetAssignedPeople -= ((Bus)vehicle).getNumberOfSeats();
                order.addVehicle(vehicle);
                if (notYetAssignedPeople <= 0)
                    break;
            }
        }
        return route;
    }

    public Route assignOrder(GoodsTransportOrder order) {
        Route route = ros.shortestRoute(info.getMainCity(), order.getDeliveryPlace(), info.getRoads());
        order.setAssignedRoute(route);
        double notYetAssignedSpace = order.getVolume();
        for (Vehicle vehicle : info.getVehicles()) {
            if (vehicle instanceof Truck) {
                notYetAssignedSpace -= ((Truck)vehicle).getCapacity();
                order.addVehicle(vehicle);
                if (notYetAssignedSpace <= 0)
                    break;
            }
            if (vehicle instanceof Van) {
                notYetAssignedSpace -= ((Van)vehicle).getCapacity();
                order.addVehicle(vehicle);
                if (notYetAssignedSpace <= 0)
                    break;
            }
        }
        return route;
    }

    public Route assignOrder(Order order) {
        if (order instanceof GoodsTransportOrder)
            return assignOrder((GoodsTransportOrder)order);
        else
            return assignOrder((PeopleTransportOrder)order);
    }

    public double getRouteCost(Route route) {
        double cost = 0.0;
        for (Road road : route.getList()) {
            cost += road.getTax();
        }
        return cost;
    }

    public double getRouteCost(Vehicle vehicle, Route route) {
        double cost = getRouteCost(route);
        for (Road road : route.getList()) {
            cost += getFuelCost(vehicle, road);
        }
        return cost;
    }

    public double getFuelCost(Vehicle vehicle, Road road) {
        double cost = vehicle.getAverageConsumption() / 100 * info.getFuelCost(vehicle.getFuelType()) * road.getLength();
        return cost;
    }

    public double getOrderCost(Order order) {
        double cost = 0.0;
        for (Vehicle vehicle : order.getAssignedVehicles()) {
            cost += getRouteCost(vehicle, order.getAssignedRoute());
        }
        return cost;
    }

}
