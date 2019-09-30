package cn.bus.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdvertisingMapper {
    public  List findallAdvertising( Map<String,Object> condition);
    public  List countAdvertising( Map<String,Object> condition);
}
