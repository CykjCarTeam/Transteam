package cn.bus.web;

import cn.bus.biz.BusBiz;
import cn.bus.entity.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private int cid;//城市

    @Resource
    private BusBiz busBiz;

    @RequestMapping("allProvince")
    public ModelAndView allProvince(ModelAndView model){
        busBiz.findCity(model);
        model.setViewName("admin/Main");
        return model;
    }
    //车辆列表
    @RequestMapping("busList")
    public ModelAndView busList(ModelAndView model,Bus bus){
        if(bus.getCid()==0){
            bus.setCid(cid);
        }
        //前台表格初始化需要发送bid请求，以及显示城市名
        busBiz.findCityByid(model,bus);
        busBiz.findProtector(model);//所有维护人
        busBiz.findLine(model,bus);//该城市已配车的所有线路
        busBiz.findState(model);//所有状态
        busBiz.findallBus(model,bus);//所有车牌

        cid=bus.getCid();//保存,之后重载表格数据需要
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
        busBiz.change(bus);
        model.setViewName("admin/BusList");//重定向
        return model;
    }

    //新增车辆
    @RequestMapping("add")
    public ModelAndView add(ModelAndView model, HttpServletRequest request, Bus bus){

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
