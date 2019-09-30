package cn.bus.entity;

public class DriverWorkload {
    private int id;
    private String dates;
    private String states;

    private  String line;



    private  int page;
    private  int limit;
    //查询字段
    private String begindates;
    private String enddates;

    public DriverWorkload() {
    }

    public DriverWorkload(int id, String dates, String states) {
        this.id = id;
        this.dates = dates;
        this.states = states;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getBegindates() {
        return begindates;
    }

    public void setBegindates(String begindates) {
        this.begindates = begindates;
    }

    public String getEnddates() {
        return enddates;
    }

    public void setEnddates(String enddates) {
        this.enddates = enddates;
    }
}
