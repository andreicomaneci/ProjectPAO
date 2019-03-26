package serviceCSV;

import model.City;
import model.Information;

import java.util.List;

public class GetFuelCostService {
    private String fileName = "src/dataFiles/Fuel.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getFuel() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //String name, String country
        for (List<String> record : records) {
            String fuelName = record.get(0);
            Double cost = Double.parseDouble(record.get(1));
            info.setFuelCost(fuelName, cost);
        }
    }
}
