package cn.bus.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IDriverSchedulingMapper {


    public  List findallScheduling(Map <String, Object> condition);
    public  List countScheduling(Map <String, Object> condition);

//    public int upaccount(@Param("name") String name, @Param("account") String xianglu);







}
