package cn.bus.biz;

import cn.bus.entity.Driver;
import cn.bus.entity.DriverWorkload;
import cn.bus.entity.Line;
import cn.bus.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("idbiz")
public class DriverBizImp implements IDriverBiz {

    @Resource
    private IDriverMapper driverMapper;

    @Resource
    private IDriverWorkloadMapper driverWorkloadMapper;

    @Resource
    private IDriverWagesMapper driverWagesMapper;

    @Resource
    private DriverLineMapper driverLineMapper;

    @Resource
    private IDriverSchedulingMapper driverSchedulingMapper;



    //新闻
    @Resource
    private NewsMapper newsMapper;

//合作商
    @Resource
    private PartnersMapper partnersMapper;
    //广告
    @Resource
    private AdvertisingMapper advertisingMapper;


//查询分页
    @Override
    public List findall(Driver driver) {
        System.out.println("查询分页");
        return driverMapper.findall(driver);
    }
//查询条数
    @Override
    public List count(Driver driver) {
        return driverMapper.count(driver);
    }
//修改司机列表
    @Override
    public int upadriver(String name, String xianglu) {
        System.out.println("进数据修改:"+ xianglu);

        return driverMapper.upadriver(name,xianglu);
    }


    //查询工作量
    @Override
    public List findalldriverscheduling(DriverWorkload driverWorkload) {
        return  driverWorkloadMapper.findalldriverscheduling(driverWorkload);
    }
    //查询工作量条数
    @Override
    public List countdriverscheduling(DriverWorkload driverWorkload) {
        return  driverWorkloadMapper.countdriverscheduling(driverWorkload);
    }

    //查询工资

    @Override
    public List findallWages( Map <String,Object> condition) {
        System.out.println("有在");
        return driverWagesMapper.findallWages(condition);
    }

    @Override
    public List countWages(  Map<String,Object> condition) {
        return driverWagesMapper.countWages(condition);
    }

    @Override
    public int upaccount(String name, String wages) {
        return driverWagesMapper.upaccount(name,wages);
    }

    //站点
    @Override
    public List<Line> findallline() {
        return driverLineMapper.findallline();
    }

    //司机排班

    @Override
    public List findallScheduling(Map <String, Object> condition) {
        return driverSchedulingMapper.findallScheduling(condition);
    }

    @Override
    public List countScheduling(Map <String, Object> condition) {
        return driverSchedulingMapper.countScheduling(condition);
    }


    //新闻
    @Override
    public List findallNews(Map <String, Object> condition) {
        return newsMapper.findallNews(condition);
    }

    @Override
    public List countNews(Map <String, Object> condition) {
        return newsMapper.findallNews(condition);
    }

    @Override
    public int deleteNews(Integer nid) {
        return newsMapper.deleteNews(nid);
    }

    @Override
    public int updateNews(String nid, String daynews) {
        return newsMapper.updateNews(nid, daynews);
    }

    //合作商
    @Override
    public List findallPartners(Map <String, Object> condition) {
        return partnersMapper.findallPartners(condition);
    }

    @Override
    public List countPartners(Map <String, Object> condition) {
        return partnersMapper.countPartners(condition);
    }

    @Override
    public int deletePartners(Integer mid) {
        return partnersMapper.deletePartners(mid);
    }

    @Override
    public int updatePartners(String mid, String mnane, String msite) {
        return partnersMapper.updatePartners(mid,mnane,msite);
    }


    //广告
    @Override
    public List findallAdvertising(Map <String, Object> condition) {
        return advertisingMapper.findallAdvertising(condition);
    }

    @Override
    public List countAdvertising(Map <String, Object> condition) {
        return advertisingMapper.countAdvertising(condition);
    }
}
