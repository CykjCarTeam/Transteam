package cn.bus.web;

import cn.bus.biz.IDriverBiz;
import cn.bus.entity.Advertising;
import cn.bus.entity.Partners;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Advertisinglist")
public class AdvertisingHandle {
    @Resource
    private IDriverBiz idbiz ;

    private Advertising advertising=new Advertising() ;
    private List flist;



    @RequestMapping("/Advertisingfind.action")
    public @ResponseBody
    Map Advertisingfind(String dname, Integer page, Integer limit){
        System.out.println(dname + ":"+page );

        Map<String,Object> condition=new HashMap <String,Object>();
        condition.put("page",(page-1)*limit);
        condition.put("limit",limit);
        condition.put("dname",dname);





        List<Advertising> list=idbiz.findallAdvertising(condition);


        List<Advertising> count=idbiz.countAdvertising(condition);

        System.out.println("adfggggggdvbr"+count.size());
        Map<String, Object> map = new HashMap <String, Object>();
        map.put("code", 0);
        map.put("count",count.size());
        map.put("msg", "");//提示消息
        map.put("data", list);

        return map;
    }

//    @RequestMapping("/Advertisingdelete")
//    public @ResponseBody String  Partnersdelete(HttpServletRequest request, Integer mid){
//
//
//        System.out.println("删除gg序号是:："+mid);
//
//
//        int ab=idbiz.deletePartners(mid);
//        System.out.println(ab+"你好ggbb");
//        String msg="0";
//        if (ab>0){
//            msg="1";
//        }
//
//
//
//
//        return msg;
//
//    }
//
//    //修改信息
//    @RequestMapping("/Advertisingchange")
//    public ModelAndView Partnerschange(HttpServletRequest request){
//
//        System.out.println("进来了Y有");
//        String mid= request.getParameter("aid1");
//
//
//        String mname= request.getParameter("mname");
//
//
//        String msite= request.getParameter("address");
//        System.out.println(mid+"---www"+mname+"--"+msite);
//
//        int ac=  idbiz.updatePartners(mid,mname,msite);
//        System.out.println(ac+"----dddd------");
//        if (ac>0){
//            System.out.println("xxxx");
//
//        }
//
//        ModelAndView  model = new ModelAndView();
//        model.setViewName("Administrator/newsList");//重定向
//
//        return model;
//    }
}
