package cn.bus.entity;

public class News {
    private Integer nid;
    private String daynews;
    private  String daytime;

    public News() {
    }

    public News(Integer nid, String daynews, String daytime) {
        this.nid = nid;
        this.daynews = daynews;
        this.daytime = daytime;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getDaynews() {
        return daynews;
    }

    public void setDaynews(String daynews) {
        this.daynews = daynews;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }
}
