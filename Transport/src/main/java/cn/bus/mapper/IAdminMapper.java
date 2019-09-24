package cn.bus.mapper;

import cn.bus.entity.Admin;
import cn.bus.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author:小星
* @Description: 后台用户表操作接口
* @Date:上午 9:53 2019/9/19 0019
*/
@Repository
public interface IAdminMapper {
    public Admin queryUser(Admin user);
    public Admin loginjax(String anum);
    public List<Menu> findfmenu(String anum);
    public List<Menu> findcmenu(String anum);

}
