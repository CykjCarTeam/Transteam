package cn.bus.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NewsMapper {

    public List findallNews(Map<String, Object> condition);
    public  List countNews(Map<String, Object> condition);

   public int updateNews(@Param("nid") String nid, @Param("daynews") String daynews);

    public int deleteNews(@Param("nid") Integer nid);
}
