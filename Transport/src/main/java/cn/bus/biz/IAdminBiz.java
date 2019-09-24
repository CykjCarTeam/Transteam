package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Station;

import java.util.List;



public interface IAdminBiz {

    public Admin login(String anum, String apwd);
    public List<Station> station_list(String city, String station, Integer page, Integer limit);
    public Integer station_listpage(String city, String station, Integer page);
}
