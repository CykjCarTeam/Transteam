package cn.bus.biz;

import cn.bus.entity.Bus;
import cn.bus.entity.Time;
import cn.bus.mapper.BusTimeMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class TimeBizImp implements TimeBiz {

    @Resource
    private BusTimeMapper timeMapper;

    @Override
    public void findDuration(ModelAndView model) {

    }

    @Override
    public void findBusTimeByBid(ModelAndView model) {

    }

    @Override
    public void findAllTime(HttpServletRequest request, Map map) {
        Bus bus=timeMapper.findAllTime(map);
        List<Time> times=bus.getTime();
        List<Time> timesNew=new ArrayList<>();//新的集合放置排班以及空闲时间
        if(times!=null&&times.size()>0){
            int total=1080;//时间轴总长
            int end=0;//
            String endTime=null;
            String spareStart=null;//空闲时间开始时间
            String spareEnd=null;//空闲时间结束时间
            for(int i=0;i<times.size();i++){
                Time time=times.get(i);
                if(time.getTimes()!=null){
                    String []hourAndMin=time.getTimes().split(":");//时间点拆分成 hour   min
                    int hour=Integer.parseInt(hourAndMin[0]);
                    int min=Integer.parseInt(hourAndMin[1]);
                    int duration=0;
                    if(time.getLine().getDuration()!=null){
                        duration=Integer.parseInt(time.getLine().getDuration());//线路时长
                    }
                    //空闲的情况
                    if(i>=1){//（第二趟排班时间，理论上排班表的安排两趟排班有时间间隔，这个条件是成立的—）
                        Time time1=new Time();
                        spareStart=endTime;//空闲时间开始时刻
                        spareEnd=time.getTimes();//空闲时间结束时刻
                        time1.setSpareStart(spareStart);
                        time1.setSpareEnd(spareEnd);
                        time1.setSpareLength((((hour-6)*60+min)-end)*100/1080);//时长比例
                        timesNew.add(time1);//添加空闲时间实体
                    }
                    //拼接结束时间点
                    end=(hour-6)*60+min+(15+duration);//在时间轴上的结束时刻，作为下一次空闲时间起点
                    if((min+duration+15)>60){
                        hour++;
                        min=min+duration+15-60;
                    }
                    time.setWorkLength((min+duration+15)*100/1080);
                    System.out.println(time.getWorkLength()+time.getSpareLength());
                    if(min==0){
                        endTime=String.valueOf(hour)+":"+String.valueOf(min)+"0";//拼接结束时间点
                    }else {
                        endTime=String.valueOf(hour)+":"+String.valueOf(min);//拼接结束时间点
                    }
                    time.setEnd(endTime);
                    timesNew.add(time);
                    if(end>=total){
                        break;
                    };//
                }
            }
        }else{
            Time time=new Time();
            timesNew.add(time);
        }

        request.setAttribute("bus",bus);
        request.setAttribute("allTime",timesNew);
//        model.addObject("bus",bus);
//        model.addObject("allTime",timesNew);
    }
}
