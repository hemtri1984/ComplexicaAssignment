
package com.complexica.test.model;


public class ForecastData {

    private long cnt;
    private java.util.List<com.complexica.test.model.List> list = null;
    private City city;

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.complexica.test.model.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.complexica.test.model.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
