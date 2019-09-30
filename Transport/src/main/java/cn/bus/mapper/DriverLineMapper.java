package cn.bus.mapper;

import cn.bus.entity.Line;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverLineMapper {
    public List<Line> findallline();
}
