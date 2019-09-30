package cn.bus.web;

import cn.bus.biz.IStatisticsBiz;
import cn.bus.entity.City;
import cn.bus.entity.GetUCount;
import cn.bus.entity.Statistics;
import cn.bus.mapper.IStatisticsMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/statisticsHandle")
public class StatisticsHandle
{
    @Resource
    private IStatisticsBiz statisticsBizImp;
    @RequestMapping(value="/statisticsline.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    List<Statistics> statisticsline(String cid)
    {

        List<Statistics> statisticslist=statisticsBizImp.statistics_line(cid);

        return statisticslist;
    }
    @RequestMapping(value="/mothcount.action", method= RequestMethod.POST)
    public @ResponseBody
    List mothcount(String cid)
    {


        List<GetUCount> list = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
        String datestr2 = dateFormat2.format(date);

        for (int i = 1; i < 6; i++)
        {
            Integer count1=0;
            GetUCount ucount = new GetUCount();
            ucount.setWeek(i + "周");
            List<Statistics> statisticslist=statisticsBizImp.mothcount(i+"",datestr2,cid);
                for (int j = 0; j < statisticslist.size(); j++)
                {
                    Statistics ss = statisticslist.get(j);
                    count1 += ss.getMoney() * ss.getPnum();
                }
            ucount.setCount1(count1);
            list.add(ucount);
        }
        return list;
    }
}
