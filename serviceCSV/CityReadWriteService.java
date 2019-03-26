package serviceCSV;

import model.City;
import model.FuelType;
import model.Information;
import model.Road;
import java.util.*;

public class CityReadWriteService {

    private String fileName = "src/dataFiles/City.csv";
    private String outfName = "src/dataFiles/CityPrint.csv";
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

    public void printCities() {
        List<City> cities = info.getCities();
        List< List<String> > data = new ArrayList<>();
        for (City city : cities) {
            List<String> record = new ArrayList<>();
            record.add(city.getName());
            record.add(city.getCountry());
            data.add(record);
        }
        readWriteService.writeTo(outfName, data);
    }
}