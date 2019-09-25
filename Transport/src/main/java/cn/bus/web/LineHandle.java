package cn.bus.web;

import cn.bus.biz.ILineBiz;
import cn.bus.entity.City;
import cn.bus.entity.Line;
import cn.bus.entity.Station;
import com.google.gson.Gson;
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
        Integer count = lineBizImp.lineCounts(condition);
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

        String sid = request.getParameter("sid");//该线路下的所有站点
        String line = request.getParameter("line");//线路名
        //由于JSON默认Integer类型是Double类型
        Gson gson = new Gson();
        Integer[] allStation = gson.fromJson(sid,Integer[].class);//解析成Integer类型
        List<Integer> lidList = Arrays.asList(allStation);//所有站点
        String duration = lidList.size() * 5 + "min";//理论时长
        String cost = "1元";//单程费用

        Map<String, Object> condition = new HashMap<>();//添加的条件
        condition.put("cid", cid);
        condition.put("line", line);
        condition.put("origin", lidList.get(0));
        condition.put("terminal", lidList.get(lidList.size()-1));
        condition.put("duration", duration);
        condition.put("cost", cost);
        condition.put("stations", lidList);

        Boolean res = lineBizImp.createLine(condition);//业务层插入

        return res ? "1" : "-1";
    }

    //删除线路
    @RequestMapping("del")
    @ResponseBody
    public String delLine(Integer lid){

        Boolean res = lineBizImp.deleteLine(lid);
        return res ? "1" : "-1";
    }

    //更新线路
    @RequestMapping("upd")
    @ResponseBody
    public String updateLine(String line, Integer lid){

        //更新线路是没有返回值，那我如何知道该线路该数据更新成功了呢
        Line lines = lineBizImp.verifyLine(line);
        String msg = "-1";

        if(null == lines){
            msg = "1";
            lineBizImp.modifyLine(new Line(lid, line));
        }

        return msg;
    }
}
