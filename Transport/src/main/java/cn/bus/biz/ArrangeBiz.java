package cn.bus.biz;

import cn.bus.entity.*;

import java.util.List;

/**
* @Author:小星
* @Description:后台用户业务接口
* @Date:上午 9:53 2019/9/19 0019
*/
public interface ArrangeBiz {


    //车辆排班表
    public List<Bus> scheduling(String lid, String toback);
    //排班、排班替换
    public List<Bus> state(Integer tid, String bid);
    //实时停站线路查询总条数
    public List<Time> removeall(String bid, String station, String province, String city, Integer time) ;
    //实时停站线路查询
    public List<Bus> remove(String bid, String station, String province, String city, Integer time, Integer page, Integer limit);
    //省份查询
    public List<City> province();
    //城市查询
    public List<City> city(String province);


}
