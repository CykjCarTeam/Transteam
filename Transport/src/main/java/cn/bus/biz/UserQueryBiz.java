package cn.bus.biz;

import cn.bus.entity.BusUser;
import cn.bus.entity.Station;
import cn.bus.entity.Uqueryline;
import cn.bus.entity.Uquerylinetime;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


public interface UserQueryBiz
{
    public List<Uqueryline> uqueryline(String line);
    public List<Uquerylinetime> uquerytime(String bid);
    public List<Station> uquerystation();
    public List<Station> uquerystation2(String station);
    public Uqueryline linestationname(Integer lid);
    public List<Station> roleline(Integer lid);
    public List<Station> ualongstation(String lid);
}
