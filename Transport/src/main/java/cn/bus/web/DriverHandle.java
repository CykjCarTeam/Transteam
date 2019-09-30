package cn.bus.web;

import cn.bus.biz.IDriverBiz;
import cn.bus.entity.Driver;
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
@RequestMapping("/drilist")
public class DriverHandle {
    @Resource
    private IDriverBiz idbiz ;

    private Driver driver=new Driver() ;
    private List flist;
    private ModelAndView mav;






    @RequestMapping("/find.action")
    public @ResponseBody Map find(String dname, String dphone, String dsite,int page,int limit){
        System.out.println(dname + ":" + dphone+"--"+dsite);

//      Map<String,Object> condition=new HashMap <String,Object>();
//      condition.put("page",(page-1)*5);
//      condition.put("limit",limit);
//      condition.put("dname",dname);
//      condition.put("dphone",dphone);
//      condition.put("dsite",dsite);
        driver.setDname(dname);
        driver.setDphone(dphone);
        driver.setDsite(dsite);
        driver.setPage((page-1)*5);
        driver.setLimit(limit);
        List<Driver> list=idbiz.findall(driver);

        List<Driver> count=idbiz.count(driver);

//        List<Driver> list=idbiz.findall(driver);
//
//        List<Driver> count=idbiz.count(driver);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
       map.put("count",count.size());
       map.put("msg", "");//提示消息
       map.put("data", list);

        return map;
    }

    //修改信息
    @RequestMapping("/change.action")
    public ModelAndView change(HttpServletRequest request){

        System.out.println("进来了Y有");
        String aid= request.getParameter("aid1");
//        String xianglu= request.getParameter("xianlu");

        String majorid1= request.getParameter("majorid1");

        System.out.println(aid+"---www"+majorid1);

        int ac=  idbiz.upadriver(aid,majorid1);
        System.out.println(ac+"----dddd------");
        if (ac>0){
            System.out.println("xxxx");

        }

        ModelAndView  model = new ModelAndView();
        model.setViewName("driverManagement/driverList");//重定向
        return model;
    }




}
