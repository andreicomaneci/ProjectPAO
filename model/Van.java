package model;

public class Van extends Vehicle {

    protected double maximumDepositWeight;
    protected double capacity;

    public Van(String manufacturer, String model, String registrationNumber, FuelType fuelType, double maximumDepositWeight, double capacity, double averageConsumption) {
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
        return "Van{" +
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
