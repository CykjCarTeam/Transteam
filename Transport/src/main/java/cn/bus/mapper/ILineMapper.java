package cn.bus.mapper;

import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.entity.Line;
import cn.bus.entity.Station;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 线路表操作接口
* @Date:上午 9:53 2019/9/24 0024
*/
@Repository
public interface ILineMapper {

    public List<Line> queryLines(Map<String,Object> condition);//查询所有线路
    public int queryCounts(Map<String, Object> condition);//线路个数
    public List<Station> queryLineStations(Integer lid);//线路站点
    public List<Bus> queryLineBuses(Integer lid);//线路车辆
    public List<City> queryCitys();  //所有城市
    public List<Station>queryCityStations(Integer cid);//城市站点
    public Line queryLine(String line);//根据查询路名查询线路
    public void updateLine(Line line);//更新线路名
}
