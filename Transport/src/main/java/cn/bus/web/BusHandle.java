package cn.bus.web;

import cn.bus.biz.BusBiz;
import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/bus/")
public class BusHandle {

    @Resource
    private BusBiz busBiz;

    @RequestMapping("busList")
    public ModelAndView login(Admin admin){


        return new ModelAndView("admin/Main");
    }
    //
    @RequestMapping("aa")
    @ResponseBody
    public Map aa(Admin admin){
        Map map=new HashMap();
        return map;
    }
}
