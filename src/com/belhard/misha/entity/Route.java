package com.belhard.misha.entity;

import java.io.Serializable;

public class Route implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private int transportId;
    private int from;
    private int to;
    private int statusId;
    private String timeDeparture;
    private String arrivalTime;

    public Route() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        if (transportId != route.transportId) return false;
        if (from != route.from) return false;
        if (to != route.to) return false;
        if (statusId != route.statusId) return false;
        if (timeDeparture != null ? !timeDeparture.equals(route.timeDeparture) : route.timeDeparture != null)
            return false;
        return arrivalTime != null ? arrivalTime.equals(route.arrivalTime) : route.arrivalTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + transportId;
        result = 31 * result + from;
        result = 31 * result + to;
        result = 31 * result + statusId;
        result = 31 * result + (timeDeparture != null ? timeDeparture.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", transportId=" + transportId +
                ", from=" + from +
                ", to=" + to +
                ", statusId=" + statusId +
                ", timeDeparture='" + timeDeparture + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
