package cn.bus.mapper;

import cn.bus.entity.DriverWorkload;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDriverWorkloadMapper {

    public List findalldriverscheduling(DriverWorkload driverWorkload);
    public  List countdriverscheduling(DriverWorkload driverWorkload);
}
