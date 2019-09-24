package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Station;
import cn.bus.mapper.IAdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("iAdminBiz")
public class AdminBizImp implements IAdminBiz {

    @Resource
    private IAdminMapper iAdminMapper;
    @Override
    public Admin login(String anum, String apwd) {
        return iAdminMapper.queryUser(new Admin(anum, apwd));
    }

    @Override
    public List<Station> station_list(String city, String station, Integer page, Integer limit)
    {
        Map<String,Object> map=new HashMap<String, Object>();
        Integer start=(page-1)*limit;
        Integer end=limit;
        map.put("city",city);
        map.put("station",station);
        map.put("start",start);
        map.put("end",end);
        return iAdminMapper.station_list(map);
    }

    @Override
    public Integer station_listpage(String city, String station, Integer page)
    {
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("city",city);
        map2.put("station",station);
        List<Station> stationList=iAdminMapper.station_listpage(map2);
        Integer count=stationList.size();
        return count;
    }
}
