package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Bus;
import cn.bus.entity.City;
import cn.bus.mapper.BusMapper;
import cn.bus.mapper.IAdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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

    @Resource
    private BusMapper busMapper;

    @Override
    public void findCity(ModelAndView model) {
        List list = busMapper.findCity();
        Map <String,List<City>>map=new <String,List<City>>HashMap();
        for (int i=0;i<=list.size();i++){
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
        System.out.println(map);
        model.addObject("proMap",map);
    }
    @Override
    public void find(ModelAndView model, Bus bus) {
        List list = busMapper.find(bus);
        model.addObject("busList",list);
    }
}
