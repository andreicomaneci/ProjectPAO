package serviceCSV;

import model.FuelType;
import model.Information;
import model.Truck;
import java.util.*;

public class TruckReadWriteService {

    private String fileName = "src/dataFiles/Truck.csv";
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
                    fuelType = FuelType.DIESEL;
                else
                    fuelType = FuelType.PETROL;
                Double maximuDepositWeight = Double.parseDouble(record.get(4));
                Double capacity = Double.parseDouble(record.get(5));
                Double averageConsumption = Double.parseDouble(record.get(6));
                Truck truck = new Truck(manufacturer, model, registrationNumber, fuelType, maximuDepositWeight, capacity, averageConsumption);
                info.addVehicle(truck);
            }
    }
}
