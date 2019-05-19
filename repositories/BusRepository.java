package repositories;

import model.Bus;
import model.FuelType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusRepository {

    private String url = "jdbc:mysql://localhost:3306/transcomp";
    private String username = "myuser";
    private String password = "xxxx";

    public void addBus(Bus bus) {
        String sql = "INSERT INTO buses (manufacturer, model, registrationNumber, fuelType, numberOfSeats, averageConsumption) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, bus.getManufacturer());
            stmt.setString(2, bus.getModel());
            stmt.setString(3, bus.getRegistrationNumber());
            stmt.setString(4, bus.getFuelType().toString());
            stmt.setInt(5, bus.getNumberOfSeats());
            stmt.setDouble(6, bus.getAverageConsumption());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Bus> readBuses() {
        List<Bus> list = new ArrayList<>();
        String sql = "SELECT * FROM buses";

        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                String manufacturer = rs.getString("manufacturer");
                String model = rs.getString("model");
                String registrationNumber = rs.getString("registrationNumber");
                String fuelTypeName = rs.getString("fuelType");
                FuelType fuelType;
                if (fuelTypeName.equalsIgnoreCase("Diesel"))
                    fuelType = FuelType.Diesel;
                else
                    fuelType = FuelType.Petrol;
                int numberOfSeats = rs.getInt("numberOfSeats");
                double averageConsumption = rs.getFloat("averageConsumption");

                list.add(new Bus(manufacturer, model, registrationNumber, fuelType, numberOfSeats, averageConsumption));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateBus(Bus bus) {
        String sql = "UPDATE buses"
                    + "SET manufacturer = ?, model = ?, registrationNumber = ?, "
                    + "fuelType = ?, numberOfSeats = ?, averageConsumption = ? "
                    + "WHERE registrationNumber = " + bus.getRegistrationNumber();

        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, bus.getManufacturer());
            stmt.setString(2, bus.getModel());
            stmt.setString(3, bus.getRegistrationNumber());
            stmt.setString(4, bus.getFuelType().toString());
            stmt.setInt(5, bus.getNumberOfSeats());
            stmt.setDouble(6, bus.getAverageConsumption());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteBus(Bus bus) {
        String sql = "DELETE FROM buses WHERE registrationNumber = ?";

        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, bus.getRegistrationNumber());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
