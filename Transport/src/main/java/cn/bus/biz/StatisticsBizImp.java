package cn.bus.biz;

import cn.bus.entity.Statistics;
import cn.bus.mapper.IStationMapper;
import cn.bus.mapper.IStatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("iStatisticsBiz")
public class StatisticsBizImp implements IStatisticsBiz
{
    @Resource
    private IStatisticsMapper iStatisticsMapper;
    @Override
    public List<Statistics> statistics_line(String cid)
    {
        return iStatisticsMapper.statistics_line(cid);
    }

    @Override
    public List<Statistics> mothcount(String week, String dates,String cid)
    {
        return iStatisticsMapper.mothcount(week,dates,cid);
    }
}
