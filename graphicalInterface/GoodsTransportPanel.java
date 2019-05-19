package graphicalInterface;

import copyrightedClasses.SpringUtilities;
import model.*;
import repositories.TruckRepository;
import repositories.VanRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoodsTransportPanel  extends JPanel {
    private JLabel manLabel = new JLabel("Manufacturer: ", JLabel.TRAILING);
    private JLabel modelLabel = new JLabel("Model: ", JLabel.TRAILING);
    private JLabel regNumLabel = new JLabel("Registration number: ", JLabel.TRAILING);
    private JLabel fuelLabel = new JLabel("Fuel type: ", JLabel.TRAILING);
    private JLabel consumpLabel = new JLabel("Average consumption: ", JLabel.TRAILING);
    private JLabel depWeightLabel = new JLabel("Maximum deposit weight: ", JLabel.TRAILING);
    private JLabel capacityLabel = new JLabel("Capacity: ", JLabel.TRAILING);

    private JTextField manTextField = new JTextField(20);
    private JTextField modelTextField = new JTextField(20);
    private JTextField regNumTextField = new JTextField(20);
    private JTextField fuelTextField = new JTextField(20);
    private JTextField consumpTextField = new JTextField(20);
    private JTextField depWeightTextField = new JTextField(20);
    private JTextField capacityTextField = new JTextField(20);

    private JLabel vehicleTypeLabel = new JLabel("Choose vehicle type: ", JLabel.TRAILING);
    JComboBox<String> vehicleTypeLister = new JComboBox<String>();

    private JButton insertVehicle = new JButton("Add new vehicle");

    private JLabel errorLabel = new JLabel();

//    private final int numFields = 7;
//    private Information info = Information.getInformation();

    private TruckRepository truckRepository = new TruckRepository();
    private VanRepository vanRepository = new VanRepository();

    public GoodsTransportPanel() {

        //SpringLayout layout = new SpringLayout();

        setLayout(new SpringLayout());

        vehicleTypeLister.addItem("Truck");
        vehicleTypeLister.addItem("Van");
        add(vehicleTypeLabel);
        vehicleTypeLabel.setLabelFor(vehicleTypeLister);
        add(vehicleTypeLister);

        add(manLabel);
        manLabel.setLabelFor(manTextField);
        add(manTextField);

        add(modelLabel);
        modelLabel.setLabelFor(modelTextField);
        add(modelTextField);

        add(regNumLabel);
        regNumLabel.setLabelFor(regNumTextField);
        add(regNumTextField);

        add(fuelLabel);
        fuelLabel.setLabelFor(fuelTextField);
        add(fuelTextField);

        add(consumpLabel);
        consumpLabel.setLabelFor(consumpTextField);
        add(consumpTextField);

        add(depWeightLabel);
        depWeightLabel.setLabelFor(depWeightTextField);
        add(depWeightTextField);

        add(capacityLabel);
        capacityLabel.setLabelFor(capacityTextField);
        add(capacityTextField);

        insertVehicle.addActionListener(new CustomActionListener());
        add(insertVehicle);

        errorLabel.setVisible(false);
        add(errorLabel);

        // 7 = 6 TextFields + 1 Button
        SpringUtilities.makeCompactGrid(this,
                9, 2, //rows, cols
                0, 6,        //initX, initY
                3, 3);
    }

    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            errorLabel.setVisible(false);
            String vehicleType = (String)vehicleTypeLister.getSelectedItem();
            try {
                String manufacturer = manTextField.getText();
                if (manufacturer == "")
                    throw new RuntimeException();
                String model = modelTextField.getText();
                if (model == "")
                    throw new RuntimeException();
                String registrationNumber = regNumTextField.getText();
                String fuelTypeString = fuelTextField.getText();
                FuelType fuelType;
                if (fuelTypeString.equalsIgnoreCase("Diesel"))
                    fuelType = FuelType.Diesel;
                else {
                    if (fuelTypeString.equalsIgnoreCase("Petrol"))
                        fuelType = FuelType.Petrol;
                    else
                        throw new  RuntimeException();
                }
                Double maximumDepositWeight = Double.parseDouble(depWeightTextField.getText());
                Double capacity = Double.parseDouble(capacityTextField.getText());
                Double averageConsumption = Double.parseDouble(consumpTextField.getText());
                if (vehicleType.equalsIgnoreCase("Truck")) {
                    Truck truck = new Truck(manufacturer, model, registrationNumber, fuelType, maximumDepositWeight, capacity, averageConsumption);
//                    info.addVehicle(truck);
                    truckRepository.addTruck(truck);
                }
                else {
                    Van van = new Van(manufacturer, model, registrationNumber, fuelType, maximumDepositWeight, capacity, averageConsumption);
//                    info.addVehicle(van);
                    vanRepository.addVan(van);
                }
            } catch (Exception exc) {
                errorLabel.setText("Input is invalid.");
                errorLabel.setVisible(true);
            }
        }
    }
}
