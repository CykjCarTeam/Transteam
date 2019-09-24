package cn.bus.entity;
/**
* @Author:小星
* @Description: 站点
* @Date:下午 3:42 2019/9/23 0023
*/
public class Station {

    private Integer sid ;
    private String station;
    private String coor_x;//经度
    private String coor_y;//纬度

    public Station() {
    }

    public Station(int sid, String station) {
        this.sid = sid;
        this.station = station;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getCoor_x() {
        return coor_x;
    }

    public void setCoor_x(String coor_x) {
        this.coor_x = coor_x;
    }

    public String getCoor_y() {
        return coor_y;
    }

    public void setCoor_y(String coor_y) {
        this.coor_y = coor_y;
    }
}
