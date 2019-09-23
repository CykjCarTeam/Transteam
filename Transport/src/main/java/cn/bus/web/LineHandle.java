package cn.bus.web;

import cn.bus.entity.Line;
import cn.bus.entity.Station;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    private int cid;//城市id（前台下拉框值)

    //用于访问后台jsp
    @RequestMapping("skip")
    public ModelAndView skipPage(){

        return new ModelAndView("admin/cityline");
    }

    //初始化路线数据表格
    @RequestMapping("init")
    @ResponseBody
    public Map<String, Object> initLine(HttpServletRequest request, int cid, int page, int limit){

        System.out.println("初始页码" + page);
        this.cid = cid;
        List<Line> allLines = new LinkedList<>();
        allLines.add(new Line("1路"));
        allLines.add(new Line("2路"));
        allLines.add(new Line("3路"));
        allLines.add(new Line("4路"));
        allLines.add(new Line("5路"));
        allLines.add(new Line("6路"));

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", 20);
        data.put("data", allLines);

        return data;
    }

    //加载添加线路的窗口
    @RequestMapping("addline")
    public ModelAndView addLine(){

        return new ModelAndView("admin/addline");
    }

    //加载站点
    @RequestMapping("loadStation")
    @ResponseBody
    public List<Station> loadStation(){

        System.out.println("进来了---");
        List<Station> allStation = new ArrayList<>();
        allStation.add(new Station(1, "软件园东二门"));
        allStation.add(new Station(2, "软件园东门"));
        allStation.add(new Station(3, "高林街"));
        allStation.add(new Station(4, "金林云玺"));
        allStation.add(new Station(5, "五缘学村"));
        return allStation;
    }

    //提交线路
    @RequestMapping("commitLine")
    @ResponseBody
    public String commitLine(HttpServletRequest request){

        System.out.println("lid" + request.getParameter("lid"));
        System.out.println("line" + request.getParameter("line"));
        String msg = "1";

        return msg;
    }
}
