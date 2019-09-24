package cn.bus.entity;

public class Station
{
    private Integer sid;
    private String station;
    private String coor_x;
    private String coor_y;
    private Integer lid;
    private String line;
    private String city;
    public Station()
    {
    }

    public Station(Integer sid, String station, String coor_x, String coor_y, Integer lid, String line, String city)
    {
        this.sid = sid;
        this.station = station;
        this.coor_x = coor_x;
        this.coor_y = coor_y;
        this.lid = lid;
        this.line = line;
        this.city = city;
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

}
