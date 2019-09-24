package cn.bus.web;

import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import cn.bus.entity.City;
import cn.bus.entity.Station;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/siteHandle")
public class SiteHandle
{
    @Resource
    private IAdminBiz AdminBizImp;
    private List<Station> stationlist;
    private Map<String,Object> stationmap=new HashMap<String, Object>();
    @RequestMapping("site_list")
    public ModelAndView site_list(){
        return new ModelAndView("admin/site-list");
    }
    @RequestMapping("site_addpage")
    public ModelAndView site_addpage(String city){
        ModelAndView  model=new ModelAndView("admin/sitesetup");
        model.addObject("city",city);
        return model;
    }
    @RequestMapping(value="/stationlist.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    Map stationlist(String city, Integer page, String station, Integer limit)
    {

        System.out.println(city);
        stationlist=AdminBizImp.station_list(city,station,page,limit);

        Integer pagecount=AdminBizImp.station_listpage(city,station,page);
        stationmap.put("code",0);
        stationmap.put("data",stationlist);
        stationmap.put("city",city);
        stationmap.put("station",station);
        stationmap.put("count",pagecount);

        return stationmap;
    }
    @RequestMapping(value="/stationcity.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    List<City> stationcity()
    {

        List<City> cityList=AdminBizImp.station_city();

        return cityList;
    }
}
