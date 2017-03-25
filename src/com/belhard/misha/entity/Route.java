package com.belhard.misha.entity;

import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.FieldMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;

import java.io.Serializable;

@ClassMapping(name = "route")
public class Route extends AbstractEntity implements Serializable{

    @IgnoreForInsert
    private static final long serialVersionUID = 1L;

    @FieldMapping(name = "transport_id")
    private int transportId;
    private int from;
    private int to;

    @FieldMapping(name = "status_id")
    private int statusId;

    @FieldMapping(name = "time_departure")
    private String timeDeparture;

    @FieldMapping(name = "arrival_time")
    private String arrivalTime;

    @IgnoreForInsert
    private Transport transport;

    @IgnoreForInsert
    private City fromCity;

    @IgnoreForInsert
    private City toCity;

    @IgnoreForInsert
    private Status status;

    public Route() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

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
        int result = transportId;
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
                "transportId=" + transportId +
                ", from=" + from +
                ", to=" + to +
                ", statusId=" + statusId +
                ", timeDeparture='" + timeDeparture + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
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

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
