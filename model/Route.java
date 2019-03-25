package model;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Route {

    private String routeCode;
    private List<Road> list;

    public Route() {
        this.routeCode = UUID.randomUUID().toString();
        this.list = new ArrayList<>();
    }

    public Route(List<Road> list) {
        this.routeCode = UUID.randomUUID().toString();
        this.list = list;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public List<Road> getList() {
        return list;
    }

    public void setList(List<Road> list) {
        this.list = list;
    }

    public void addRoad(Road road) {
        list.add(road);
    }

    public double getRouteLength() {
        double length = 0.0;
        for (Road road : list) {
            length += road.getLength();
        }
        return length;
    }

    public double getAverageMaximumSpeed() {
        double averageMaximumSpeed = this.getRouteLength();
        double averageTotalTime = 0.0;
        for (Road road : list) {
            averageTotalTime += road.getLength() / road.getSpeedLimit();
        }
        averageMaximumSpeed /= averageTotalTime;
        return averageMaximumSpeed;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeCode='" + routeCode + '\'' +
                ", list=" + list +
                '}';
    }
}
