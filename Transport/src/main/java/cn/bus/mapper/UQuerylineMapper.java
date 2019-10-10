package cn.bus.mapper;

import cn.bus.entity.BusUser;
import cn.bus.entity.Station;
import cn.bus.entity.Uqueryline;
import cn.bus.entity.Uquerylinetime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UQuerylineMapper
{
    public List<Uqueryline> uqueryline(@Param("line")String line);
    public List<Uquerylinetime> uquerytime(@Param("bid")String bid);
    public List<Station> uquerystation();
    public List<Station> uquerystation2(@Param("station")String station);
    public Uqueryline linestationname(@Param("lid")Integer lid);
    public List<Station> roleline(@Param("lid")Integer lid);
    public List<Station> ualongstation(@Param("lid")String lid);
}
