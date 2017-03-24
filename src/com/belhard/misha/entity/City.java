package com.belhard.misha.entity;


import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.FieldMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;

import java.io.Serializable;

@ClassMapping(name = "city")
public class City extends AbstractEntity implements Serializable {

    @IgnoreForInsert
    private static final long serialVersionUID = 1L;

    private String city;

    @FieldMapping(name = "country_id")
    private int countryId;

    @IgnoreForInsert
    private Country country;

    public City() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city1 = (City) o;

        if (countryId != city1.countryId) return false;
        return city != null ? city.equals(city1.city) : city1.city == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + countryId;
        return result;
    }

    @Override
    public String toString() {
        return "ShowCity{" +
                "city='" + city + '\'' +
                ", countryId=" + countryId +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
