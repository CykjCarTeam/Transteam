package cn.bus.entity;
/**
* @Author:小星
* @Description: 站点
* @Date:下午 3:42 2019/9/23 0023
*/
public class Station {

    private int sid ;
    private String station;
    private String coord_x;//经度
    private String coord_y;//纬度

    public Station() {
    }

    public Station(int sid, String station) {
        this.sid = sid;
        this.station = station;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(String coord_x) {
        this.coord_x = coord_x;
    }

    public String getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(String coord_y) {
        this.coord_y = coord_y;
    }
}
