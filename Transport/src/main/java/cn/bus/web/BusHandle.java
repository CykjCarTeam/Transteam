package cn.bus.web;

import cn.bus.biz.BusBiz;
import cn.bus.entity.Bus;
import cn.bus.tool.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        System.out.println(bus.getLid());
        busBiz.findBus(map,bus);
        return map;
    }
    //修改车辆信息
    @RequestMapping(value = "change" , method= RequestMethod.POST)
    @ResponseBody
    public String change(Bus bus){
        bus.setBusyear(PageUtil.getYear(bus.getBusyear()));//换算使用年份
        int num=busBiz.change(bus);
        String res="0";
        if(num>0){//修改成功
            res="1";
        }
        return res;
    }
    //新增车辆车牌bid是否重复
    @RequestMapping(value="checkBid", method= RequestMethod.POST)
    @ResponseBody
    public String checkBid(Bus bus){
        Bus bus1=busBiz.findBusByid(bus);
        String res="0";
        if(null!=bus1){//
            res="1";
        }
        return res;
    }
    //新增车辆
    @RequestMapping(value="add", method= RequestMethod.POST)
    @ResponseBody
    public String add(Bus bus){
        System.out.println("========="+bus.getBusyear());
        bus.setBusyear(PageUtil.getYear(bus.getBusyear()));//换算使用年份
        boolean flag=busBiz.add(bus);
        String res="0";
        if(flag){//新增成功
            res="1";
        }
        return res;
    }

    @RequestMapping(value="delete",method = RequestMethod.POST)//删除
    @ResponseBody
    public String delete(Bus bus){
        int num=busBiz.delete(bus);
        String res="0";
        if(num>0){//删除成功
            res="1";
        }
        return res;
    }

    @RequestMapping(value="stop",method = RequestMethod.POST)//报废
    @ResponseBody
    public String stop(Bus bus){
        int num=busBiz.stop(bus);
        String res="0";
        if(num>0){//
            res="1";
        }
        return res;
    }
}
