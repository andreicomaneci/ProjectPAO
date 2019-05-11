package graphicalInterface;

import model.City;
import model.Information;
import model.Road;
import model.Route;
import service.RouteOptimizationService;
import serviceCSV.CityReadWriteService;
import serviceCSV.RoadReadWriteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoadSelectorPanel extends JPanel {

    private JLabel startLabel = new JLabel("Departure");
    private JLabel stopLabel = new JLabel("Destination");
    private JLabel messageField = new JLabel();

    private JTextArea answerField = new JTextArea();

    private JTextField startField = new JTextField(20);
    private JTextField stopField = new JTextField(20);

    private JButton getRouteButton = new JButton("Get route");

    private Information info = Information.getInformation();
    private RouteOptimizationService ros = new RouteOptimizationService();

    public RoadSelectorPanel() {

        setLayout(new FlowLayout());

        CityReadWriteService cityReader = new CityReadWriteService();
        RoadReadWriteService roadReader = new RoadReadWriteService();
        cityReader.getCities();
        roadReader.getRoads();

//        StringBuffer aux = new StringBuffer();
//        aux.append("\nThe list of all roads\n---");
//        for (Road road: info.getRoads()) {
//            aux.append(road + "\n");
//        }
//        answerField.setText(aux.toString());

        startLabel.setLabelFor(startField);
        stopLabel.setLabelFor(stopField);

        add(startLabel);
        add(startField);

        add(stopLabel);
        add(stopField);

        getRouteButton.addActionListener(new customActionListener());
        add(getRouteButton);

        add(messageField);
        add(answerField);
    }

    private String printRoute(Route route, City departure, City destination) {
        StringBuilder solution = new StringBuilder("");
        City currCity = null;
        City nextCity = departure;
        for (Road road : route.getList()) {
            currCity = nextCity;
            if (road.getDeparture().equals(currCity))
                nextCity = road.getDestination();
            else
                nextCity = road.getDeparture();
            solution.append(currCity + "\n");
        }
        solution.append(nextCity);
        return solution.toString();
    }

    public class customActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            messageField.setVisible(false);
            String start = startField.getText();
            String stop = stopField.getText();
            try {
                City departure = info.getCity(start);
                City destination = info.getCity(stop);
                if (start.equals(""))
                    throw new RuntimeException("Departure field cannot be empty.");
                if (stop.equals(""))
                    throw new RuntimeException("Destination field cannot be empty.");
                if (departure == null)
                    throw new RuntimeException("Specified departure could not be found.");
                if (destination == null)
                    throw new RuntimeException("Specified destination could not be found.");
                Route bestRoute = ros.shortestRoute(departure, destination, info.getRoads());
                answerField.setText(printRoute(bestRoute, departure, destination));
                answerField.setVisible(true);
            } catch (Exception exc) {
                messageField.setText(exc.getMessage());
                messageField.setVisible(true);
                answerField.setVisible(false);
            }
        }
    }
}
