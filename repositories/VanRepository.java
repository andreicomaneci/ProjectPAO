package repositories;

import model.FuelType;
import model.Van;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VanRepository {

    private String url = "jdbc:mysql://localhost:3306/transcomp";
    private String username = "myuser";
    private String password = "xxxx";

    public void addVan(Van van) {
        String sql = "INSERT INTO vans (manufacturer, model, registrationNumber, fuelType, "
                + "maximumDepositWeight, capacity, averageConsumption) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, van.getManufacturer());
            stmt.setString(2, van.getModel());
            stmt.setString(3, van.getRegistrationNumber());
            stmt.setString(4, van.getFuelType().toString());
            stmt.setDouble(5, van.getMaximumDepositWeight());
            stmt.setDouble(6, van.getCapacity());
            stmt.setDouble(7, van.getAverageConsumption());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Van> readVans() {
        List<Van> list = new ArrayList<>();
        String sql = "SELECT * FROM vans";

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

                list.add(new Van(manufacturer, model, registrationNumber, fuelType, maximumDepositWeight, capacity, averageConsumption));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateVan(Van van) {
        String sql = "UPDATE vans"
                + "SET manufacturer = ?, model = ?, registrationNumber = ?, "
                + "fuelType = ?, maximumDepositWeight = ?, capacity = ?, averageConsumption = ? "
                + "WHERE registrationNumber = " + van.getRegistrationNumber();

        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, van.getManufacturer());
            stmt.setString(2, van.getModel());
            stmt.setString(3, van.getRegistrationNumber());
            stmt.setString(4, van.getFuelType().toString());
            stmt.setDouble(5, van.getMaximumDepositWeight());
            stmt.setDouble(6, van.getCapacity());
            stmt.setDouble(7, van.getAverageConsumption());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteVan(Van van) {
        String sql = "DELETE FROM vans WHERE registrationNumber = ?";

        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setString(1, van.getRegistrationNumber());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
