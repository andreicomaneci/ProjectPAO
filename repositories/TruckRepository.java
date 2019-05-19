package repositories;

import model.FuelType;
import model.Truck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TruckRepository {

    private String url = "jdbc:mysql://localhost:3306/transcomp";
    private String username = "myuser";
    private String password = "xxxx";

    public void addTruck(Truck truck) {
        String sql = "INSERT INTO trucks (manufacturer, model, registrationNumber, fuelType, "
                + "maximumDepositWeight, capacity, averageConsumption) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, truck.getManufacturer());
            stmt.setString(2, truck.getModel());
            stmt.setString(3, truck.getRegistrationNumber());
            stmt.setString(4, truck.getFuelType().toString());
            stmt.setDouble(5, truck.getMaximumDepositWeight());
            stmt.setDouble(6, truck.getCapacity());
            stmt.setDouble(7, truck.getAverageConsumption());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Truck> readTrucks() {
        List<Truck> list = new ArrayList<>();
        String sql = "SELECT * FROM trucks";

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
                double maximumDepositWeight = rs.getDouble("maximumDepositWeight");
                double capacity = rs.getDouble("capacity");
                double averageConsumption = rs.getFloat("averageConsumption");

                list.add(new Truck(manufacturer, model, registrationNumber, fuelType, maximumDepositWeight, capacity, averageConsumption));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateTruck(Truck truck) {
        String sql = "UPDATE trucks"
                + "SET manufacturer = ?, model = ?, registrationNumber = ?, "
                + "fuelType = ?, maximumDepositWeight = ?, capacity = ?, averageConsumption = ? "
                + "WHERE registrationNumber = " + truck.getRegistrationNumber();

        try (
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, truck.getManufacturer());
            stmt.setString(2, truck.getModel());
            stmt.setString(3, truck.getRegistrationNumber());
            stmt.setString(4, truck.getFuelType().toString());
            stmt.setDouble(5, truck.getMaximumDepositWeight());
            stmt.setDouble(6, truck.getCapacity());
            stmt.setDouble(7, truck.getAverageConsumption());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteTruck(Truck truck) {
        String sql = "DELETE FROM trucks WHERE registrationNumber = ?";

        try (
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, truck.getRegistrationNumber());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
