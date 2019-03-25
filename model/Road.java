package model;

import java.util.Objects;

public class Road {                 // posibil sa ramana singura, fara alti descendenti
    protected City departure;
    protected City destination;
    protected String roadNumber;
    protected double length;
    protected int tax;
    protected int speedLimit;

    public Road(City departure, City destination, String roadNumber, double length, int tax, int speedLimit) {
        this.departure = departure;
        this.destination = destination;
        this.roadNumber = roadNumber;
        this.length = length;
        this.tax = tax;
        this.speedLimit = speedLimit;
    }

    public City getDeparture() {
        return departure;
    }

    public void setDeparture(City departure) {
        this.departure = departure;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public String getRoadNumber() {
        return roadNumber;
    }

    public void setRoadNumber(String roadNumber) {
        this.roadNumber = roadNumber;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Double.compare(road.length, length) == 0 &&
                tax == road.tax &&
                departure.equals(road.departure) &&
                destination.equals(road.destination) &&
                Objects.equals(roadNumber, road.roadNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, roadNumber, length, tax);
    }

    @Override
    public String toString() {
        return departure + " -> " + destination;
    }
}
