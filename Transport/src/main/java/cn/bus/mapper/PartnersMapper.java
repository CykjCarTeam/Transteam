package cn.bus.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PartnersMapper {
    public List findallPartners(Map <String,Object> condition);
    public  List countPartners( Map<String,Object> condition);


    public int updatePartners(@Param("mid") String  mid,  @Param("mnane")String mnane,@Param("msite")String msite);
    public int deletePartners(@Param("mid") Integer mid);




}
