package cn.bus.web;

import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
@Controller
@RequestMapping("/SiteHandle/")
public class SiteHandle
{


    @RequestMapping("site_list")
    public ModelAndView site_list(){
        return new ModelAndView("admin/site-list");
    }
    @RequestMapping("site_addpage")
    public ModelAndView site_addpage(){
        return new ModelAndView("admin/sitesetup");
    }
}