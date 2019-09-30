package cn.bus.entity;

public class DriverScheduling {
    private Integer id;
    private String dates;
    private String states;

    private Integer aid;
    private String aname;

    public DriverScheduling() {
    }

    public DriverScheduling(Integer id, String dates, String states, Integer aid, String aname) {
        this.id = id;
        this.dates = dates;
        this.states = states;
        this.aid = aid;
        this.aname = aname;
    }

//    public DriverScheduling(Integer id, String dates, String states, Integer aid) {
//        this.id = id;
//        this.dates = dates;
//        this.states = states;
//        this.aid = aid;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }


    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }
}
