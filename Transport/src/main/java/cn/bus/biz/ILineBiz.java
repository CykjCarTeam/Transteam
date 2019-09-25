package cn.bus.biz;

import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.entity.Line;
import cn.bus.entity.Station;

import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 线路业务接口
* @Date:上午 10:19 2019/9/24 0024
*/
public interface ILineBiz {

    public List<Line> getLines(Map<String, Object> condition);//查询线路
    public Integer lineCounts(Map<String, Object> condtion);//线路总数
    public List<Station> getStations(Integer lid);//线路站点数
    public List<Bus> getBuses(Integer lid);//线路车辆数
    public List<Station> cityStations(Integer cid);//城市站点
    public List<City> getCitys();//所有城市信息
    public Line verifyLine(String line);//检测线路是否存在
    public Boolean createLine(Map<String, Object> condition);//创建一条线路
    public Boolean deleteLine(Integer lid);//删除线路
    public void modifyLine(Line line);//更新线路
}
