package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.City;
import cn.bus.entity.Station;
import cn.bus.mapper.IAdminMapper;
import cn.bus.mapper.IStationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("iStationBiz")
public class StationBizImp implements IStationBiz {
    @Resource
    private IStationMapper iStationMapper;
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
        return iStationMapper.station_list(map);
    }

    @Override
    public Integer station_listpage(String city, String station, Integer page)
    {
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("city",city);
        map2.put("station",station);
        List<Station> stationList=iStationMapper.station_listpage(map2);
        Integer count=stationList.size();
        return count;
    }

    @Override
    public List<City> station_city()
    {
        return iStationMapper.station_city();
    }

    @Override
    public boolean station_add(String station, String coor_x, String coor_y,String cid)
    {
        boolean flag=false;
        Map<String,Object> stationmap=new HashMap<String, Object>();
        stationmap.put("station",station);
        stationmap.put("coor_x",coor_x);
        stationmap.put("coor_y",coor_y);

        iStationMapper.station_add(stationmap);
        Integer count=station_city_add((Integer) stationmap.get("sid"),cid);
        if(count>0)
        {
            flag=true;
        }
        return flag;
    }

    @Override
    public Integer station_city_add(Integer sid,String cid)
    {
        Map<String,Object> scnmap=new HashMap<String, Object>();
        scnmap.put("sid",sid);
        scnmap.put("cid",cid);

        return iStationMapper.station_city_add(scnmap);
    }

    @Override
    public boolean station_del(String sid,String cid)
    {
        Integer count=iStationMapper.station_city_del(sid,cid);
        Integer count2=iStationMapper.station_del(sid);
        boolean flag=false;
        if (count>0&&count2>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public Integer station_city_del(String sid, String cid)
    {
        return iStationMapper.station_city_del(sid,cid);
    }

    @Override
    public Integer stationupdate(String sid, String station, String coor_x, String coor_y)
    {
        Map<String,Object> supmap=new HashMap<String, Object>();
        supmap.put("sid",sid);
        supmap.put("station",station);
        supmap.put("coor_x",coor_x);
        supmap.put("coor_y",coor_y);

        return iStationMapper.stationupdate(supmap);
    }
}
