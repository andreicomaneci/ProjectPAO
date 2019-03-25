package model;

import java.util.Objects;

public class GoodsTransportOrder extends Order {

    City deliveryPlace;
    double weight;
    double volume;

    public GoodsTransportOrder(City deliveryPlace, double weight, double volume) {
        this.deliveryPlace = deliveryPlace;
        this.weight = weight;
        this.volume = volume;
    }

    public City getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(City deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsTransportOrder that = (GoodsTransportOrder) o;
        return Double.compare(that.weight, weight) == 0 &&
                Double.compare(that.volume, volume) == 0 &&
                Objects.equals(deliveryPlace, that.deliveryPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryPlace, weight, volume);
    }

    @Override
    public String toString() {
        return "GoodsTransportOrder{" +
                "deliveryPlace=" + deliveryPlace +
                ", weight=" + weight +
                ", volume=" + volume +
                '}';
    }
}
