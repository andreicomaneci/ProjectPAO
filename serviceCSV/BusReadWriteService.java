package serviceCSV;

import model.FuelType;
import model.Information;
import model.Bus;
import java.util.*;

public class BusReadWriteService {

    private String fileName = "src/dataFiles/Bus.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getBuses() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //String manufacturer, String model, String registrationNumber, FuelType fuelType, int numberOfSeats, double averageConsumption
        for (List<String> record : records) {
            String manufacturer = record.get(0);
            String model = record.get(1);
            String registrationNumber = record.get(2);
            String fuelTypeString = record.get(3);
            FuelType fuelType;
            if (fuelTypeString.equalsIgnoreCase("Diesel"))
                fuelType = FuelType.DIESEL;
            else
                fuelType = FuelType.PETROL;
            Integer numberOfSeats = Integer.parseInt(record.get(4));
            Double averageConsumption = Double.parseDouble(record.get(5));
            Bus bus = new Bus(manufacturer, model, registrationNumber, fuelType, numberOfSeats, averageConsumption);
            info.addVehicle(bus);
        }
    }
}