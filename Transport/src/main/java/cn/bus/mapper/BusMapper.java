package cn.bus.mapper;

import cn.bus.entity.Bus;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List findBus(Bus bus);
    int busTotal(Bus bus);
    int change(Bus bus);
}
