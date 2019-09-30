package cn.bus.entity;

import java.util.List;

public class Bus {
    private String bid;//车牌
    private String bus;//几路车
    private String protector;
    private String status;
    private String busyear;
    private String online;//是否固定线路

    private String intotime;
    private Time times;
    private Station station;

    private Line line;
    private Params params;

    private City city;
    private List<Time> time;

    public Bus() {
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public List<Time> getTime() {
        return time;
    }

    public void setTime(List<Time> time) {
        this.time = time;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }


    public String getBusyear() {
        return busyear;
    }

    public void setBusyear(String busyear) {
        this.busyear = busyear;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getProtector() {
        return protector;
    }

    public void setProtector(String protector) {
        this.protector = protector;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getIntotime() {
        return intotime;
    }

    public void setIntotime(String intotime) {
        this.intotime = intotime;
    }

    public Time getTimes() {
        return times;
    }

    public void setTimes(Time times) {
        this.times = times;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }


}
