package cn.bus.mapper;

import cn.bus.entity.Admin;
import cn.bus.entity.Station;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface IAdminMapper {

    public Admin queryUser(Admin user);
}
