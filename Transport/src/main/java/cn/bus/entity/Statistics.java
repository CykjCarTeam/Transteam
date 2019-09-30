package cn.bus.entity;

public class Statistics
{
    private String id;
    private Integer cid;
    private Integer pnum;
    private Integer psum;
    private String line;
    private Integer money;
    private String city;

    public Statistics()
    {
    }

    public Statistics(String id, Integer cid, Integer pnum, Integer psum, String line, Integer money, String city)
    {
        this.id = id;
        this.cid = cid;
        this.pnum = pnum;
        this.psum = psum;
        this.line = line;
        this.money = money;
        this.city = city;
    }

    public Integer getPsum()
    {
        return psum;
    }

    public void setPsum(Integer psum)
    {
        this.psum = psum;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getCid()
    {
        return cid;
    }

    public void setCid(Integer cid)
    {
        this.cid = cid;
    }

    public Integer getPnum()
    {
        return pnum;
    }

    public void setPnum(Integer pnum)
    {
        this.pnum = pnum;
    }

    public String getLine()
    {
        return line;
    }

    public void setLine(String line)
    {
        this.line = line;
    }

    public Integer getMoney()
    {
        return money;
    }

    public void setMoney(Integer money)
    {
        this.money = money;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
}
