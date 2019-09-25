package cn.bus.biz;

import cn.bus.entity.Bus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
* @Author:小星
* @Description:后台用户业务接口
* @Date:上午 9:53 2019/9/19 0019
*/
public interface BusBiz {
    void findCity(ModelAndView model);
    void findBus(Map map);
    void findProtector(ModelAndView model);
    void findLine(ModelAndView model,Map map);
    void findState(ModelAndView model);
    void findallBus(ModelAndView model,Map map);
    void findCityByid(ModelAndView model,Map map);

    int change(Map map);//修改

    Bus findBusByid(Bus bus);
    boolean add(Map map);//新增

    int delete(Bus bus);//删除
    int stop(Bus bus);//报废
}
