package cn.bus.entity;

public class Time {
    private Integer tid;
    private String times;
    private Line line;
    private String state;

    private String start;//开始时间点
    private String end;//结束时间点
    private int workLength;//时长比例

    private String spareStart;//空闲开始时间点
    private String spareEnd;//空闲结束时间点
    private int spareLength;//时长比例

    public Time() {
    }

    public int getWorkLength() {
        return workLength;
    }

    public void setWorkLength(int workLength) {
        this.workLength = workLength;
    }

    public int getSpareLength() {
        return spareLength;
    }

    public void setSpareLength(int spareLength) {
        this.spareLength = spareLength;
    }

    public String getSpareStart() {
        return spareStart;
    }

    public void setSpareStart(String spareStart) {
        this.spareStart = spareStart;
    }

    public String getSpareEnd() {
        return spareEnd;
    }

    public void setSpareEnd(String spareEnd) {
        this.spareEnd = spareEnd;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
