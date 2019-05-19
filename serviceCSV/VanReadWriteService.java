package serviceCSV;

import model.FuelType;
import model.Information;
import model.Van;
import model.Vehicle;

import java.util.*;

public class VanReadWriteService {

    private String fileName = "src/dataFiles/Van.csv";
    private String outfName = "src/dataFiles/VanPrint.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getVans() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //String manufacturer, String model, String registrationNumber, FuelType fuelType, double maximumDepositWeight, double capacity, double averageConsumption
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
            Double maximumDepositWeight = Double.parseDouble(record.get(4));
            Double capacity = Double.parseDouble(record.get(5));
            Double averageConsumption = Double.parseDouble(record.get(6));
            Van van = new Van(manufacturer, model, registrationNumber, fuelType, maximumDepositWeight, capacity, averageConsumption);
            info.addVehicle(van);
        }
    }

    public void printVans() {
        List<Van> vans = new ArrayList<>();
        for (Vehicle car : info.getVehicles()) {
            if (car instanceof Van)
                vans.add((Van)car);
        }
        List< List<String> > data = new ArrayList<>();
        for (Van van : vans) {
            List<String> record = new ArrayList<>();
            record.add(van.getManufacturer());
            record.add(van.getModel());
            record.add(van.getRegistrationNumber());
            String fuelName;
            if (van.getFuelType().equals(FuelType.Diesel))
                fuelName = "Diesel";
            else
                fuelName = "Petrol";
            record.add(fuelName);
            record.add(String.valueOf(van.getMaximumDepositWeight()));
            record.add(String.valueOf(van.getCapacity()));
            record.add(String.valueOf(van.getAverageConsumption()));
            data.add(record);
        }
        readWriteService.writeTo(outfName, data);
    }
}