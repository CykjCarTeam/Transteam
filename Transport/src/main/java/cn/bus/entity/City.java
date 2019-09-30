package cn.bus.entity;

public class City
{
    private Integer cid;
    private String province;
    private String city;

    public City()
    {
    }

    public City(Integer cid, String province, String city)
    {
        this.cid = cid;
        this.province = province;
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

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
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
