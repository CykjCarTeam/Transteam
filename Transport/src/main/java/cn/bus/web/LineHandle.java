package cn.bus.web;

import cn.bus.biz.ILineBiz;
import cn.bus.entity.City;
import cn.bus.entity.Line;
import cn.bus.entity.Station;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
* @Author:小星
* @Description: 配置城市路线
* @Date:上午 11:17 2019/9/23 0023
*/
@Controller
@RequestMapping("/lineHandle/")
public class LineHandle {

    private Integer cid;//城市id（前台下拉框值)
    @Resource
    private ILineBiz lineBizImp;

    //用于访问后台jsp
    @RequestMapping("skip")
    public ModelAndView skipPage(){

        List<City> allCitys = lineBizImp.getCitys();
        ModelAndView modelAndView = new ModelAndView("admin/cityline");
        modelAndView.addObject("citys" , allCitys);

        return modelAndView;
    }

    //加载添加线路的窗口
    @RequestMapping("addline")
    public ModelAndView addLine(){

        return new ModelAndView("admin/addline");
    }

    //初始化路线数据表格
    @RequestMapping("init")
    @ResponseBody
    public Map<String, Object> initLine(HttpServletRequest request, Integer cid, Integer page, Integer limit){

        this.cid = cid;
        String line = request.getParameter("line");//线路
        Map<String, Object> condition = new HashMap<>();//查询条件
        condition.put("cid", cid);
        condition.put("line", line);
        condition.put("page", (page - 1) * limit);//当前页
        condition.put("count", limit);//一页显示几条

        List<Line> allLines = lineBizImp.getLines(condition);
        int count = lineBizImp.lineCounts(condition);
        Map<String, Object> data = new LinkedHashMap<>();//响应给前台的数据
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", count);
        data.put("data", allLines);

        return data;
    }

    //加载站点
    @RequestMapping("loadStation")
    @ResponseBody
    public List<Station> loadStation(){

        List<Station> allStation =lineBizImp.cityStations(cid);
        return allStation;
    }

    //提交线路添加
    @RequestMapping("commitLine")
    @ResponseBody
    public String commitLine(HttpServletRequest request){

        System.out.println("lid" + request.getParameter("lid"));
        System.out.println("line" + request.getParameter("line"));
        String msg = "1";

        return msg;
    }

    //删除线路
    @RequestMapping("del")
    @ResponseBody
    public String delLine(Integer lid){

        System.out.println("lid" + lid);
        return "1";
    }

    //更新线路
    @RequestMapping("upd")
    @ResponseBody
    public String updateLine(String line, Integer lid){

        Line lines = lineBizImp.verifyLine(line);
        String msg = "";
        if(lines != null){
            msg = "-1";
        }else{
            msg = "1";
            lineBizImp.modifyLine(new Line(lid, line));
        }

        return msg;
    }
}
