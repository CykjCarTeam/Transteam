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
@RequestMapping("/driwagelist")
public class DriverWagesHandle {

    @Resource
    private IDriverBiz idbiz ;

    private Driver driver=new Driver() ;
    private List flist;
    private ModelAndView mav;


    @RequestMapping("/find.action")
    public @ResponseBody
    Map find(String dname, Integer page, Integer limit){
        System.out.println(dname + ":"+page );
//       driver.setDname(dname);
//        driver.setPage((page-1)*5);
//        driver.setLimit(limit);
      Map<String,Object> condition=new HashMap <String,Object>();
      condition.put("page",(page-1)*limit);
      condition.put("limit",limit);
      condition.put("dname",dname);





        List<Driver> list=idbiz.findallWages(condition);


        List<Driver> count=idbiz.countWages(condition);

        System.out.println("adfggggggdvbr"+count.size());
        Map<String, Object> map = new HashMap <String, Object>();
        map.put("code", 0);
        map.put("count",count.size());
        map.put("msg", "");//提示消息
        map.put("data", list);

        return map;
    }

    @RequestMapping("/change.action")
    public ModelAndView change(HttpServletRequest request){

        System.out.println("进来gogo");
        String name= request.getParameter("aname");


        String gongzi= request.getParameter("gongzi");

        System.out.println("---pppp"+name);
        System.out.println("---pppp"+gongzi);



        int ac=  idbiz.upaccount(name,gongzi);
        System.out.println(ac+"----dddd------");

        if (ac>0){
            System.out.println("xxxx");

        }
        ModelAndView  model = new ModelAndView();

        model.setViewName("driverManagement/driverWagesList");//重定向
        return model;
    }

}
