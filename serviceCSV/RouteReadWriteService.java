package serviceCSV;

import model.City;
import model.Road;
import model.Route;
import model.Information;

import java.util.ArrayList;
import java.util.List;

public class RouteReadWriteService {

    private String fileName = "src/dataFiles/Route.csv";
    private String outfName = "src/dataFiles/RoutePrint.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getRoutes() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //String name, String country
        for (List<String> record : records) {
            String[] listOfCityNames = record.toArray(new String[0]);
            Route route = new Route();
            for (int i = 1; i < listOfCityNames.length; ++i) {
                Road road = info.getRoadBetweenCities(listOfCityNames[i - 1], listOfCityNames[i]);
                route.addRoad(road);
            }
            info.addRoute(route);
        }
    }

}
