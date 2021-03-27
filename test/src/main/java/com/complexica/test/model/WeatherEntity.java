package com.complexica.test.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="weather")
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String cityName;
    private String countryName;
    private String countryCode;
    private Float temperature;
    private String weather;
    private Long recordTime;

    public Long getId() {
        return id;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherEntity that = (WeatherEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(countryName, that.countryName) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(weather, that.weather) &&
                Objects.equals(recordTime, that.recordTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryName+cityName);
    }

    @Override
    public String toString() {
        return "NameEntity{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", recordTime='" + recordTime + '\'' +
                '}';
    }

    
}
