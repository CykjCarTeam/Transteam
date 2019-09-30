package cn.bus.entity;

//线路实体类
public class Station {
  private int sid;
  public String station;
  private Bus bus;
  private Time times;

  public Station() {
  }

  public int getSid() {
    return sid;
  }

  public void setSid(int sid) {
    this.sid = sid;
  }

  public Bus getBus() {
    return bus;
  }

  public void setBus(Bus bus) {
    this.bus = bus;
  }

  public Time getTimes() {
    return times;
  }

  public void setTimes(Time times) {
    this.times = times;
  }

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }
}