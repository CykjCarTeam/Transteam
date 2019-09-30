package cn.bus.web;

import cn.bus.biz.TimeBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户访问句柄
* @Date:上午 9:53 2019/9/19 0019
*/
@Controller
@RequestMapping("/busTime/")
public class BusTimeHandle {

    @Resource
    private TimeBiz timeBiz;

    //进入时间轴界面
    @RequestMapping("time")
    public ModelAndView time(ModelAndView model){
        Map map=new HashMap();
        map.put("bid","闽5");
        timeBiz.findAllTime(model,map);
        model.setViewName("admin/BusTime");
        return model;
    }

}
