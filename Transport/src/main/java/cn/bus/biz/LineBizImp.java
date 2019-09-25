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
    public Integer lineCounts(Map<String, Object> condtion) {
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
    public Boolean createLine(Map<String, Object> condition) {

        Integer num =  iLineMapper.addLine(condition);//线路表插入
        Integer num2 = iLineMapper.addCityLine(condition); //城市站点关系表插入
        Integer num3 = -1;
        Boolean flag = false;
        List<Integer> stations = (List<Integer>)condition.get("stations");
        for(Integer sid : stations){
            condition.remove("sid");
            condition.put("sid", sid);
            num3 = iLineMapper.addLineStations(condition); //线路站点关系表的插入
        }

        if(num > 0 && num2 > 0 && num3 > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public void modifyLine(Line line) {

        iLineMapper.updateLine(line);
    }

    @Override
    public Boolean deleteLine(Integer lid) {
        //那我要如何去判定我数据删除成功？
        Integer num = iLineMapper.delCityLine(lid);     //删的条数
        Integer num2 = iLineMapper.delLineStation(lid);
        Integer num3 = iLineMapper.delBusLine(lid);     //关联表数据为空是返回值=0
        Integer num4 = iLineMapper.delDriverLine(lid);
        Integer num5 = iLineMapper.delLine(lid);
        System.out.println("num" + num);
        System.out.println("num2" + num2);
        System.out.println("num3" + num3);
        System.out.println("num4" + num4);
        System.out.println("num5" + num5);

        Boolean flag = false;

        if(num > 0 && num2 > 0 && num3 > 0 && num4 > 0 & num5 > 0){
            flag = true;
        }

        return flag;
    }

}
