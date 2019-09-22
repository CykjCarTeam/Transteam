package cn.bus.web;

import cn.bus.biz.BusBiz;
import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import cn.bus.entity.Bus;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户访问句柄
* @Date:上午 9:53 2019/9/19 0019
*/
@Controller
@RequestMapping("/bus/")
public class BusHandle {
    private Map map=new HashMap();

    @Resource
    private BusBiz busBiz;

    @RequestMapping("allProvince")
    public ModelAndView allProvince(ModelAndView model){
        busBiz.findCity(model);
        model.setViewName("admin/Main");
        return model;
    }
    @RequestMapping("busList")
    public ModelAndView busList(ModelAndView model,Bus bus){
        model.addObject("bus",bus);
        model.setViewName("admin/BusList");
        return model;
    }
    //
    @RequestMapping("findBus")
    @ResponseBody
    public Map findBus(Bus bus){
        map.put("msg","");
        map.put("code",0);
        busBiz.findBus(map,bus);
        return map;
    }
    //修改车辆信息
    @RequestMapping("change")
    public ModelAndView change(ModelAndView model,Bus bus){
        System.out.println(bus.getProtector());
        busBiz.change(bus);
        model.setViewName("admin/BusList");//重定向
        return model;
    }
    @RequestMapping("delete")//删除
    @ResponseBody
    public Map delete(Bus bus){
        return map;
    }

    @RequestMapping("stop")//报废
    @ResponseBody
    public Map stop(Bus bus){
        return map;
    }
}
