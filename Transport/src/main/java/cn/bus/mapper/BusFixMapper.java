package cn.bus.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户表操作接口
* @Date:上午 9:53 2019/9/19 0019
*/
@Repository
public interface BusFixMapper {

    //所有在检修车辆
    List findBusOnFix(Map map);
    int countFix(Map map);

    //某个城市 车辆所有检修记录
    List fixRecord(Map map);
    int countRecord(Map map);

}
