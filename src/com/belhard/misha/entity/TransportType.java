package com.belhard.misha.entity;

import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;

import java.io.Serializable;



@ClassMapping(name = "transport_type")
public class TransportType extends AbstractEntity implements Serializable {

    @IgnoreForInsert
    private static final long serialVersionUID = 1L;

    private String type;

    public TransportType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportType that = (TransportType) o;

        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ShowTransportType{" +
                "type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
