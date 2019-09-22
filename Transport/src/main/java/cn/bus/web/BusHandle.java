package cn.bus.web;

import cn.bus.biz.BusBiz;
import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import cn.bus.entity.Bus;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private Map map=new HashMap();

    @Resource
    private BusBiz busBiz;

    @RequestMapping("allProvince")
    public ModelAndView allProvince(ModelAndView model,Bus bus){
        busBiz.find(model,bus);
        model.setViewName("admin/Main");
        return model;
    }
    //
    @RequestMapping("aa")
    @ResponseBody
    public Map aa(Bus bus){

        return map;
    }
    @RequestMapping("change")
    public String change(Bus bus){
        System.out.println("修改");
        return "redirect:/admin/BusList";//重定向
    }
    @RequestMapping("delete")//删除
    @ResponseBody
    public Map delete(Bus bus){
        return map;
    }
    @RequestMapping("stop")//报废
    @ResponseBody
    public Map stop(Bus bus){
        return map;
    }
}
