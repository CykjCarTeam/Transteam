package cn.bus.mapper;

import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.entity.Line;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户表操作接口
* @Date:上午 9:53 2019/9/19 0019
*/
@Repository
public interface BusMapper {

    //所有省市
    List findCity();
    //某一个市所有公交
    List findBus(Map map);
    int busTotal(Map map);
//所有维护人
    List findProtector();

    List findLine(Map map);

    List findState();

    City findCityByid(Map map);

    int change(Map map);

    int delBus(Bus bus);

    Bus findBusByid(Bus bus);
    boolean addBus(Map map);

    int stop(Bus bus);

    public List<Line> findlineall(Line line);
    public List<Line> findline(Map map);
}
