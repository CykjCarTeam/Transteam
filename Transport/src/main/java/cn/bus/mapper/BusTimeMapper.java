package cn.bus.mapper;

import cn.bus.entity.Bus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户表操作接口
* @Date:上午 9:53 2019/9/19 0019
*/
@Repository
public interface BusTimeMapper {

    Bus findAllTime(Map map);

    List findBusTimeByBid();
}
