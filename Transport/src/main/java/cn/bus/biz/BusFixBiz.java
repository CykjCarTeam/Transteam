package cn.bus.biz;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
* @Author:小星
* @Description:后台用户业务接口
* @Date:上午 9:53 2019/9/19 0019
*/
public interface BusFixBiz {

    void findAllBus(ModelAndView model, Map map);
    void findBusOnFix(Map map);

    void findFixRecord(Map map);
}
