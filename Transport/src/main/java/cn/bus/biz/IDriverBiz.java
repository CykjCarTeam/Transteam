package cn.bus.biz;

import cn.bus.entity.Driver;
import cn.bus.entity.DriverWorkload;
import cn.bus.entity.Line;

import java.util.List;
import java.util.Map;

public interface IDriverBiz {
  //司机列表
  public  List findall(Driver driver);
  public  List count(Driver driver);

//  public  List findall(Map <String,Object> condition);
//  public  List count(Driver driver);

  //修改
  public int upadriver(String name, String xianglu);



  //工作量表

  public  List findalldriverscheduling(DriverWorkload driverWorkload);
  public  List countdriverscheduling(DriverWorkload driverWorkload);

  //工资表
  public  List findallWages(Map<String, Object> condition);
  public  List countWages(Map<String, Object> condition);

  public int upaccount(String name, String wages);


  //路线
  public  List<Line> findallline();

  //司机排班
  public  List findallScheduling(Map<String, Object> condition);
  public  List countScheduling(Map<String, Object> condition);


  //新闻
  public  List findallNews(Map<String, Object> condition);
  public  List countNews(Map<String, Object> condition);

  public int deleteNews(Integer nid);

  public int updateNews(String nid, String daynews);


  //合作商
  public  List findallPartners(Map<String, Object> condition);
  public  List countPartners(Map<String, Object> condition);

  public int deletePartners(Integer mid);

  public int updatePartners(String mid, String mnane, String msite);

  //广告
  public  List findallAdvertising(Map<String, Object> condition);
  public  List countAdvertising(Map<String, Object> condition);
}
