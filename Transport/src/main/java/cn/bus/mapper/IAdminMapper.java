package cn.bus.mapper;

import cn.bus.entity.Admin;
import cn.bus.entity.Menu;
import cn.bus.entity.Parameter;
import cn.bus.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IAdminMapper {

    public Admin queryUser(Admin user);//登录
    public Admin loginjax(String anum);//用户ajax
    public List<Menu> findfmenu(String anum);//父类菜单
    public List<Menu> findcmenu(String anum);//子类菜单
    public List<Admin> findrole();//查询角色
    public List<Admin> listajax(Map<String, Object> conditions);//用户信息
    public Integer listcount(Map<String, Object> conditions);//总的条数
    public Integer deleteanum(Map<String, Object> delete);//删除
    public Integer upstate(Map<String, Object> upstate);//修改状态
    public Parameter findpass(Integer pid);//查询密码
    public Integer uppass(Map<String, Object> upstate);//修改状态
    public Integer addAdmin(Admin admin);//注册
    public Integer uprole(Map<String, Object> uprole);//修改角色
    public Role roleajax(String role);//判断角色是否存在
    public Integer deleterole(Integer rid);//删除角色
    public Integer addrole(String role);//增加角色
    public List<Menu> allpower();//所以权限
    public List<Menu> power(Integer rid);//查询权限

}
