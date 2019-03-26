package serviceCSV;

import model.City;
import model.FuelType;
import model.Information;
import model.Road;
import java.util.*;

public class RoadReadWriteService {

    private String fileName = "src/dataFiles/Road.csv";
    private String outfName = "src/dataFiles/RoadPrint.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getRoads() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //City departure, City destination, String roadNumber, double length, int tax, int speedLimit
        for (List<String> record : records) {
            String departureName = record.get(0);
            String destinationName = record.get(1);
            String roadNumber = record.get(2);
            Double length = Double.parseDouble(record.get(3));
            Integer tax = Integer.parseInt(record.get(4));
            Integer speedLimit = Integer.parseInt(record.get(5));
            City departure = info.getCity(departureName);
            City destination = info.getCity(destinationName);
            Road road = new Road(departure, destination, roadNumber, length, tax, speedLimit);
            info.addRoad(road);
        }
    }

    public void printRoads() {
        List<Road> roads = info.getRoads();
        List< List<String> > data = new ArrayList<>();
        for (Road road : roads) {
            List<String> record = new ArrayList<>();
            record.add(road.getDeparture().getName());
            record.add(road.getDestination().getName());
            record.add(road.getRoadNumber());
            record.add(String.valueOf(road.getLength()));
            record.add(String.valueOf(road.getTax()));
            record.add(String.valueOf(road.getSpeedLimit()));
            data.add(record);
        }
        readWriteService.writeTo(outfName, data);
    }
}