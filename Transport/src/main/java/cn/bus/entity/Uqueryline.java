package cn.bus.entity;

public class Uqueryline
{
    private Integer lid;
    private String line;
    private Integer origin;
    private Integer terminal;
    private Integer duration;
    private String cost;
    private String bid;
    private String fstation;
    private String lstation;
    public Uqueryline()
    {
    }

    public Uqueryline(Integer lid, String line, Integer origin, Integer terminal, Integer duration, String cost, String bid, String fstation, String lstation)
    {
        this.lid = lid;
        this.line = line;
        this.origin = origin;
        this.terminal = terminal;
        this.duration = duration;
        this.cost = cost;
        this.bid = bid;
        this.fstation = fstation;
        this.lstation = lstation;
    }

    public String getFstation()
    {
        return fstation;
    }

    public void setFstation(String fstation)
    {
        this.fstation = fstation;
    }

    public String getLstation()
    {
        return lstation;
    }

    public void setLstation(String lstation)
    {
        this.lstation = lstation;
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

    public Integer getOrigin()
    {
        return origin;
    }

    public void setOrigin(Integer origin)
    {
        this.origin = origin;
    }

    public Integer getTerminal()
    {
        return terminal;
    }

    public void setTerminal(Integer terminal)
    {
        this.terminal = terminal;
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    public String getBid()
    {
        return bid;
    }

    public void setBid(String bid)
    {
        this.bid = bid;
    }
}
