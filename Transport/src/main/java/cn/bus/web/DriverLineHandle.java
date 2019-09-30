package cn.bus.web;

import cn.bus.biz.IDriverBiz;
import cn.bus.entity.Driver;
import cn.bus.entity.Line;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Linelist")
public class DriverLineHandle {

    @Resource
    private IDriverBiz idbiz;

    private Line line = new Line();

    private ModelAndView mav;

    @RequestMapping(value = "/find.action" , method = RequestMethod.POST , produces = "application/json;charset=utf-8")
    @ResponseBody
    public List <Line> find(HttpServletRequest request , String aid) {

        System.out.println("----sdddd000------"+aid);
        List <Line> flist = idbiz.findallline();
        System.out.println("list:"+flist);
        return flist;
    }

}
