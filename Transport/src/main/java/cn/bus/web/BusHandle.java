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
    private int cid;//城市

    @Resource
    private BusBiz busBiz;

    //进入车辆管理主界面
    @RequestMapping("allProvince")
    public ModelAndView allProvince(ModelAndView model){
        busBiz.findCity(model);
        model.setViewName("admin/BusMain");
        return model;
    }
    //车辆列表
    @RequestMapping("busList")
    public ModelAndView busList(ModelAndView model,Integer cid){
        Map map=new HashMap();
        map.put("cid",cid);//
        //前台表格初始化需要发送bid请求，以及显示城市名
        busBiz.findCityByid(model,map);
        busBiz.findProtector(model);//所有维护人
        busBiz.findLine(model,map);//该城市已配车的所有线路
        busBiz.findState(model);//所有状态
        busBiz.findallBus(model,map);//所有车牌

        cid=cid;//保存,之后重载表格数据需要
        model.setViewName("admin/BusList");
        return model;
    }
    //
    @RequestMapping("findBus")
    @ResponseBody
    public Map findBus(Integer cid,String bid,String bus,String status,Integer lid,String online,String protector,Integer page,Integer limit){
        Map map=new HashMap();
        map.put("cid",cid);//
        map.put("bid",bid);
        map.put("bus",bus);//车名
        map.put("status",status);
        map.put("lid",lid);//
        map.put("online",online);//是否固定线路
        map.put("protector",protector);
        System.out.println(bid+bus+online+protector);
        map.put("page",limit*(page-1));
        map.put("limit",limit);

        map.put("msg","");
        map.put("code",0);
        busBiz.findBus(map);
        return map;
    }
    //修改车辆信息
    @RequestMapping(value = "change" , method= RequestMethod.POST)
    @ResponseBody
    public String change(String oldbid,String bus,String busyear,String online,String protector,Integer page,Integer limit){
        Map map=new HashMap();
        map.put("oldbid",oldbid);//修改之前的车牌
        map.put("bus",bus);//车名
        if(null!=busyear){
            busyear=PageUtil.getYear(busyear);
        }
        map.put("busyear",busyear);//上牌年份
        map.put("online",online);//是否固定线路
        map.put("protector",protector);

        int num=busBiz.change(map);
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
    public String add(Integer cid,String bid,String bus,String busyear,String online,String protector,String status){
        Map map=new HashMap();
        map.put("cid",cid);
        map.put("bid",bid);//修改之前的车牌
        map.put("bus",bus);//车名
        if(null!=busyear){
            busyear=PageUtil.getYear(busyear);
        }
        map.put("busyear",busyear);//上牌年份
        map.put("online",online);//是否固定线路
        map.put("protector",protector);
        map.put("status",status);
        boolean flag=busBiz.add(map);
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
