package cn.bus.mapper;

import cn.bus.entity.Driver;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDriverMapper {
    public List findall(Driver driver);
    public List count(Driver driver);

    public int upadriver(@Param("name") String  name, @Param("xianglu") String xianglu);

}
