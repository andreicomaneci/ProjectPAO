package graphicalInterface;

import model.Information;
import model.Vehicle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CompanyGraphicsClass extends JFrame {

    private JTabbedPane jtp = new JTabbedPane();

    public CompanyGraphicsClass() {

        jtp.add("Bus", new BusPanel());
        jtp.add("Goods Vehicle", new GoodsTransportPanel());
        jtp.add("My Vehicles", new VehiclesPanel());
        jtp.add("Road selector", new RoadSelectorPanel());
        add(jtp);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Transport Company App");
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CompanyGraphicsClass();
                }
        });
    }
}