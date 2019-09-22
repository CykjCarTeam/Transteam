package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Bus;
import cn.bus.mapper.BusMapper;
import cn.bus.mapper.IAdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class BusBizImp implements BusBiz {

    @Resource
    private BusMapper busMapper;

    @Override
    public void find(ModelAndView model, Bus bus) {
        List list = busMapper.find(bus);
        model.addObject("proList",list);
    }
}
