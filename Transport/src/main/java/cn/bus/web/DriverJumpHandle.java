package cn.bus.web;

import cn.bus.biz.IDriverBiz;
import cn.bus.entity.Admin;
import cn.bus.entity.DriverScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/DriverJumpHandle/")
public class DriverJumpHandle {

    @Resource
    private IDriverBiz idbiz ;
    private DriverScheduling driversch=new DriverScheduling() ;


    @RequestMapping("driverlist")
    public ModelAndView driverlist(){



        return new ModelAndView("admin/driverList");

    }

    @RequestMapping("Wages")
    public ModelAndView Wages(){



        return new ModelAndView("admin/driverWagesList");

    }

    @RequestMapping("SchedulingList")
    public ModelAndView SchedulingList(HttpServletRequest request){
        Integer page=1;
          Integer limit=5;


        Map <String,Object> condition=new HashMap <String,Object>();

        condition.put("page",(page-1)*limit);
        condition.put("limit",limit);


        List <DriverScheduling > list=idbiz.findallScheduling(condition);
        List <DriverScheduling > clist=idbiz.findallScheduling(condition);

//

        Map<DriverScheduling,List<DriverScheduling>> map=new LinkedHashMap <DriverScheduling,List<DriverScheduling>>();
        for (DriverScheduling driverScheduling :list) {
            map.put(driverScheduling,new ArrayList <DriverScheduling>());
            for (DriverScheduling driverScheduling1 :clist) {
                if(driverScheduling1.getAname()==driverScheduling.getAname()){
                    List<DriverScheduling> flist=map.get(driverScheduling);
                    list.add(driverScheduling1);
                }
            }
        }
//        request.getSession().setAttribute("map",map);
//        request.getSession().setAttribute("anum",);



        List<DriverScheduling > count=idbiz.countScheduling(condition);
        System.out.println("你的名字是"+list.get(0).getAname()+count.size());
        request.getSession().setAttribute("flist", list);


        return new ModelAndView("admin/driverSchedulingList2");

    }

    @RequestMapping("workloadList")
    public ModelAndView workloadList(){
        System.out.println("ggg");
        System.out.println("了");

        return new ModelAndView("admin/workloadList");


    }

    @RequestMapping("News")
    public ModelAndView News(){



        return new ModelAndView("admin/newsList");


    }
    @RequestMapping("Partnerslist")
    public ModelAndView Partnerslist(){



        return new ModelAndView("admin/PartnersList");



    }

    @RequestMapping("Advertising")
    public ModelAndView Advertising(){


        return new ModelAndView("admin/AdvertisingList");



    }
}
