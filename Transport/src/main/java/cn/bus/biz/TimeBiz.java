package cn.bus.biz;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
* @Author:小星
* @Description:后台用户业务接口
* @Date:上午 9:53 2019/9/19 0019
*/
public interface TimeBiz {

    void findAllTime(ModelAndView model,Map map);
    void findDuration(ModelAndView model);
    void findBusTimeByBid(ModelAndView model);
}
