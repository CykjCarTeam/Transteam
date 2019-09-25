package cn.bus.biz;

import cn.bus.mapper.BusFixMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class TimeBizImp implements TimeBiz {

    @Resource
    private BusFixMapper busFixMapper;

    @Override
    public void findDuration(ModelAndView model) {

    }

    @Override
    public void findBusTimeByBid(ModelAndView model) {

    }
}
