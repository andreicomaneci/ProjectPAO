package serviceCSV;

import model.FuelType;
import model.Information;
import model.Truck;
import model.Vehicle;

import java.util.*;

public class TruckReadWriteService {

    private String fileName = "src/dataFiles/Truck.csv";
    private String outfName = "src/dataFiles/TruckPrint.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getTrucks() {
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
            Double maximuDepositWeight = Double.parseDouble(record.get(4));
            Double capacity = Double.parseDouble(record.get(5));
            Double averageConsumption = Double.parseDouble(record.get(6));
            Truck truck = new Truck(manufacturer, model, registrationNumber, fuelType, maximuDepositWeight, capacity, averageConsumption);
            info.addVehicle(truck);
        }
    }

    public void printTrucks() {
        List<Truck> trucks = new ArrayList<>();
        for (Vehicle car : info.getVehicles()) {
            if (car instanceof Truck)
                trucks.add((Truck)car);
        }
        List< List<String> > data = new ArrayList<>();
        for (Truck truck : trucks) {
            List<String> record = new ArrayList<>();
            record.add(truck.getManufacturer());
            record.add(truck.getModel());
            record.add(truck.getRegistrationNumber());
            String fuelName;
            if (truck.getFuelType().equals(FuelType.Diesel))
                fuelName = "Diesel";
            else
                fuelName = "Petrol";
            record.add(fuelName);
            record.add(String.valueOf(truck.getMaximumDepositWeight()));
            record.add(String.valueOf(truck.getCapacity()));
            record.add(String.valueOf(truck.getAverageConsumption()));
            data.add(record);
        }
        readWriteService.writeTo(outfName, data);
    }
}
