package com.belhard.misha.entity;

import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;

import java.io.Serializable;

@ClassMapping(name = "country")
public class Country extends AbstractEntity implements Serializable {

    @IgnoreForInsert
    private static final long serialVersionUID = 1L;

    private String country;

    public Country() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country1 = (Country) o;

        return country != null ? country.equals(country1.country) : country1.country == null;
    }

    @Override
    public int hashCode() {
        return country != null ? country.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ShowCountry{" +
                "country='" + country + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
