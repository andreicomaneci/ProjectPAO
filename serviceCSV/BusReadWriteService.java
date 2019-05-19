package serviceCSV;

import model.FuelType;
import model.Information;
import model.Bus;
import model.Vehicle;

import java.util.*;

public class BusReadWriteService {

    private String fileName = "src/dataFiles/Bus.csv";
    private String outfName = "src/dataFiles/BusPrint.csv";
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
                fuelType = FuelType.Diesel;
            else
                fuelType = FuelType.Petrol;
            Integer numberOfSeats = Integer.parseInt(record.get(4));
            Double averageConsumption = Double.parseDouble(record.get(5));
            Bus bus = new Bus(manufacturer, model, registrationNumber, fuelType, numberOfSeats, averageConsumption);
            info.addVehicle(bus);
        }
    }

    public void printBuses() {
        List<Bus> buses = new ArrayList<>();
        for (Vehicle car : info.getVehicles()) {
            if (car instanceof Bus)
                buses.add((Bus)car);
        }
        List< List<String> > data = new ArrayList<>();
        for (Bus bus : buses) {
            List<String> record = new ArrayList<>();
            record.add(bus.getManufacturer());
            record.add(bus.getModel());
            record.add(bus.getRegistrationNumber());
            String fuelName;
            if (bus.getFuelType().equals(FuelType.Diesel))
                fuelName = "Diesel";
            else
                fuelName = "Petrol";
            record.add(fuelName);
            record.add(Integer.toString(bus.getNumberOfSeats()));
            record.add(String.valueOf(bus.getAverageConsumption()));
            data.add(record);
        }
        readWriteService.writeTo(outfName, data);
    }
}