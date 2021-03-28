package com.complexica.test.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="weather")
public class WeatherEntity implements Serializable {
    private static final long serialVersionUID = -3556308911813478176L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String cityname;
    private String country;
    private String countrycode;
    private Float temperature;
    private String weather;
    private String weatherdate;
    private String weathertime;
    private Date recordtime;
    private Boolean cache;

    public Long getId() {
        return id;
    }

    public String getWeathertime() {
        return weathertime;
    }

    public void setWeathertime(String weathertime) {
        this.weathertime = weathertime;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    public String getDate() {
        return weatherdate;
    }

    public void setDate(String weatherDate) {
        this.weatherdate = weatherDate;
    }

    public Date getRecordTime() {
        return recordtime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordtime = recordTime;
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
        return countrycode;
    }

    public void setCountryCode(String countryCode) {
        this.countrycode = countryCode;
    }

    public String getCountryName() {
        return country;
    }

    public void setCountryName(String country) {
        this.country = country;
    }

    public String getCityName() {
        return cityname;
    }

    public void setCityName(String cityName) {
        this.cityname = cityName;
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
                Objects.equals(cityname, that.cityname) &&
                Objects.equals(country, that.country) &&
                Objects.equals(countrycode, that.countrycode) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(weather, that.weather) &&
                Objects.equals(recordtime, that.recordtime) &&
                Objects.equals(weatherdate, that.weatherdate) &&
                Objects.equals(weathertime, that.weathertime) &&
                Objects.equals(cache, that.cache);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country+cityname);
    }

    @Override
    public String toString() {
        return "NameEntity{" +
                "id=" + id +
                ", cityName='" + cityname + '\'' +
                ", countryName='" + country + '\'' +
                ", countryCode='" + countrycode + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", recordTime='" + recordtime + '\'' +
                ", date='" + weatherdate + '\'' +
                ", date='" + weathertime + '\'' +
                ", cache='" + cache + '\'' +
                '}';
    }

    
}
