package model;

public class Bus extends Vehicle{

    private int numberOfSeats;

    public Bus(String manufacturer, String model, String registrationNumber, FuelType fuelType, int numberOfSeats, double averageConsumption) {
        super(manufacturer, model, registrationNumber, fuelType, averageConsumption);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "numberOfSeats=" + numberOfSeats +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", fuelType=" + fuelType +
                ", averageConsumption=" + averageConsumption +
                '}';
    }
}
