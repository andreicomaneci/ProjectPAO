package graphicalInterface;

import copyrightedClasses.SpringUtilities;
import model.Bus;
import model.FuelType;
import model.Information;
import repositories.BusRepository;

import javax.swing.*;
import javax.swing.SpringLayout;
import java.awt.event.*;

public class BusPanel extends JPanel {

    private JLabel manLabel = new JLabel("Manufacturer: ", JLabel.TRAILING);
    private JLabel modelLabel = new JLabel("Model: ", JLabel.TRAILING);
    private JLabel regNumLabel = new JLabel("Registration number: ", JLabel.TRAILING);
    private JLabel fuelLabel = new JLabel("Fuel type: ", JLabel.TRAILING);
    private JLabel seatsLabel = new JLabel("Number of seats: ", JLabel.TRAILING);
    private JLabel consumpLabel = new JLabel("Average consumption: ", JLabel.TRAILING);

    private JTextField manTextField = new JTextField(20);
    private JTextField modelTextField = new JTextField(20);
    private JTextField regNumTextField = new JTextField(20);
    private JTextField fuelTextField = new JTextField(20);
    private JTextField seatsTextField = new JTextField(20);
    private JTextField consumpTextField = new JTextField(20);

    private JButton insertBus = new JButton("Add new bus");

    private JLabel errorLabel = new JLabel();

    //private final int numFields = 6;
//    private Information info = Information.getInformation();

    private BusRepository busRepository = new BusRepository();

    public BusPanel() {

        //SpringLayout layout = new SpringLayout();

        setLayout(new SpringLayout());

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

        add(seatsLabel);
        seatsLabel.setLabelFor(seatsTextField);
        add(seatsTextField);

        add(consumpLabel);
        consumpLabel.setLabelFor(consumpTextField);
        add(consumpTextField);

        insertBus.addActionListener(new CustomActionListener());
        add(insertBus);

        errorLabel.setVisible(false);
        add(errorLabel);

        // 7 = 6 TextFields + 1 Button
        SpringUtilities.makeCompactGrid(this,
                7, 2, //rows, cols
                6, 6,        //initX, initY
                3, 3);
    }

    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            errorLabel.setVisible(false);
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
                Integer numberOfSeats = Integer.parseInt(seatsTextField.getText());
                Double averageConsumption = Double.parseDouble(consumpTextField.getText());
                Bus bus = new Bus(manufacturer, model, registrationNumber, fuelType, numberOfSeats, averageConsumption);
//                info.addVehicle(bus);
                busRepository.addBus(bus);
                errorLabel.setText("Bus added.");
                errorLabel.setVisible(true);
            } catch (Exception exc) {
                errorLabel.setText("Input is invalid.");
                errorLabel.setVisible(true);
            }
        }
    }
}
