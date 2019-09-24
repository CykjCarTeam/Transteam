package cn.bus.biz;

import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.entity.Line;
import cn.bus.entity.Station;
import cn.bus.mapper.ILineMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class LineBizImp implements ILineBiz {

    @Resource
    private ILineMapper iLineMapper;

    @Override
    public List<Line> getLines(Map<String, Object> condition) {

        List<Line> list = iLineMapper.queryLines(condition);
        List<Line> lines = new LinkedList<>();
        for(Line line : list){
           List<Station> stations = this.getStations(line.getLid());//添加站点
           List<Bus> buses = this.getBuses(line.getLid());//添加车辆
           line.setAllStations(stations);
           line.setAllBus(buses);
           lines.add(line);
        }
        return lines;
    }

    @Override
    public int lineCounts(Map<String, Object> condtion) {
        return iLineMapper.queryCounts(condtion);
    }

    @Override
    public List<Station> getStations(Integer lid) {
        return iLineMapper.queryLineStations(lid);
    }

    @Override
    public List<Bus> getBuses(Integer lid) {
        return iLineMapper.queryLineBuses(lid);
    }

    @Override
    public List<Station> cityStations(Integer cid) {
        return iLineMapper.queryCityStations(cid);
    }

    @Override
    public List<City> getCitys() {
        return iLineMapper.queryCitys();
    }

    @Override
    public Line verifyLine(String line) {
        return iLineMapper.queryLine(line);
    }

    @Override
    public void modifyLine(Line line) {
        iLineMapper.updateLine(line);
    }

}
