package cn.bus.web;

import cn.bus.biz.IAdminBiz;
import cn.bus.biz.IStationBiz;
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
    private IStationBiz StationBizImp;
    private List<Station> stationlist;
    private Map<String,Object> stationmap=new HashMap<String, Object>();
    @RequestMapping("site_list")
    public ModelAndView site_list(){
        return new ModelAndView("admin/site-list");
    }
    @RequestMapping("cashier_statistics")
    public ModelAndView cashier_statistics(){
        return new ModelAndView("admin/cashier_statistics");
    }
    @RequestMapping("site_addpage")
    public ModelAndView site_addpage(String city,String cid){
        ModelAndView  model=new ModelAndView("admin/sitesetadd");
        model.addObject("city",city);
        model.addObject("cid",cid);
        return model;
    }
    @RequestMapping("site_querypage")
    public ModelAndView site_querypage(String station,String coor_x,String coor_y,String city){
        ModelAndView  model=new ModelAndView("admin/sitesetquery");
        model.addObject("station",station);
        model.addObject("coor_x",coor_x);
        model.addObject("coor_y",coor_y);
        model.addObject("city",city);
        return model;
    }
    @RequestMapping(value="/stationlist.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    Map stationlist(String city, Integer page, String station, Integer limit)
    {
        stationlist=StationBizImp.station_list(city,station,page,limit);
        Integer pagecount=StationBizImp.station_listpage(city,station,page);
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

        List<City> cityList=StationBizImp.station_city();

        return cityList;
    }
    @RequestMapping(value="/stationadd.action", method= RequestMethod.GET)
    public @ResponseBody
    String stationadd(String cid,String station,String coor_x,String coor_y)
    {

        String mes=null;
        boolean flag=StationBizImp.station_add(station,coor_x,coor_y,cid);

        if (flag)
        {
            mes="0";
        }else
        {
            mes="1";
        }
        return mes;
    }

    @RequestMapping(value="/stationdel.action", method= RequestMethod.POST)
    public @ResponseBody
    String stationdel(String sid,String cid)
    {

        String mes=null;
        boolean flag=StationBizImp.station_del(sid,cid);
        if (flag)
        {
            mes="0";
        }else
        {
            mes="1";
        }
        return mes;
    }
    @RequestMapping("site_uppage")
    public ModelAndView site_uppage(String city,String sid,String coor_x,String coor_y,String station){
        ModelAndView  model=new ModelAndView("admin/sitesetupdate");
        model.addObject("city",city);
        model.addObject("sid",sid);
        model.addObject("coor_x",coor_x);
        model.addObject("coor_y",coor_y);
        model.addObject("station",station);

        return model;
    }
    @RequestMapping(value="/stationupdate.action", method= RequestMethod.POST)
    public @ResponseBody
    String stationupdate(String sid, String station, String coor_x, String coor_y)
    {

        String mes=null;
        Integer count=StationBizImp.stationupdate(sid,station,coor_x,coor_y);
        if (count>0)
        {
            mes="0";
        }else
        {
            mes="1";
        }
        return mes;
    }
}
