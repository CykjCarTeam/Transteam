package cn.bus.web;

import cn.bus.biz.BusBiz;
import cn.bus.biz.BusFixBiz;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/busFix/")
public class BusFixHandle {

    @Resource
    private BusFixBiz fixBiz;
    @Resource
    private BusBiz busBiz;

    //进入车辆维修管理主界面
    @RequestMapping("busFixMain")
    public ModelAndView busFixMain(ModelAndView model){
        busBiz.findCity(model);
        model.setViewName("admin/BusFixMain");
        return model;
    }
    @RequestMapping("busFixList")
    public ModelAndView busFixList(ModelAndView model,Integer cid){
        Map map=new HashMap();
        map.put("cid",cid);
        busBiz.findCityByid(model,map);
        fixBiz.findAllBus(model,map);
        model.setViewName("admin/BusFix");
        return model;
    }
    @RequestMapping(value="busOnFix")
    @ResponseBody
    public Map busOnFix(Integer cid,Integer page,Integer limit){
        Map map=new HashMap();
        map.put("cid",cid);//城市id
        map.put("page",limit*(page-1));
        map.put("limit",limit);
        System.out.println(cid);
        fixBiz.findBusOnFix(map);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping(value="fixRecord")//维修记录
    @ResponseBody
    public Map fixRecord(Integer cid,String bid,Integer page,Integer limit){
        Map map=new HashMap();
        map.put("cid",cid);//城市id
        map.put("bid",bid);
        map.put("page",limit*(page-1));
        map.put("limit",limit);
        fixBiz.findFixRecord(map);
        map.put("code",0);
        map.put("msg","");
        return map;
    }
    //进入时间轴界面
    @RequestMapping("time")
    public ModelAndView time(ModelAndView model){
        busBiz.findCity(model);
        model.setViewName("admin/BusTime");
        return model;
    }
}
