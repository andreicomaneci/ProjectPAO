package graphicalInterface;

import copyrightedClasses.SpringUtilities;
import model.*;
import repositories.BusRepository;
import repositories.TruckRepository;
import repositories.VanRepository;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class VehicleEditorPanel extends JPanel {

    private ButtonGroup vehicleTypesGroup = new ButtonGroup();

    private JRadioButton busButton = new JRadioButton("Bus");
    private JRadioButton vanButton = new JRadioButton("Van");
    private JRadioButton truckButton = new JRadioButton("Truck");

    private JPanel formPanel = new JPanel();
    private JPanel radioButtonPanel = new JPanel();
    private JPanel listPanel = new JPanel();

    private JLabel commLabel = new JLabel();
    private JLabel errorLabel = new JLabel();

    private JLabel manLabel = new JLabel("Manufacturer: ", JLabel.TRAILING);
    private JLabel modelLabel = new JLabel("Model: ", JLabel.TRAILING);
    private JLabel regNumLabel = new JLabel("Registration number: ", JLabel.TRAILING);
    private JLabel fuelLabel = new JLabel("Fuel type: ", JLabel.TRAILING);
    private JLabel seatsLabel = new JLabel("Number of seats: ", JLabel.TRAILING);
    private JLabel consumpLabel = new JLabel("Average consumption: ", JLabel.TRAILING);
    private JLabel depWeightLabel = new JLabel("Maximum deposit weight: ", JLabel.TRAILING);
    private JLabel capacityLabel = new JLabel("Capacity: ", JLabel.TRAILING);

    private JTextField manTextField = new JTextField(20);
    private JTextField modelTextField = new JTextField(20);
    private JTextField regNumTextField = new JTextField(20);
    private JTextField fuelTextField = new JTextField(20);
    private JTextField seatsTextField = new JTextField(20);
    private JTextField consumpTextField = new JTextField(20);
    private JTextField depWeightTextField = new JTextField(20);
    private JTextField capacityTextField = new JTextField(20);

    private JButton editButton = new JButton("Edit");

    private JList<Vehicle> vehiclesList = new JList<Vehicle>();

//    private Information info = Information.getInformation();
    private BusRepository busRepository = new BusRepository();
    private VanRepository vanRepository = new VanRepository();
    private TruckRepository truckRepository = new TruckRepository();

    public VehicleEditorPanel() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        busButton.addActionListener(new CustomActionListener());
        vanButton.addActionListener(new CustomActionListener());
        truckButton.addActionListener(new CustomActionListener());
        vehicleTypesGroup.add(busButton);
        vehicleTypesGroup.add(truckButton);
        vehicleTypesGroup.add(vanButton);
        radioButtonPanel.add(busButton);
        radioButtonPanel.add(truckButton);
        radioButtonPanel.add(vanButton);

        errorLabel.setVisible(false);

        vehiclesList.setCellRenderer(new VehicleRenderer());
        vehiclesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vehiclesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Vehicle car = vehiclesList.getSelectedValue();
                createForm(formPanel, car);
            }
        });

        listPanel.add(vehiclesList);

        add(radioButtonPanel);
        add(listPanel);
        add(formPanel);
        add(errorLabel);
    }

    private void setNewVehicleList(List<? extends Vehicle> vehicles) {

        listPanel.removeAll();
        vehiclesList = new JList<Vehicle>();
        vehiclesList.setCellRenderer(new VehicleRenderer());
        vehiclesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vehiclesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Vehicle car = vehiclesList.getSelectedValue();
                createForm(formPanel, car);
            }
        });
        vehiclesList.setListData(new Vector<>(vehicles));
        listPanel.add(vehiclesList);
    }

    private void createForm(JPanel initPanel, Vehicle vehicle) {

        initPanel.removeAll();

        if (vehicle instanceof Bus) {

            Bus bus = (Bus) vehicle;

            manTextField.setText(bus.getManufacturer());
            modelTextField.setText(bus.getModel());
            regNumTextField.setText(bus.getRegistrationNumber());
            fuelTextField.setText(bus.getFuelType().toString());
            seatsTextField.setText(Integer.toString(bus.getNumberOfSeats()));
            consumpTextField.setText(Double.toString(bus.getAverageConsumption()));

            initPanel.setLayout(new GridLayout(0,2));

            initPanel.add(manLabel);
            manLabel.setLabelFor(manTextField);
            initPanel.add(manTextField);

            initPanel.add(modelLabel);
            modelLabel.setLabelFor(modelTextField);
            initPanel.add(modelTextField);

            initPanel.add(regNumLabel);
            regNumLabel.setLabelFor(regNumTextField);
            initPanel.add(regNumTextField);

            initPanel.add(fuelLabel);
            fuelLabel.setLabelFor(fuelTextField);
            initPanel.add(fuelTextField);

            initPanel.add(seatsLabel);
            seatsLabel.setLabelFor(seatsTextField);
            initPanel.add(seatsTextField);

            initPanel.add(consumpLabel);
            consumpLabel.setLabelFor(consumpTextField);
            initPanel.add(consumpTextField);

            editButton.addActionListener(new EditActionListener());
            initPanel.add(editButton);

            commLabel.setVisible(false);
            initPanel.add(commLabel);

//            SpringUtilities.makeCompactGrid(initPanel,
//                    7, 2, //rows, cols
//                    6, 6,        //initX, initY
//                    3, 3);

        } else {

            if (vehicle instanceof Truck) {
                Truck truck = (Truck) vehicle;

                manTextField.setText(truck.getManufacturer());
                modelTextField.setText(truck.getModel());
                regNumTextField.setText(truck.getRegistrationNumber());
                fuelTextField.setText(truck.getFuelType().toString());
                consumpTextField.setText(Double.toString(truck.getAverageConsumption()));
                depWeightTextField.setText(Double.toString(truck.getMaximumDepositWeight()));
                capacityTextField.setText(Double.toString(truck.getCapacity()));

            } else {
                Van van = (Van) vehicle;

                manTextField.setText(van.getManufacturer());
                modelTextField.setText(van.getModel());
                regNumTextField.setText(van.getRegistrationNumber());
                fuelTextField.setText(van.getFuelType().toString());
                consumpTextField.setText(Double.toString(van.getAverageConsumption()));
                depWeightTextField.setText(Double.toString(van.getMaximumDepositWeight()));
                capacityTextField.setText(Double.toString(van.getCapacity()));
            }

            initPanel.setLayout(new GridLayout(0,2));

            initPanel.add(manLabel);
            manLabel.setLabelFor(manTextField);
            initPanel.add(manTextField);

            initPanel.add(modelLabel);
            modelLabel.setLabelFor(modelTextField);
            initPanel.add(modelTextField);

            initPanel.add(regNumLabel);
            regNumLabel.setLabelFor(regNumTextField);
            initPanel.add(regNumTextField);

            initPanel.add(fuelLabel);
            fuelLabel.setLabelFor(fuelTextField);
            initPanel.add(fuelTextField);

            initPanel.add(consumpLabel);
            consumpLabel.setLabelFor(consumpTextField);
            initPanel.add(consumpTextField);

            initPanel.add(depWeightLabel);
            depWeightLabel.setLabelFor(depWeightTextField);
            initPanel.add(depWeightTextField);

            initPanel.add(capacityLabel);
            capacityLabel.setLabelFor(capacityTextField);
            initPanel.add(capacityTextField);

            editButton.addActionListener(new EditActionListener());
            initPanel.add(editButton);

            commLabel.setVisible(false);
            initPanel.add(commLabel);

//            SpringUtilities.makeCompactGrid(initPanel,
//                    8, 2, //rows, cols
//                    0, 6,        //initX, initY
//                    3, 3);
        }

//        initPanel.setVisible(true);

    }

    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String vehicleType = evt.getActionCommand();

            // JList e dinamica; daca schimbi selectia, va selecta doar null
            if (vehicleType.equals("Bus")) {
//                List<Bus> buses = info.getBuses();
                List<Bus> buses = busRepository.readBuses();
                setNewVehicleList(buses);
                formPanel.removeAll();
            } else if (vehicleType.equals("Truck")) {
//                List<Truck> trucks = info.getTrucks();
                List<Truck> trucks = truckRepository.readTrucks();
//                listPanel.removeAll();
//                vehiclesList = new JList<Vehicle>();
//                vehiclesList.setListData(new Vector<>(trucks));
//                listPanel.add(vehiclesList);
                setNewVehicleList(trucks);
                formPanel.removeAll();
            } else {
//                List<Van> vans = info.getVans();
                List<Van> vans = vanRepository.readVans();
//                listPanel.removeAll();
//                vehiclesList = new JList<Vehicle>();
//                vehiclesList.setListData(new Vector<>(vans));
//                listPanel.add(vehiclesList);
                setNewVehicleList(vans);
                formPanel.removeAll();
            }

        }
    }

    class EditActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

//    class CarSelectionListener implements ListSelectionListener {
//        @Override
//        public void valueChanged(ListSelectionEvent evt) {
//            Vehicle car = vehiclesList.getSelectedValue();
//            createForm(formPanel, car);
//        }
//    }

    class VehicleRenderer extends JLabel implements ListCellRenderer<Vehicle> {
        public VehicleRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Vehicle> list, Vehicle vehicle, int index,
                                                      boolean isSelected, boolean cellHasFocus) {

            //setIcon(imageIcon);
            setText(vehicle.getManufacturer() + " " + vehicle.getModel() + ", " + vehicle.getRegistrationNumber());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }
}
