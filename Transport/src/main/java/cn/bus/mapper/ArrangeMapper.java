package cn.bus.mapper;

import cn.bus.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户表操作接口
* @Date:上午 9:53 2019/9/19 0019
*/
@Repository
public interface ArrangeMapper {

    public Admin queryUser(Admin user);
    public Admin scheduling1(Admin user);
    public Admin unlinebus(Admin user);


    //车辆排班表
    public List<Bus> scheduling(Map map);
    //排班、排班替换
    public List<Bus> state(Map map) ;

    public List<Time> removeall(Map map);
    public List<Bus> remove(Map map);
    //省份查询
    public List<City> province(City city);
    //城市查询
    public List<City> city(String province);


}
