package service;

import model.Vehicle;

import java.util.Comparator;

class ConsumptionComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        Double c1 = v1.getAverageConsumption();
        Double c2 = v2.getAverageConsumption();
        return c1.compareTo(c2);
    }
}
