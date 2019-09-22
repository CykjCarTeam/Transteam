package cn.bus.mapper;

import cn.bus.entity.Admin;
import org.springframework.stereotype.Repository;
/**
* @Author:小星
* @Description: 后台用户表操作接口
* @Date:上午 9:53 2019/9/19 0019
*/
@Repository
public interface IAdminMapper {

    public Admin queryUser(Admin user);
}
