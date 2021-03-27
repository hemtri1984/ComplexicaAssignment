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
    private String country;
    private String countryCode;
    private Float temperature;
    private String weather;
    private String weatherDate;
    private Long recordTime;

    public Long getId() {
        return id;
    }

    public String getDate() {
        return weatherDate;
    }

    public void setDate(String weatherDate) {
        this.weatherDate = weatherDate;
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
        return country;
    }

    public void setCountryName(String country) {
        this.country = country;
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
                Objects.equals(country, that.country) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(weather, that.weather) &&
                Objects.equals(recordTime, that.recordTime) &&
                Objects.equals(weatherDate, that.weatherDate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country+cityName);
    }

    @Override
    public String toString() {
        return "NameEntity{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", date='" + weatherDate + '\'' +
                '}';
    }

    
}
