package graphicalInterface;

import model.*;
import repositories.BusRepository;
import repositories.TruckRepository;
import repositories.VanRepository;
import serviceCSV.BusReadWriteService;
import serviceCSV.TruckReadWriteService;
import serviceCSV.VanReadWriteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class VehiclesPanel extends JPanel {

    String[] columnHead = {"Manufacturer", "Model", "Registration Number"};

    private JPanel checkBoxPanel = new JPanel();

    private JCheckBox busCB = new JCheckBox("Bus");
    private JCheckBox vanCB = new JCheckBox("Van");
    private JCheckBox truckCB = new JCheckBox("Truck");

    private JTable busTable = new JTable(new String[0][3], columnHead);
    private JTable vanTable = new JTable(new String[0][3], columnHead);
    private JTable truckTable = new JTable(new String[0][3], columnHead);

    private JScrollPane busSP = new JScrollPane();
    private JScrollPane vanSP = new JScrollPane();
    private JScrollPane truckSP = new JScrollPane();

//    private Information info = Information.getInformation();

    private BusRepository busRepository = new BusRepository();
    private VanRepository vanRepository = new VanRepository();
    private TruckRepository truckRepository = new TruckRepository();

    public VehiclesPanel() {

        setLayout(new GridLayout(0,1));
        busCB.addItemListener(new CustomItemListener());
        vanCB.addItemListener(new CustomItemListener());
        truckCB.addItemListener(new CustomItemListener());

        BusReadWriteService busReader = new BusReadWriteService();
        VanReadWriteService vanReader = new VanReadWriteService();
        TruckReadWriteService truckReader = new TruckReadWriteService();
        busReader.getBuses();
        vanReader.getVans();
        truckReader.getTrucks();

        busSP.setVisible(false);
        vanSP.setVisible(false);
        truckSP.setVisible(false);

        checkBoxPanel.add(busCB);
        checkBoxPanel.add(truckCB);
        checkBoxPanel.add(vanCB);

        busSP.getViewport().add(busTable);
        vanSP.getViewport().add(vanTable);
        truckSP.getViewport().add(truckTable);

        add(checkBoxPanel);
        add(busSP);
        add(truckSP);
        add(vanSP);

    }

    void listVehicles(String type) {
        switch (type) {
            case "Bus": {
//                List<Bus> buses = info.getBuses();
                List<Bus> buses = busRepository.readBuses();
                String[][] busInfo = new String[buses.size()][3];
                int index = 0;
                for (Bus bus : buses) {
                    busInfo[index] = new String[]{bus.getManufacturer(), bus.getModel(), bus.getRegistrationNumber()};
                    ++index;
                }
                busTable = new JTable(busInfo, columnHead);
                busSP.getViewport().add(busTable);
                busSP.setVisible(true);
            }
                break;
            case "Truck": {
//                List<Truck> trucks = info.getTrucks();
                List<Truck> trucks = truckRepository.readTrucks();
                String[][] truckInfo = new String[trucks.size()][3];
                int index = 0;
                for (Truck truck : trucks) {
                    truckInfo[index] = new String[]{truck.getManufacturer(), truck.getModel(), truck.getRegistrationNumber()};
                    ++index;
                }
                truckTable = new JTable(truckInfo, columnHead);
                truckSP.getViewport().add(truckTable);
                truckSP.setVisible(true);
            }
                break;
            case "Van": {
//                List<Van> vans = info.getVans();
                List<Van> vans = vanRepository.readVans();
                String[][] vanInfo = new String[vans.size()][3];
                int index = 0;
                for (Van van : vans) {
                    vanInfo[index] = new String[]{van.getManufacturer(), van.getModel(), van.getRegistrationNumber()};
                    ++index;
                }
                vanTable = new JTable(vanInfo, columnHead);
                vanSP.getViewport().add(vanTable);
                vanSP.setVisible(true);
            }
                break;
        }

    }

    class CustomItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent ie) {
            JCheckBox cb = (JCheckBox)ie.getItem();
            String type = cb.getText();

            if (cb.isSelected()) {
                listVehicles(type);
            } else {
                switch (type) {
                    case "Bus":
                        busSP.setVisible(false);
                        break;
                    case "Truck":
                        truckSP.setVisible(false);
                        break;
                    case "Van":
                        vanSP.setVisible(false);
                        break;
                }
            }
        }
    }
}
