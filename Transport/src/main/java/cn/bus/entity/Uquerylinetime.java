package cn.bus.entity;

public class Uquerylinetime
{
    private String bid;
    private String times;

    public Uquerylinetime()
    {
    }

    public Uquerylinetime(String bid, String times)
    {
        this.bid = bid;
        this.times = times;
    }

    public String getBid()
    {
        return bid;
    }

    public void setBid(String bid)
    {
        this.bid = bid;
    }

    public String getTimes()
    {
        return times;
    }

    public void setTimes(String times)
    {
        this.times = times;
    }
}
