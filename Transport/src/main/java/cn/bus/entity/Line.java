package cn.bus.entity;

public class Line {
    private Integer lid;
    private String line;
    private  String origin;
    private  String duration;
    private  String cost;

    public Line() {
    }



    public Line(Integer lid, String line, String origin, String duration, String cost) {
        this.lid = lid;
        this.line = line;
        this.origin = origin;
        this.duration = duration;
        this.cost = cost;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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
}
