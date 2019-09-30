package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Menu;
import cn.bus.entity.Parameter;
import cn.bus.entity.Role;

import java.util.List;
import java.util.Map;

/**
* @Author:小星
* @Description:后台用户业务接口
* @Date:上午 9:53 2019/9/19 0019
*/
public interface IAdminBiz {

    public Admin login(String anum, String apwd);
    public Admin loginjax(String anum);
    public List<Menu> findfmenu(String anum);
    public List<Menu> findcmenu(String anum);
    public List<Admin> findrole();
    public List<Admin> listajax(Map<String, Object> conditions);
    public Integer listcount(Map<String, Object> conditions);
    public Integer deleteanum(Map<String, Object> delete);
    public Integer upstate(Map<String, Object> upstate);
    public Parameter findpass(Integer pid);
    public Integer uppass(Map<String, Object> upstate);
    public Integer addAdmin(Admin admin);
    public Integer uprole(Map<String, Object> uprole);
    public Role roleajax(String role);
    public Integer deleterole(Integer rid);
    public Integer addrole(String role);
    public List<Menu> allpower();
    public List<Menu> power(Integer rid);
}
