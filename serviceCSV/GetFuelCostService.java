package serviceCSV;

import model.City;
import model.Information;

import java.util.List;

public class GetFuelCostService {
    private String fileName = "src/dataFiles/Fuel.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getCities() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //String name, String country
        for (List<String> record : records) {
            String name = record.get(0);
            String country = record.get(1);
            City city = new City(name, country);
            info.addCity(city);
        }
    }
}
