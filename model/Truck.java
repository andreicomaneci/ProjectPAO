package model;

public class Truck extends Vehicle {

    protected double maximumDepositWeight;
    protected double capacity;

    public Truck(String manufacturer, String model, String registrationNumber, FuelType fuelType, double maximumDepositWeight, double capacity, double averageConsumption) {
        super(manufacturer, model, registrationNumber, fuelType, averageConsumption);
        this.maximumDepositWeight = maximumDepositWeight;
        this.capacity = capacity;
    }

    public double getMaximumDepositWeight() {
        return maximumDepositWeight;
    }

    public void setMaximumDepositWeight(double maximumDepositWeight) {
        this.maximumDepositWeight = maximumDepositWeight;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", fuelType=" + fuelType +
                ", maximumDepositWeight=" + maximumDepositWeight +
                ", capacity=" + capacity +
                ", averageConsumption=" + averageConsumption +
                '}';
    }
}
