package cn.bus.entity;

import java.util.List;

/**
* @Author:小星
* @Description:线路
* @Date:下午 3:21 2019/9/23 0023
*/
public class Line {

    private Integer lid;
    private String line;
    private String duration;//理论时长
    private String cost;  //理论费用
    private Station origin; //起点
    private Station terminal;//终点
    private List<Station> allStations; //线路对应的站点
    private List<Bus> allBus;//对应的车辆
    private Bus bus;

    public Line() {

    }

    public Line(Integer lid, String line) {
        this.lid = lid;
        this.line = line;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public Station getTerminal() {
        return terminal;
    }

    public void setTerminal(Station terminal) {
        this.terminal = terminal;
    }

    public List<Station> getAllStations() {
        return allStations;
    }

    public void setAllStations(List<Station> allStations) {
        this.allStations = allStations;
    }

    public List<Bus> getAllBus() {
        return allBus;
    }

    public void setAllBus(List<Bus> allBus) {
        this.allBus = allBus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
