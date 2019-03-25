package serviceCSV;

import model.City;
import model.GoodsTransportOrder;
import model.Information;

import java.util.List;

public class GoodsTransportOrderReadWriteService {

    private String fileName = "src/dataFiles/Goods.csv";
    private ReadWriteService readWriteService = new ReadWriteService();
    private Information info = Information.getInformation();

    public void getOrders() {
        List<List<String>> records = readWriteService.readFrom(fileName);

        //City deliveryPlace, double weight, double volume
        for (List<String> record : records) {
            String cityName = record.get(0);
            City city = info.getCity(cityName);
            Double weight = Double.parseDouble(record.get(1));
            Double volume = Double.parseDouble(record.get(2));
            GoodsTransportOrder order = new GoodsTransportOrder(city, weight, volume);
            info.addOrder(order);
        }
    }
}
