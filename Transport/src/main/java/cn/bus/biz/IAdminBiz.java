package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.City;
import cn.bus.entity.Station;

import java.util.List;
import java.util.Map;


public interface IAdminBiz {

    public Admin login(String anum, String apwd);
    public List<Station> station_list(String city, String station, Integer page, Integer limit);
    public Integer station_listpage(String city, String station, Integer page);
    public List<City> station_city();
    public boolean station_add(String station, String coor_x, String coor_y,String cid);
    public Integer station_city_add(Integer sid,String cid);
    public Integer station_del(String sid);
    public Integer station_city_del(String sid,String cid);
    public Integer stationupdate(String sid,String station,String coor_x,String coor_y);
}
