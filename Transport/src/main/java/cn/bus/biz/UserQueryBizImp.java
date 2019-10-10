package cn.bus.biz;

import cn.bus.entity.Station;
import cn.bus.entity.Uqueryline;
import cn.bus.entity.Uquerylinetime;
import cn.bus.mapper.UQuerylineMapper;
import cn.bus.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Service("userQueryBiz")
public class UserQueryBizImp implements UserQueryBiz {

    @Resource
    private UQuerylineMapper uQuerylineMapper;

    @Override
    public List<Uqueryline> uqueryline(String line)
    {
        return uQuerylineMapper.uqueryline(line);
    }

    @Override
    public List<Uquerylinetime> uquerytime(String bid)
    {
        return uQuerylineMapper.uquerytime(bid);
    }

    @Override
    public List<Station> uquerystation()
    {
        return uQuerylineMapper.uquerystation();
    }

    @Override
    public List<Station> uquerystation2(String station)
    {
        return uQuerylineMapper.uquerystation2(station);
    }

    @Override
    public Uqueryline linestationname(Integer lid)
    {
        return uQuerylineMapper.linestationname(lid);
    }

    @Override
    public List<Station> roleline(Integer lid)
    {
        return uQuerylineMapper.roleline(lid);
    }

    @Override
    public List<Station> ualongstation(String lid)
    {
        return uQuerylineMapper.ualongstation(lid);
    }
}
