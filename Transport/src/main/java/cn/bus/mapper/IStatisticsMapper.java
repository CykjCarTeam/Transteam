package cn.bus.mapper;

import cn.bus.entity.Admin;
import cn.bus.entity.Statistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStatisticsMapper
{
public List<Statistics> statistics_line(@Param("cid") String cid);
public List<Statistics> mothcount(@Param("week")String week,@Param("dates")String dates,@Param("cid")String cid);
}
