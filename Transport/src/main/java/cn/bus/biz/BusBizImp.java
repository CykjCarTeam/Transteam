package cn.bus.biz;

import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.mapper.BusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class BusBizImp implements BusBiz {

    private int total=0;
    @Resource
    private BusMapper busMapper;

    @Override
    public void findCity(ModelAndView model) {
        List list = busMapper.findCity();
        Map <String,List<City>>map=new <String,List<City>>HashMap();
        for (int i=0;i<list.size();i++){
            City city=(City)list.get(i);
            String province=city.getProvince();
            if (!map.containsKey(province)){
                List list1=new ArrayList();
                list1.add(city);
                map.put(province,list1);
            }else {
                map.get(province).add(city);
            }
        }
        model.addObject("proMap",map);
    }


    @Override
    public void findBus(Map map, Bus bus) {
        List list = busMapper.findBus(bus);
        total=busMapper.busTotal(bus);
        map.put("count", total);
        map.put("data",list);
    }
    @Override
    public void findProtector(ModelAndView model){
        model.addObject("protectors",busMapper.findProtector()) ;//前台条件查询下拉框需要
    }

    @Override
    public void findLine(ModelAndView model,Bus bus) {
        model.addObject("lineList",busMapper.findLine(bus));
    }

    @Override
    public void findState(ModelAndView model) {
        model.addObject("stateList",busMapper.findState()) ;
    }

    @Override
    public void findallBus(ModelAndView model, Bus bus) {
        model.addObject("allBus",busMapper.findBus(bus)) ;
    }

    @Override
    public void findCityByid(ModelAndView model, Bus bus) {
        model.addObject("city",busMapper.findCityByid(bus));
    }

    @Override
    public void change(Bus bus) {
        int num = busMapper.change(bus);
        System.out.println("change-------"+num);
    }
    //新增
    @Override
    public void add(Bus bus) {
        busMapper.addBus(bus);
    }
//删除
    @Override
    public void delete(Bus bus) {
        busMapper.delBus(bus);
    }
//报废
    @Override
    public void stop(Bus bus) {
        busMapper.stop(bus);
    }

    @Test
    public void check(){
        Bus bus=new Bus();
        bus.setCid(1);
        busMapper.findBus(bus);
    }
}
