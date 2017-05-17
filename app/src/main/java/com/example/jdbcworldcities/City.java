package com.example.jdbcworldcities;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private String countryCode;
    private String district;
    private int population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public City(String name, String countryCode, String district, int population) {
        super();
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    @Override
    public String toString() {
        return "City [name=" + name + ", countryCode=" + countryCode
                + ", district=" + district + ", population=" + population + "]";
    }


}
