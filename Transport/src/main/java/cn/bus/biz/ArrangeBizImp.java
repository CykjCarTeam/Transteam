package cn.bus.biz;

import cn.bus.entity.*;
import cn.bus.mapper.ArrangeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class ArrangeBizImp implements ArrangeBiz {

    @Resource
    private ArrangeMapper arrangeMapper;


    //车辆排班表
    @Override
    public List<Bus> scheduling( String lid,String toback) {
        Map<String,Object> map = new HashMap<>();
        map.put("lid",lid);
        map.put("toback",toback);
        return  arrangeMapper.scheduling(map);
    }
    //排班、排班替换
    @Override
    public List<Bus> state(Integer tid, String bid) {
        Map<String,Object> map = new HashMap<>();
        map.put("tid",tid);
        map.put("bid",bid);
        return arrangeMapper.state(map);
    }
    //实时停站线路查询总条数
    @Override
    public List<Time> removeall(String bid,String station,String province,String city,Integer time) {
        Map<String,Object> map = new HashMap<>();
        map.put("bid",bid);
        map.put("station",station);
        map.put("province",province);
        map.put("city",city);
        map.put("time",time);
        return  arrangeMapper.removeall(map);
    }
    //实时停站线路查询
    @Override
    public List<Bus> remove(String bid,String station,String province,String city,Integer time, Integer page, Integer limit) {
        Map<String,Object> map = new HashMap<>();
        map.put("bid",bid);
        map.put("station",station);
        map.put("province",province);
        map.put("city",city);
        map.put("time",time);
        map.put("page",page);
        map.put("limit",limit);
        return  arrangeMapper.remove(map);
    }
    //省份查询
    @Override
    public List<City> province() {
        return  arrangeMapper.province(new City());
    }
    //城市查询
    @Override
    public List<City> city(String province) {
        return  arrangeMapper.city(province);
    }

}
