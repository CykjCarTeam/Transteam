package cn.bus.web;

import cn.bus.biz.IDriverBiz;
import cn.bus.entity.Driver;
import cn.bus.entity.DriverWorkload;
import cn.bus.entity.News;
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
@RequestMapping("/newlist")
public class NewsHandle {

    @Resource
    private IDriverBiz idbiz ;

    private News news=new News() ;


    @RequestMapping("/newfind")
    public @ResponseBody
    Map newfind( String dname,Integer page, Integer limit){


        Map<String,Object> condition=new HashMap <String,Object>();
        condition.put("page",(page-1)*limit);
        condition.put("limit",limit);
        condition.put("dname",dname);



        List<News> list=idbiz.findallNews(condition);

        List<News> count=idbiz.countNews(condition);

        System.out.println("jjjjj"+count.size());
        Map<String, Object> map = new HashMap <String, Object>();
        map.put("code", 0);
        map.put("count",count.size());
        map.put("msg", "");//提示消息
        map.put("data", list);

        return map;
    }

    @RequestMapping("/newdelete")
    public @ResponseBody String  newdelete(HttpServletRequest request,Integer nid){
        System.out.println("删除进来");

        System.out.println("删除序号是:："+nid);

        System.out.println(nid+"你好nid");
       int ab=idbiz.deleteNews(nid);
        System.out.println(ab+"你好bb");
        String msg="0";
        if (ab>0){
            msg="1";
        }




        return msg;

    }

    //修改信息
    @RequestMapping("/newchange")
    public ModelAndView newchange(HttpServletRequest request){

        System.out.println("进来了Y有");
        String nid= request.getParameter("aid1");


        String xinwen= request.getParameter("xinwen");

        System.out.println(nid+"---www"+xinwen);

        int ac=  idbiz.updateNews(nid,xinwen);
        System.out.println(ac+"----dddd------");
        if (ac>0){
            System.out.println("xxxx");

        }

        ModelAndView  model = new ModelAndView();
        model.setViewName("Administrator/newsList");//重定向

        return model;
    }
}
