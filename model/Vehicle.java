package model;

import java.util.Objects;

public class Vehicle {

    protected String manufacturer;
    protected String model;
    protected String registrationNumber;
    protected FuelType fuelType;
    protected double averageConsumption;

    public Vehicle(String manufacturer, String model, String registrationNumber, FuelType fuelType, double averageConsumption) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.fuelType = fuelType;
        this.averageConsumption = averageConsumption;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.averageConsumption, averageConsumption) == 0 &&
                manufacturer.equals(vehicle.manufacturer) &&
                model.equals(vehicle.model) &&
                registrationNumber.equals(vehicle.registrationNumber) &&
                fuelType == vehicle.fuelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, registrationNumber, fuelType, averageConsumption);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", fuelType=" + fuelType +
                ", averageConsumption=" + averageConsumption +
                '}';
    }
}
