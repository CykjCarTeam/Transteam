package cn.bus.web;

import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
* @Author:小星
* @Description: 后台用户访问句柄
* @Date:上午 9:53 2019/9/19 0019
*/
@Controller
@RequestMapping("/adminHandle/")
public class AdminHandle {

    @Resource
    private IAdminBiz iAdminBiz;

    @RequestMapping("login")
    public ModelAndView login(Admin admin){

        System.out.println("进来了");
        System.out.println("anum" + admin.getAnum());
        Admin admins = iAdminBiz.login(admin.getAnum(), admin.getApwd());


        return new ModelAndView("admin/index");
    }
}
