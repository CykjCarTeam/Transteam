package cn.bus.web;

import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import cn.bus.entity.Menu;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
* @Author:小星
* @Description: 后台用户访问句柄
* @Date:上午 9:53 2019/9/19 0019
*/
@Controller
@RequestMapping("/adminHandle")
public class AdminHandle {
    @Resource
    private IAdminBiz iAdminBiz;
    @RequestMapping("/login")
    public ModelAndView login(Admin admin, String authCode, HttpServletRequest request){
        String valueCode=(String)request.getSession().getAttribute("strCode");
        Admin admins = iAdminBiz.login(admin.getAnum(), admin.getApwd());
        if(null !=admins&valueCode.equals(authCode)) {
            //活动菜单数据
            List<Menu> flist=iAdminBiz.findfmenu(admins.getAnum());//父类菜单
            List<Menu> clist=iAdminBiz.findcmenu(admins.getAnum());//子类菜单
            Map<Menu,List<Menu>> map=new LinkedHashMap<Menu,List<Menu>>();
            for (Menu menu:flist) {
                map.put(menu,new ArrayList<Menu>());
                for (Menu menu1:clist) {
                    if(menu.getMid()==menu1.getPid()){
                        List<Menu> list=map.get(menu);
                        list.add(menu1);
                    }
                }
            }
            request.getSession().setAttribute("map",map);
            request.getSession().setAttribute("anum",admin.getAnum());
            return new ModelAndView("admin/index");
        }
        return  new ModelAndView("admin/login");
    }

    //登录的ajax
    @RequestMapping(value="/logajax", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String logajax(String anum){
        Admin admin=iAdminBiz.loginjax(anum);
        String messagq;
        if(null!=admin){
            messagq="1";
        }else{
            messagq="0";
        }
        return messagq;
    }
}
