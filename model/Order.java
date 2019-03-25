package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private String orderCode;
    private Route assignedRoute;
    private List<Vehicle> assignedVehicles = new ArrayList<>();

    public Order() {
        this.orderCode = UUID.randomUUID().toString();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Route getAssignedRoute() {
        return assignedRoute;
    }

    public void setAssignedRoute(Route assignedRoute) {
        this.assignedRoute = assignedRoute;
    }

    public List<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }

    public void setAssignedVehicles(List<Vehicle> assignedVehicles) {
        this.assignedVehicles = assignedVehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        this.assignedVehicles.add(vehicle);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode='" + orderCode + '\'' +
                ", assignedRoute=" + assignedRoute +
                ", assignedVehicles=" + assignedVehicles +
                '}';
    }
}
