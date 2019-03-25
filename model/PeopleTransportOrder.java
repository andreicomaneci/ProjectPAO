package model;

import java.util.Objects;

public class PeopleTransportOrder extends Order {

    private int numberOfPeople;
    private City departure;
    private City destination;

    public PeopleTransportOrder(int numberOfPeople, City departure, City destination) {
        this.numberOfPeople = numberOfPeople;
        this.departure = departure;
        this.destination = destination;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleTransportOrder that = (PeopleTransportOrder) o;
        return numberOfPeople == that.numberOfPeople &&
                Objects.equals(departure, that.departure) &&
                Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPeople, departure, destination);
    }

    @Override
    public String toString() {
        return "PeopleTransportOrder{" +
                "numberOfPeople=" + numberOfPeople +
                ", departure=" + departure +
                ", destination=" + destination +
                '}';
    }
}
