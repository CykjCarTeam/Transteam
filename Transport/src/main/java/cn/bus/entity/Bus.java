package cn.bus.entity;

public class Bus {
    private String bid;//车牌
    private String bus;//几路车
    private String protector;
    private String status;
    private int year;
    private String online;//是否固定线路
    private int lid;//线路id

    private int page;

    private Params params;

    private int cid;
    private String city;

    private String oldbid;//修改之前的车牌

    public Bus() {
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOldbid() {
        return oldbid;
    }

    public void setOldbid(String oldbid) {
        this.oldbid = oldbid;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page-1;
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }
}
