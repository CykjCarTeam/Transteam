package cn.bus.web;

import cn.bus.biz.IDriverBiz;
import cn.bus.entity.Driver;
import cn.bus.entity.DriverScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/drischedulinglist")
public class DriverSchedulingHandle {
    @Resource
    private IDriverBiz idbiz ;

    private DriverScheduling driversch=new DriverScheduling() ;
    private List flist;
    private ModelAndView mav;


//    @RequestMapping("/find.action")
//    public @ResponseBody
//    Map find(String dname, Integer page, Integer limit){
//        System.out.println("排班");
//        System.out.println(dname + ":"+page );
//
//        Map<String,Object> condition=new HashMap <String,Object>();
//        condition.put("page",(page-1)*limit);
//        condition.put("limit",limit);
//        condition.put("dname",dname);
//
//
//
//
//
//        List<DriverScheduling > list=idbiz.findallScheduling(condition);
//
//
//        System.out.println("名字："+list.get(0).getAname());
//
//        List<DriverScheduling > count=idbiz.countScheduling(condition);
//
//        System.out.println("gggg"+count.size());
//        Map<String, Object> map = new HashMap <String, Object>();
//        map.put("code", 0);
//        map.put("count",count.size());
//        map.put("msg", "");//提示消息
//        map.put("data", list);
//
//        return map;
//    }



    @RequestMapping("/find.action")
    public ModelAndView findwenall(HttpServletRequest request){
        System.out.println("哦哦");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("driverSchedulingList3");


        return mav;
    }

}
