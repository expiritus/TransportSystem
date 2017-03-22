package com.belhard.misha.entity;

import com.belhard.misha.customAnnotations.ClassMapping;

import java.io.Serializable;


@ClassMapping(name = "status")
public class Status extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;

    public Status() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status1 = (Status) o;

        return status != null ? status.equals(status1.status) : status1.status == null;
    }

    @Override
    public int hashCode() {
        return status != null ? status.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
