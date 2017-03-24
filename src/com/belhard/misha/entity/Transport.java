package com.belhard.misha.entity;

import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.FieldMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;

import java.io.Serializable;


@ClassMapping(name = "transport")
public class Transport extends AbstractEntity implements Serializable {

    @IgnoreForInsert
    private static final long serialVersionUID = 1L;

    @FieldMapping(name = "transport_type_id")
    private int transportTypeId;
    private String model;
    private int capacity;
    private double speed;

    @IgnoreForInsert
    private TransportType transportType;

    public Transport() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (transportTypeId != transport.transportTypeId) return false;
        if (capacity != transport.capacity) return false;
        if (Double.compare(transport.speed, speed) != 0) return false;
        return model != null ? model.equals(transport.model) : transport.model == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = transportTypeId;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + capacity;
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ShowTransport{" +
                "transportTypeId=" + transportTypeId +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                '}';
    }

    public int getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(int transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }
}
