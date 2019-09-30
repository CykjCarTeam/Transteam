package cn.bus.biz;

import cn.bus.entity.City;
import cn.bus.entity.Station;
import cn.bus.entity.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IStatisticsBiz
{
    public List<Statistics> statistics_line(String cid);
    public List<Statistics> mothcount(String week,String dates,String cid);
}
