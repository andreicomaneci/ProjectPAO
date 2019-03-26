package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Information {
    private static Information information;

    private static List<Road> roads = new LinkedList<>();
    private static List<Vehicle> vehicles = new LinkedList<>();
    private static List<City> cities = new LinkedList<>();
    private static List<Route> routes = new LinkedList<>();
    private static List<Order> orders = new LinkedList<>();
    private Map<FuelType, Double> fuelCost = new HashMap<>();
    private City mainOfficeLocation = new City("Buftea", "Romania");

    private Information() {
        cities.add(mainOfficeLocation);
    }

    public static Information getInformation() {
        if (information == null)
            information = new Information();
        return information;
    }

    public void addRoad(Road road) {
        roads.add(road);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public City getCity(String cityName) {
        for (City city : cities) {
            if (city.getName().equals(cityName) == true)
                return city;
        }
        return null;
    }

    public Vehicle getVehicle(String registrationNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegistrationNumber().equals(registrationNumber) == true)
                return vehicle;
        }
        return null;
    }

    public Road getRoadBetweenCities(City city1, City city2) {
        for (Road road : roads) {
            if ((road.getDeparture().equals(city1) == true) && (road.getDestination().equals(city2) == true))
                return road;
            if ((road.getDeparture().equals(city2) == true) && (road.getDestination().equals(city1) == true))
                return road;
        }
        return null;
    }

    public Road getRoadBetweenCities(String cityName1, String cityName2) {
        return getRoadBetweenCities(getCity(cityName1), getCity(cityName2));
    }

    public double getFuelCost(FuelType fuel) {
        return fuelCost.get(fuel);
    }

    public double getFuelCost(String fuelName) {
        if (fuelName.equalsIgnoreCase("Diesel"))
            return getFuelCost(FuelType.DIESEL);
        else
            return getFuelCost(FuelType.PETROL);
    }

    public void setFuelCost(FuelType fuel, double cost) {
        fuelCost.put(fuel, cost);
    }

    public void setFuelCost(String fuelName, double cost) {
        if (fuelName.equalsIgnoreCase("Diesel"))
            setFuelCost(FuelType.DIESEL, cost);
        else
            setFuelCost(FuelType.PETROL, cost);
    }

    public List<Road> getRoads() {
        return roads;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public City getMainCity() {
        return mainOfficeLocation;
    }
}
