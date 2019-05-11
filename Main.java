import model.*;
import service.OrderService;
import serviceCSV.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle v1 = new Truck("DAF", "XF", "KX63KXH", FuelType.DIESEL, 13000,96270, 34);
        Vehicle v2 = new Truck("Volvo", "FH16", "L922BCU", FuelType.DIESEL, 14230, 88850, 33.25);
        Vehicle v3 = new Van("Mercedes-Benz", "Sprinter", "BB35099", FuelType.PETROL, 1360, 10500, 11.4);
        Vehicle v4 = new Van("Renault", "Master", "JX83WXF", FuelType.PETROL, 4500, 12000, 12.8);
        Vehicle v5 = new Bus("MCV", "Evora", "BN68XTE", FuelType.DIESEL, 46, 27);
        Information info = Information.getInformation();
        info.addVehicle(v1);
        info.addVehicle(v2);
        info.addVehicle(v3);
        info.addVehicle(v4);
        info.addVehicle(v5);
        System.out.println(v1);

        System.out.println(info.getMainCity());
        BusReadWriteService busReader = new BusReadWriteService();
        VanReadWriteService vanReader = new VanReadWriteService();
        TruckReadWriteService truckReader = new TruckReadWriteService();
        CityReadWriteService cityReader = new CityReadWriteService();
        RouteReadWriteService routeReader = new RouteReadWriteService();
        RoadReadWriteService roadReader = new RoadReadWriteService();
        PeopleTransportOrderReadWriteService peopleReader = new PeopleTransportOrderReadWriteService();
        GoodsTransportOrderReadWriteService goodReader = new GoodsTransportOrderReadWriteService();
        GetFuelCostService fuelReader = new GetFuelCostService();

        busReader.getBuses();
        vanReader.getVans();
        truckReader.getTrucks();
        cityReader.getCities();
        roadReader.getRoads();
        routeReader.getRoutes();
        peopleReader.getOrders();
        goodReader.getOrders();
        fuelReader.getFuel();
        System.out.println("\nThe list of all vehicles\n---");
        for (Vehicle car : info.getVehicles()) {
            //System.out.print(car.getClass() + " | ");
            System.out.println(car);
        }
        System.out.println("\nThe list of all cities\n---");
        for (City city : info.getCities()) {
            System.out.println(city);
        }
        System.out.println("\nThe list of all roads\n---");
        for (Road road: info.getRoads()) {
            System.out.println(road);
        }
        System.out.println("\nThe list of all routes\n---");
        for (Route route : info.getRoutes()) {
            System.out.println(route);
        }
        System.out.println("\nThe list of all orders\n---");
        for (Order order : info.getOrders()) {
            System.out.println(order);
            OrderService stuffDoer = new OrderService();
            Route route = stuffDoer.assignOrder(order);
            System.out.println(route);
            System.out.println(stuffDoer.getRouteCost(route));
            System.out.println(stuffDoer.getOrderCost(order));
        }
        /*OrderService stuffDoer = new OrderService();
        List<Bus> bestBuses = stuffDoer.bestBusesWithLeastNumberOfSeats(30);
        System.out.println(bestBuses.get(0));*/
        busReader.printBuses();
    }
}
