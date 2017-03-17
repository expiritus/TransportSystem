package com.belhard.misha.entity;

import java.io.Serializable;

public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int transportTypeId;
    private String model;
    private int capacity;
    private boolean speed;

    public Transport() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (id != transport.id) return false;
        if (transportTypeId != transport.transportTypeId) return false;
        if (capacity != transport.capacity) return false;
        if (speed != transport.speed) return false;
        return model != null ? model.equals(transport.model) : transport.model == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + transportTypeId;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (speed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", transportTypeId=" + transportTypeId +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isSpeed() {
        return speed;
    }

    public void setSpeed(boolean speed) {
        this.speed = speed;
    }
}
