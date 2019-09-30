package cn.bus.web;

import cn.bus.biz.IDriverBiz;
import cn.bus.entity.Driver;
import cn.bus.entity.DriverWorkload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/worklist")
public class WorkloadHandle {

    @Resource
    private IDriverBiz idbiz ;

    private DriverWorkload driverWorkload=new DriverWorkload() ;

    private ModelAndView mav;





    @RequestMapping("/wrokload.action")
    public @ResponseBody
    Map find( String begindate, String enddate, int page, int limit){
        System.out.println( ":v" + begindate+"--"+enddate);
        System.out.println("us方法被调用了...");
        //json
        System.out.println("page" + page);
        System.out.println("limit" + limit);

        driverWorkload.setPage((page-1)*5);
        driverWorkload.setLimit(limit);

        driverWorkload.setBegindates(begindate);
        driverWorkload.setEnddates(enddate);

        List<DriverWorkload> list=idbiz.findalldriverscheduling(driverWorkload);

        List<DriverWorkload>  count=idbiz.countdriverscheduling(driverWorkload);
        System.out.println(count.size()+"ggggggaaaaaaoooooo");
        Map<String, Object> map = new HashMap <String, Object>();
        map.put("code", 0);
        map.put("count",count.size());
        map.put("msg", "");//提示消息
        map.put("data", list);

        return map;
    }

}
