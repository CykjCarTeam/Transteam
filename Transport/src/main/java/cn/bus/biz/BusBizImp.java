package cn.bus.biz;

import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.entity.Line;
import cn.bus.mapper.BusMapper;
import cn.bus.tool.PageUtil;
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
    public void findBus(Map map) {
        List list = busMapper.findBus(map);
        total=busMapper.busTotal(map);
        map.put("count", total);
        map.put("data",list);
    }
    @Override
    public void findProtector(ModelAndView model){
        model.addObject("protectors",busMapper.findProtector()) ;//前台条件查询下拉框需要
    }

    @Override
    public void findLine(ModelAndView model,Map map) {
        model.addObject("lineList",busMapper.findLine(map));
    }

    @Override
    public void findState(ModelAndView model) {
        model.addObject("stateList",busMapper.findState()) ;
    }

    @Override
    public void findallBus(ModelAndView model, Map map) {
        model.addObject("allBus",busMapper.findBus(map)) ;
    }

    @Override
    public void findCityByid(ModelAndView model, Map map) {
        model.addObject("city",busMapper.findCityByid(map));
    }

    @Override
    public int change(Map map) {
        int num = busMapper.change(map);
        System.out.println("change-------"+num);
        return num;
    }

    @Override
    public Bus findBusByid(Bus bus) {
        return busMapper.findBusByid(bus);
    }

    //新增
    @Override
    public boolean add(Map map) {
        return busMapper.addBus(map);
    }
//删除
    @Override
    public int delete(Bus bus) {
        return busMapper.delBus(bus);
    }
//报废
    @Override
    public int stop(Bus bus) {
        return busMapper.stop(bus);
    }

    @Override
    public List<Line> findline(Integer page, Integer limit) {
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        return  busMapper.findline(map);
    }

    //线路列表查询总条数
    @Override
    public List<Line> findlineall() {
        return  busMapper.findlineall(new Line());
    }
}
