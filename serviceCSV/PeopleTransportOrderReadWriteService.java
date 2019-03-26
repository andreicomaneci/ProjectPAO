package serviceCSV;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class PeopleTransportOrderReadWriteService {
    private String fileName = "src/dataFiles/People.csv";
    private String outfName = "src/dataFiles/PeoplePrint.csv";
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

    public void printOrders() {
        List<PeopleTransportOrder> orders = new ArrayList<>();
        for (Order order : info.getOrders()) {
            if (order instanceof PeopleTransportOrder)
                orders.add((PeopleTransportOrder) order);
        }
        List< List<String> > data = new ArrayList<>();
        for (PeopleTransportOrder order : orders) {
            List<String> record = new ArrayList<>();
            record.add(String.valueOf(order.getNumberOfPeople()));
            record.add(order.getDeparture().getName());
            record.add(order.getDestination().getName());
            data.add(record);
        }
        readWriteService.writeTo(outfName, data);
    }
}
