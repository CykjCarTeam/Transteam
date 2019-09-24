package cn.bus.mapper;

import cn.bus.entity.City;
import cn.bus.entity.Station;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IStationMapper
{
    public List<Station> station_list(Map map);
    public List<Station> station_listpage(Map map);
    public List<City> station_city();
}
