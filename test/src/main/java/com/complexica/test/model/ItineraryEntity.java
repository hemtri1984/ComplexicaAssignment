package com.complexica.test.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="itinerary")
public class ItineraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String itineraryName;
    private String cityname;
    private String country;
    private Float temperature;
    private String weather;
    private String travelDate;
    private String advice;
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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

    public String getCityName() {
        return cityname;
    }

    public void setCityName(String cityName) {
        this.cityname = cityName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItineraryEntity that = (ItineraryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(itineraryName, that.itineraryName) &&
                Objects.equals(cityname, that.cityname) &&
                Objects.equals(country, that.country) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(weather, that.weather) &&
                Objects.equals(travelDate, that.travelDate)  &&
                Objects.equals(advice, that.advice) && 
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itineraryName);
    }

    @Override
    public String toString() {
        return "NameEntity{" +
                "id=" + id +
                ", itineraryName='" + itineraryName + '\'' +
                ", cityName='" + cityname + '\'' +
                ", country='" + country + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", travelDate='" + travelDate + '\'' +
                ", advice='" + advice + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
    
}
