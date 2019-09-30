package cn.bus.web;

import cn.bus.biz.ArrangeBiz;
import cn.bus.biz.TimeBiz;
import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.entity.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/arrangeHandle/")
public class ArrangeHandle {
    private Integer page;
    private Integer limit;
    @Resource
    private ArrangeBiz arrangeBiz;

    @Resource
    private TimeBiz timeBiz;


    //跳转城市车辆鸟瞰图
    @RequestMapping("bird")
    public ModelAndView bird(){
        return new ModelAndView("admin/cityline");
    }
    //跳转车辆管理界面
    @RequestMapping("scheduling1")
    public ModelAndView scheduling1(){
        return new ModelAndView("admin/scheduling");
    }
    //弹窗
    @RequestMapping("arrange")
    public ModelAndView arrange(HttpServletRequest request, String lid, String bid) {
//        model.addObject("lid",lid);
//        model.addObject("bid",bid);
        request.setAttribute("lid",lid);
        request.setAttribute("bid",bid);
        System.out.println("00000000000000000000000"+bid);
        //时间轴
        Map map=new HashMap();
        map.put("bid",bid);
        timeBiz.findAllTime(request,map);
        //
        return new ModelAndView("admin/arrange");
    }
    //跳转车辆实时停站车辆查询
    @RequestMapping("unlinebus")
    public ModelAndView unlinebus(){
        return new ModelAndView("admin/unlinebus");
    }

    //车辆排班表
    @RequestMapping(value="/scheduling.action",method= RequestMethod.GET, produces="application/json;charset=utf-8")
    public @ResponseBody Map scheduling(HttpServletRequest request,String toback){
        String lid=request.getParameter("llid");

        System.out.println("goback"+toback);
        List<Bus> list = arrangeBiz.scheduling(lid,toback);
        System.out.println("goback"+list);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("data",list);
        map.put("code",0);
        return map;
    }
    //排班 /排班替换
    @RequestMapping(value="/state.action", method=RequestMethod.POST, produces="application/text;charset=utf-8")
    public @ResponseBody String state(String bid,Integer tid){
        List<Bus> list;
        list = arrangeBiz.state(tid,bid);
        String msg;
        if (list!= null) {
            msg = "0";
        }else{
            msg = "1";
        }
        return msg;
    }
    //实时停站车辆查询
    @RequestMapping(value="/remove.action",method= RequestMethod.GET, produces="application/json;charset=utf-8")
    public @ResponseBody Map remove(String bid,String station,String province,String city,Integer page,Integer limit){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int mouth = cal.get(Calendar.MONTH)+ 1;
        int day = cal.get(Calendar.DATE);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        Integer time = h;
        System.out.println("时间"+year+"-"+mouth+"-"+day+"-"+h+":"+min);
        List<Time> list1 = arrangeBiz.removeall(bid,station,province,city,time);
        List<Bus> list = arrangeBiz.remove(bid,station,province,city,time,(page-1)*limit,limit);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",list);
        map.put("code",0);
        map.put("count",list1.size());
        return map;
    }
    //省份查询
    @RequestMapping(value="/province.action",method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody List province(){
        List<City> list = arrangeBiz.province();
        return list;
    }
    //城市查询
    @RequestMapping(value="/city.action",method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody List city(String province){
        List<City> list = arrangeBiz.city(province);
        return list;
    }

}
