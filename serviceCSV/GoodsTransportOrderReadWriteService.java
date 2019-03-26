package serviceCSV;

import model.City;
import model.GoodsTransportOrder;
import model.Information;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class GoodsTransportOrderReadWriteService {

    private String fileName = "src/dataFiles/Goods.csv";
    private String outfName = "src/dataFiles/GoodsPrint.csv";
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

    public void printOrders() {
        List<GoodsTransportOrder> orders = new ArrayList<>();
        for (Order order : info.getOrders()) {
            if (order instanceof GoodsTransportOrder)
                orders.add((GoodsTransportOrder) order);
        }
        List< List<String> > data = new ArrayList<>();
        for (GoodsTransportOrder order : orders) {
            List<String> record = new ArrayList<>();
            record.add(order.getDeliveryPlace().getName());
            record.add(String.valueOf(order.getWeight()));
            record.add(String.valueOf(order.getVolume()));
            data.add(record);
        }
        readWriteService.writeTo(outfName, data);
    }
}
