package cn.bus.entity;

public class Station
{
    private Integer sid;
    private String station;
    private String coor_x;
    private String coor_y;
    private Integer lid;
    private String line;
    private Integer cid;
    private String city;
    private Bus bus;
    private Time times;

    public Station()
    {
    }

    public Station(Integer sid, String station, String coor_x, String coor_y, Integer lid, String line, Integer cid, String city)
    {
        this.sid = sid;
        this.station = station;
        this.coor_x = coor_x;
        this.coor_y = coor_y;
        this.lid = lid;
        this.line = line;
        this.cid = cid;
        this.city = city;
    }

    public Integer getCid()
    {
        return cid;
    }

    public void setCid(Integer cid)
    {
        this.cid = cid;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Integer getSid()
    {
        return sid;
    }

    public void setSid(Integer sid)
    {
        this.sid = sid;
    }

    public String getStation()
    {
        return station;
    }

    public void setStation(String station)
    {
        this.station = station;
    }

    public String getCoor_x()
    {
        return coor_x;
    }

    public void setCoor_x(String coor_x)
    {
        this.coor_x = coor_x;
    }

    public String getCoor_y()
    {
        return coor_y;
    }

    public void setCoor_y(String coor_y)
    {
        this.coor_y = coor_y;
    }

    public Integer getLid()
    {
        return lid;
    }

    public void setLid(Integer lid)
    {
        this.lid = lid;
    }

    public String getLine()
    {
        return line;
    }

    public void setLine(String line)
    {
        this.line = line;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Time getTimes() {
        return times;
    }

    public void setTimes(Time times) {
        this.times = times;
    }
}
