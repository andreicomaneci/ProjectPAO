package serviceCSV;

import model.City;
import model.GoodsTransportOrder;
import model.Information;
import model.PeopleTransportOrder;

import java.util.List;

public class PeopleTransportOrderReadWriteService {
    private String fileName = "src/dataFiles/People.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getOrders() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //int numberOfPeople, City departure, City destination
        for (List<String> record : records) {
            Integer numberOfPeople = Integer.parseInt(record.get(0));
            City departure = info.getCity(record.get(1));
            City destination = info.getCity(record.get(2));
            PeopleTransportOrder order = new PeopleTransportOrder(numberOfPeople, departure, destination);
            info.addOrder(order);
        }
    }
}
