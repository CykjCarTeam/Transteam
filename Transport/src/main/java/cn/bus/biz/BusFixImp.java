package cn.bus.biz;

import cn.bus.mapper.BusFixMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class BusFixImp implements BusFixBiz {

    @Resource
    private BusFixMapper busFixMapper;

    @Override
    public void findBusOnFix(Map map) {
        List list=busFixMapper.findBusOnFix(map);
        int count=busFixMapper.countFix(map);
        map.put("data",list);
        map.put("count",count);
    }

    @Override
    public void findFixRecord(Map map) {
        List list= busFixMapper.fixRecord(map);
        int count=busFixMapper.countRecord(map);
        map.put("data",list);
        map.put("count",count);
    }

    @Override
    public void findAllBus(ModelAndView model, Map map) {
        List list= busFixMapper.fixRecord(map);
        model.addObject("allBus",list);
    }


}
